package com.baidu.ssh.service.impl;

import com.baidu.ssh.dao.IProductStockDAO;
import com.baidu.ssh.domain.ProductStock;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.QueryObject;
import com.baidu.ssh.service.IProductStockService;
import lombok.Setter;

import java.util.List;

public class ProductStockServiceImpl implements IProductStockService {
    @Setter
    private IProductStockDAO productStockDAO;
    public void save(ProductStock productStock) {
        productStockDAO.save(productStock);
    }

    public void update(ProductStock productStock) {
        productStockDAO.update(productStock);
    }

    public void delete(Long id) {
        productStockDAO.delete(id);
    }

    public ProductStock get(Long id) {
        return productStockDAO.get(id);
    }

    public List<ProductStock> list() {
        return productStockDAO.list();
    }

    public PageResult query(QueryObject qo) {
        return productStockDAO.query(qo);
    }
}
