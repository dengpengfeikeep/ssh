package com.baidu.ssh.service.impl;

import java.util.Date;
import java.util.List;

import lombok.Setter;

import com.baidu.ssh.dao.IProductStockDAO;
import com.baidu.ssh.dao.IStockOutcomeBillDAO;
import com.baidu.ssh.domain.ProductStock;
import com.baidu.ssh.domain.StockOutcomeBill;
import com.baidu.ssh.domain.StockOutcomeBillItem;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.QueryObject;
import com.baidu.ssh.service.IStockOutcomeBillService;
import com.baidu.ssh.util.CurrentEmployee;

public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService {
	@Setter
	private IStockOutcomeBillDAO stockOutcomeBillDAO;

	@Setter
	private IProductStockDAO productStockDAO;

	public void save(StockOutcomeBill bill) {
		// 1.设置制单人和制单时间
		bill.setInputUser(CurrentEmployee.getCurrentEmployee());
		bill.setInputTime(new Date());
		// 2.设置未审核状态
		bill.setStatus(StockOutcomeBill.NORMAL);
		for (StockOutcomeBillItem item : bill.getItems()) {
			// 3.设置入库单与入库单明细之间的联系
			item.setBill(bill);
			// 4.计算小计
			item.setAmount(item.getAmount());
			// 5.计算入库数量和金额
			bill.setTotalNumber(item.getNumber());
			bill.setTotalAmount(item.getAmount());
		}
		// 6.保存入库单
		stockOutcomeBillDAO.save(bill);
	}

	public void update(StockOutcomeBill bill) {
		if (bill.getStatus() == StockOutcomeBill.NORMAL) {
			// 2.设置未审核状态
			bill.setStatus(StockOutcomeBill.NORMAL);
			for (StockOutcomeBillItem item : bill.getItems()) {
				// 3.设置入库单与入库单明细之间的联系
				item.setBill(bill);
				// 4.计算小计
				item.setAmount(item.getAmount());
				// 5.计算入库数量和金额
				bill.setTotalNumber(item.getNumber());
				bill.setTotalAmount(item.getAmount());
			}
		}
		stockOutcomeBillDAO.update(bill);
	}

	public void delete(Long id) {
		stockOutcomeBillDAO.delete(id);
	}

	public StockOutcomeBill get(Long id) {
		return stockOutcomeBillDAO.get(id);
	}

	public List<StockOutcomeBill> list() {
		return stockOutcomeBillDAO.list();
	}

	public PageResult query(QueryObject qo) {
		return stockOutcomeBillDAO.query(qo);
	}

	/**
	 * 	审核,会牵涉到库存的改变(金额,数量,价格)
	 */
	@SuppressWarnings("all")
	public void audit(Long id) {
		// 1.先判断当前为未审核状态
		StockOutcomeBill outcomeBill = stockOutcomeBillDAO.get(id);
		if (outcomeBill != null
				&& outcomeBill.getStatus() == StockOutcomeBill.NORMAL) {
			// 2.设置审核人,审核时间,已审核状态
			outcomeBill.setAuditor(CurrentEmployee.getCurrentEmployee());
			outcomeBill.setAuditTime(new Date());
			outcomeBill.setStatus(StockOutcomeBill.CHECK);
			// 3.操作库存
			for (StockOutcomeBillItem item : outcomeBill.getItems()) {
				// 判断库存中是否有该商品
				ProductStock productStock = productStockDAO
						.getByDepotIdAndProductId(outcomeBill.getDepot()
								.getId(), item.getProduct().getId());
				if (productStock == null
						|| productStock.getStockNumber().compareTo(
								item.getNumber()) < 0) {
					// 库存没有该商品或者库存不足
					throw new RuntimeException(item.getProduct().getName()
							+ "缺货" + item.getNumber() + "件!");
				} else {
					// 库存中有该商品并且数量充足,销售出库不会涉及库存价格的变动
					// 改变库存总金额
					productStock.setAmount(productStock.getPrice().multiply(
							productStock.getStockNumber()));
					// 改变库存总数量
					productStock.setStockNumber(productStock.getStockNumber()
							.subtract(item.getNumber()));
					productStockDAO.update(productStock);
				}
			}
		}
		stockOutcomeBillDAO.update(outcomeBill);
	}
}
