package com.baidu.ssh.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import com.baidu.ssh.dao.IProductStockDAO;
import com.baidu.ssh.domain.ProductStock;

public class ProductStockDAOImpl extends GenericDAOImpl<ProductStock> implements
		IProductStockDAO {

	/**
	 * @Description 根据仓库和货品找库存对象
	 * @param depotId
	 * @param productId
	 */
	public ProductStock getByDepotIdAndProductId(Long depotId, Long productId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select obj from ProductStock obj where obj.depot.id = ? and obj.product.id = ? and obj.stockNumber > 0";
		Query query = session.createQuery(hql);
		Object result = query.setParameter(0, depotId)
				.setParameter(1, productId).uniqueResult();
		return (ProductStock) result;
	}
}
