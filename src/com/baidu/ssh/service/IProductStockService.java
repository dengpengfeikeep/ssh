package com.baidu.ssh.service;


import com.baidu.ssh.domain.ProductStock;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.QueryObject;

import java.util.List;

public interface IProductStockService {

    void save(ProductStock productStock);

    void update(ProductStock productStock);

    void delete(Long id);

    ProductStock get(Long id);

    List<ProductStock> list();

    PageResult query(QueryObject qo);
}
