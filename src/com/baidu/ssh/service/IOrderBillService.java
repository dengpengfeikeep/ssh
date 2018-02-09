package com.baidu.ssh.service;

import java.util.List;

import com.baidu.ssh.domain.OrderBill;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.QueryObject;

public interface IOrderBillService {

	void save(OrderBill orderBill);

	void update(OrderBill orderBill);

	void delete(Long id);

	OrderBill get(Long id);

	List<OrderBill> list();

	PageResult query(QueryObject qo);

	/**
	 * @Description 订单审核
	 * @param id
	 */
	void audit(Long id);
}
