package com.baidu.ssh.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.baidu.ssh.dao.IEmployeeDAO;
import com.baidu.ssh.domain.Employee;
import com.baidu.ssh.query.EmployeeQueryObject;
import com.baidu.ssh.query.PageResult;

/**
 * @ClassName EmployeeImpl
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年12月28日 上午2:00:39
 * @version 1.0.0
 */
public class EmployeeDAOImpl extends GenericDAOImpl<Employee> implements
		IEmployeeDAO {

	public Employee checkLogin(String name, String password) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select obj from Employee obj where obj.name = ? and obj.password = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, name);
		query.setParameter(1, password);
		Employee employee = (Employee) query.uniqueResult();
		return employee;
	}

	@SuppressWarnings("unchecked")
	public PageResult query(EmployeeQueryObject qo) {

		int currentPage = qo.getCurrentPage();
		int pageSize = qo.getPageSize();

		// 查询结果集总数
		Session session = sessionFactory.getCurrentSession();
		String countHql = "select count(obj) from Employee obj" + qo.getQuery();
		Query query = session.createQuery(countHql);
		for (int index = 0; index < qo.getParameters().size(); index++) {
			query.setParameter(index, qo.getParameters().get(index));
		}
		int totalCount = ((Long) query.uniqueResult()).intValue();

		// ........................
		// 查询结果集
		if (totalCount == 0) {
			return new PageResult(0, Collections.EMPTY_LIST, 1, pageSize);
		}
		String hql = "select obj from Employee obj" + qo.getQuery();
		query = session.createQuery(hql);
		for (int index = 0; index < qo.getParameters().size(); index++) {
			query.setParameter(index, qo.getParameters().get(index));
		}
		// 分页
		if (currentPage > 0 && pageSize > 0) {
			query.setFirstResult((currentPage - 1) * pageSize);
			query.setMaxResults(pageSize);
		}
		List result = query.list();
		return new PageResult(totalCount, result, currentPage, pageSize);
	}

}
