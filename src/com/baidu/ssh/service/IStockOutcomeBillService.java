package com.baidu.ssh.service;

import java.util.List;

import com.baidu.ssh.domain.StockOutcomeBill;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.QueryObject;

public interface IStockOutcomeBillService {

	void save(StockOutcomeBill stockOutcomeBill);

	void update(StockOutcomeBill stockOutcomeBill);

	void delete(Long id);

	StockOutcomeBill get(Long id);

	List<StockOutcomeBill> list();

	PageResult query(QueryObject qo);

	/**
	 * @Description 销售出库单审核
	 * @param id
	 */
	void audit(Long id);
}
