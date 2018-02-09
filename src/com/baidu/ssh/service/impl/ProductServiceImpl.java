package com.baidu.ssh.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Setter;

import com.alibaba.fastjson.JSON;
import com.baidu.ssh.dao.IProductDAO;
import com.baidu.ssh.domain.Product;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.ProductQueryObject;
import com.baidu.ssh.service.IProductService;

public class ProductServiceImpl implements IProductService {
	@Setter
	private IProductDAO ProductDAO;

	public void save(Product obj) {
		ProductDAO.save(obj);

	}

	public void update(Product obj) {
		ProductDAO.update(obj);

	}

	public void delete(Long id) {
		ProductDAO.delete(id);

	}

	public Product get(Long id) {

		return ProductDAO.get(id);
	}

	public List<Product> list() {

		return ProductDAO.list();
	}

	public PageResult query(ProductQueryObject qo) {

		return ProductDAO.query(qo);
	}

	/**
	 *返回json格式数据
	 */
	public String getToJson(Long id) {
		Product product = ProductDAO.get(id);
		Map<String, Object> productMap = new HashMap<>();
		productMap.put("id", product.getId());
		productMap.put("name", product.getName());
		productMap.put("brandName", product.getBrand() != null ? product
				.getBrand().getName() : null);
		productMap.put("costPrice", product.getCostPrice());
		return JSON.toJSONString(productMap);
	}
}
