package com.baidu.ssh.service.impl;

import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import lombok.Setter;

import com.baidu.ssh.dao.IProductStockDAO;
import com.baidu.ssh.dao.IStockIncomeBillDAO;
import com.baidu.ssh.domain.ProductStock;
import com.baidu.ssh.domain.StockIncomeBill;
import com.baidu.ssh.domain.StockIncomeBillItem;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.QueryObject;
import com.baidu.ssh.service.IStockIncomeBillService;
import com.baidu.ssh.util.CurrentEmployee;

public class StockIncomeBillServiceImpl implements IStockIncomeBillService {
	@Setter
	private IStockIncomeBillDAO stockIncomeBillDAO;

	@Setter
	private IProductStockDAO productStockDAO;

	public void save(StockIncomeBill bill) {
		// 1.设置制单人和制单时间
		bill.setInputUser(CurrentEmployee.getCurrentEmployee());
		bill.setInputTime(new Date());
		// 2.设置未审核状态
		bill.setStatus(StockIncomeBill.NORMAL);
		for (StockIncomeBillItem item : bill.getItems()) {
			// 3.设置入库单与入库单明细之间的联系
			item.setBill(bill);
			// 4.计算小计
			item.setAmount(item.getAmount());
			// 5.计算入库数量和金额
			bill.setTotalNumber(item.getNumber());
			bill.setTotalAmount(item.getAmount());
		}
		// 6.保存入库单
		stockIncomeBillDAO.save(bill);
	}

	public void update(StockIncomeBill bill) {
		if (bill.getStatus() == StockIncomeBill.NORMAL) {
			// 2.设置未审核状态
			bill.setStatus(StockIncomeBill.NORMAL);
			for (StockIncomeBillItem item : bill.getItems()) {
				// 3.设置入库单与入库单明细之间的联系
				item.setBill(bill);
				// 4.计算小计
				item.setAmount(item.getAmount());
				// 5.计算入库数量和金额
				bill.setTotalNumber(item.getNumber());
				bill.setTotalAmount(item.getAmount());
			}
		}
		stockIncomeBillDAO.update(bill);
	}

	public void delete(Long id) {
		stockIncomeBillDAO.delete(id);
	}

	public StockIncomeBill get(Long id) {
		return stockIncomeBillDAO.get(id);
	}

	public List<StockIncomeBill> list() {
		return stockIncomeBillDAO.list();
	}

	public PageResult query(QueryObject qo) {
		return stockIncomeBillDAO.query(qo);
	}

	/**
	 * 	审核,会牵涉到库存的改变(金额,数量,价格)
	 */
	@SuppressWarnings("all")
	public void audit(Long id) {
		StockIncomeBill incomeBill = stockIncomeBillDAO.get(id);
		// 1.先判断当前为未审核状态
		if (incomeBill.getStatus() == StockIncomeBill.NORMAL) {
			// 2.设置审核人,审核时间,已审核状态
			incomeBill.setAuditor(CurrentEmployee.getCurrentEmployee());
			incomeBill.setAuditTime(new Date());
			incomeBill.setStatus(StockIncomeBill.CHECK);
			// 3.操作库存
			for (StockIncomeBillItem item : incomeBill.getItems()) {
				// 判断库存中是否有该商品
				ProductStock productStock = productStockDAO
						.getByDepotIdAndProductId(
								incomeBill.getDepot().getId(), item
										.getProduct().getId());
				if (productStock != null) {
					// 库存中有该商品
					System.out.println(item.getNumber());
					productStock.setStockNumber(productStock.getStockNumber()
							.add(item.getNumber()));
					productStock.setAmount(productStock.getAmount().add(
							item.getAmount()));
					productStock.setPrice(productStock.getAmount().divide(
							productStock.getStockNumber(), 2,
							RoundingMode.HALF_UP));
					productStock.setDepot(incomeBill.getDepot());
					productStockDAO.update(productStock);
				} else {
					// 库存没有该商品
					productStock = new ProductStock();
					productStock.setDepot(incomeBill.getDepot());
					productStock.setAmount(item.getAmount());
					productStock.setPrice(item.getCostPrice());
					productStock.setProduct(item.getProduct());
					productStock.setStockNumber(item.getNumber());
					productStockDAO.save(productStock);
				}
			}
			stockIncomeBillDAO.update(incomeBill);
		}
	}
}
