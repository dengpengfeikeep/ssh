package com.baidu.ssh.service.impl;

import java.util.List;

import lombok.Setter;

import com.baidu.ssh.dao.IBrandDAO;
import com.baidu.ssh.domain.Brand;
import com.baidu.ssh.query.BrandQueryObject;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.service.IBrandService;

public class BrandServiceImpl implements IBrandService {
	@Setter
	private IBrandDAO BrandDAO;

	public void save(Brand obj) {
		BrandDAO.save(obj);

	}

	public void update(Brand obj) {
		BrandDAO.update(obj);

	}

	public void delete(Long id) {
		BrandDAO.delete(id);

	}

	public Brand get(Long id) {

		return BrandDAO.get(id);
	}

	public List<Brand> list() {

		return BrandDAO.list();
	}

	public PageResult query(BrandQueryObject qo) {

		return BrandDAO.query(qo);
	}

}
