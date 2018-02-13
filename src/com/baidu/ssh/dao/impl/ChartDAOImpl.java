package com.baidu.ssh.dao.impl;

import java.util.List;

import lombok.Setter;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.baidu.ssh.dao.IChartDAO;
import com.baidu.ssh.query.ChartQueryObject;
import com.baidu.ssh.query.SaleChartQueryObject;
import com.baidu.ssh.vo.ChartVO;
import com.baidu.ssh.vo.OrderGroupByType;
import com.baidu.ssh.vo.SaleChartVO;
import com.baidu.ssh.vo.SaleGroupByType;

/**
 * @ClassName ChartDAOImpl
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年2月12日 下午7:46:02
 * @version 1.0.0
 */
public class ChartDAOImpl implements IChartDAO {

	@Setter
	private SessionFactory sessionFactory;

	/**
	 *  订货报表查询
	 */
	@SuppressWarnings("unchecked")
	public List<ChartVO> queryCharts(ChartQueryObject qo) {
		// 从qo对象中得到分组信息(employee,supplier..),并转成枚举类型
		OrderGroupByType type = OrderGroupByType.valueOf(qo.getGroupType()
				.toUpperCase());

		Session session = sessionFactory.getCurrentSession();
		StringBuilder hql = new StringBuilder(80);
		hql.append("select new com.baidu.ssh.vo.ChartVO("
				+ type.getGroupValue()
				+ ",sum(obj.number),sum(obj.amount)) from OrderBillItem obj");
		hql.append(qo.getQuery());
		hql.append(" group by " + type.getGroupBy());
		Query query = session.createQuery(hql.toString());
		// 设置占位符参数
		for (int index = 0; index < qo.getParameters().size(); index++) {
			query.setParameter(index, qo.getParameters().get(index));
		}
		return query.list();

	}

	/**
	 *   销售账表查询
	 */
	public List<SaleChartVO> queryCharts(SaleChartQueryObject qo) {
		// 从qo对象中得到分组信息(employee,product..),并转成枚举类型
		SaleGroupByType type = SaleGroupByType.valueOf(qo.getGroupType()
				.toUpperCase());

		Session session = sessionFactory.getCurrentSession();
		StringBuilder hql = new StringBuilder(80);
		hql.append("select new com.baidu.ssh.vo.SaleChartVO("
				+ type.getGroupValue()
				+ ",sum(obj.number),sum(obj.saleAmount),sum(obj.saleAmount-obj.costAmount)) from SaleAccount obj");
		hql.append(qo.getQuery());
		hql.append(" group by " + type.getGroupBy());
		Query query = session.createQuery(hql.toString());
		// 设置占位符参数
		for (int index = 0; index < qo.getParameters().size(); index++) {
			query.setParameter(index, qo.getParameters().get(index));
		}
		return query.list();
	}

}
