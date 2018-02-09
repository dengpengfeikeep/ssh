package com.baidu.ssh.service;

import java.util.List;

import com.baidu.ssh.domain.Product;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.ProductQueryObject;

public interface IProductService {
	void save(Product obj);

	void update(Product obj);

	void delete(Long id);

	Product get(Long id);

	List<Product> list();

	PageResult query(ProductQueryObject obj);

	/**
	 * @Description 返回商品的json格式数据
	 * @param id
	 */
	String getToJson(Long id);

}
