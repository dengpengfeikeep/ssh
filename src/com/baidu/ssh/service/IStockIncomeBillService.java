package com.baidu.ssh.service;

import com.baidu.ssh.domain.StockIncomeBill;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.QueryObject;

import java.util.List;

public interface IStockIncomeBillService {

	void save(StockIncomeBill stockIncomeBill);

	void update(StockIncomeBill stockIncomeBill);

	void delete(Long id);

	StockIncomeBill get(Long id);

	List<StockIncomeBill> list();

	PageResult query(QueryObject qo);

	/**
	 * @Description 到货入库单审核
	 * @param id
	 */
	void audit(Long id);
}
