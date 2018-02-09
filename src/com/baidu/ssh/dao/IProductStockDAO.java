package com.baidu.ssh.dao;

import com.baidu.ssh.domain.ProductStock;

public interface IProductStockDAO extends IGenericDAO<ProductStock> {

	/**
	 * @Description 根据仓库和货品找库存对象
	 * @param depotId
	 * @param productId
	 */
	ProductStock getByDepotIdAndProductId(Long depotId, Long productId);
}
