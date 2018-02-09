package com.baidu.ssh.service.impl;

import java.util.Date;
import java.util.List;

import lombok.Setter;

import com.baidu.ssh.dao.IOrderBillDAO;
import com.baidu.ssh.domain.OrderBill;
import com.baidu.ssh.domain.OrderBillItem;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.QueryObject;
import com.baidu.ssh.service.IOrderBillService;
import com.baidu.ssh.util.CurrentEmployee;

public class OrderBillServiceImpl implements IOrderBillService {
	@Setter
	private IOrderBillDAO orderBillDAO;

	public void save(OrderBill bill) {
		// 设置制单人和制单时间
		bill.setInputUser(CurrentEmployee.getCurrentEmployee());
		bill.setInputTime(new Date());
		// 设置未审核状态
		bill.setStatus(OrderBill.NORMAL);
		// 设置订单与明细之间的关系
		for (OrderBillItem item : bill.getItems()) {
			item.setBill(bill);
			// 计算明细小计
			item.setAmount(item.getAmount());
			// 计算订单总价格,总数量
			bill.setTotalAmount(item.getAmount());
			bill.setTotalNumber(item.getNumber());
		}
		// 保存订单对象
		orderBillDAO.save(bill);
	}

	public void update(OrderBill bill) {
		// 判断状态是否为未审核
		if (bill.getStatus() == OrderBill.NORMAL) {
			// 设置未审核状态
			bill.setStatus(OrderBill.NORMAL);
			// 设置订单与明细之间的关系
			for (OrderBillItem item : bill.getItems()) {
				item.setBill(bill);
				// 计算明细小计
				item.setAmount(item.getAmount());
				// 计算订单总价格,总数量
				bill.setTotalAmount(item.getAmount());
				bill.setTotalNumber(item.getNumber());
			}
		}
		orderBillDAO.update(bill);
	}

	public void delete(Long id) {
		orderBillDAO.delete(id);
	}

	public OrderBill get(Long id) {
		return orderBillDAO.get(id);
	}

	public List<OrderBill> list() {
		return orderBillDAO.list();
	}

	public PageResult query(QueryObject qo) {
		return orderBillDAO.query(qo);
	}

	/**
	 *订单审核
	 */
	public void audit(Long id) {
		OrderBill bill = orderBillDAO.get(id);
		if (bill != null) {
			// 设置审核状态
			bill.setStatus(OrderBill.CHECK);
			// 设置审核人和审核时间
			bill.setAuditor(CurrentEmployee.getCurrentEmployee());
			bill.setAuditTime(new Date());
		}
	}
}
