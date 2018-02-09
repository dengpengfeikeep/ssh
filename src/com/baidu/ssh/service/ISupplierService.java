package com.baidu.ssh.service;

import java.util.List;

import com.baidu.ssh.domain.Supplier;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.SupplierQueryObject;

public interface ISupplierService {
	void save(Supplier obj);

	void update(Supplier obj);

	void delete(Long id);

	Supplier get(Long id);

	List<Supplier> list();

	PageResult query(SupplierQueryObject obj);

}
