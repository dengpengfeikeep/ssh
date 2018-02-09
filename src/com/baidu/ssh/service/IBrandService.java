package com.baidu.ssh.service;

import java.util.List;

import com.baidu.ssh.domain.Brand;
import com.baidu.ssh.query.BrandQueryObject;
import com.baidu.ssh.query.PageResult;

public interface IBrandService {
	void save(Brand obj);

	void update(Brand obj);

	void delete(Long id);

	Brand get(Long id);

	List<Brand> list();

	PageResult query(BrandQueryObject obj);

}
