package com.baidu.ssh.service.impl;

import java.util.List;

import lombok.Setter;

import com.baidu.ssh.dao.ISupplierDAO;
import com.baidu.ssh.domain.Supplier;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.SupplierQueryObject;
import com.baidu.ssh.service.ISupplierService;

public class SupplierServiceImpl implements ISupplierService {
	@Setter
	private ISupplierDAO SupplierDAO;

	public void save(Supplier obj) {
		SupplierDAO.save(obj);

	}

	public void update(Supplier obj) {
		SupplierDAO.update(obj);

	}

	public void delete(Long id) {
		SupplierDAO.delete(id);

	}

	public Supplier get(Long id) {

		return SupplierDAO.get(id);
	}

	public List<Supplier> list() {

		return SupplierDAO.list();
	}

	public PageResult query(SupplierQueryObject qo) {

		return SupplierDAO.query(qo);
	}

}
