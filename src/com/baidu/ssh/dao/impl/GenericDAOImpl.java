package com.baidu.ssh.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import lombok.Setter;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.baidu.ssh.dao.IGenericDAO;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.QueryObject;

/**
 * @ClassName GenericDAOImpl
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年1月11日 下午12:06:08
 * @version 1.0.0
 */
public class GenericDAOImpl<T> implements IGenericDAO<T> {

	private Class<T> targetType;

	@SuppressWarnings("all")
	public GenericDAOImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		targetType = (Class<T>) type.getActualTypeArguments()[0];

	}

	@Setter
	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(T obj) {
		Session session = sessionFactory.getCurrentSession();
		session.save(obj);

	}

	public void update(T obj) {
		Session session = sessionFactory.getCurrentSession();
		session.update(obj);

	}

	public void delete(Long id) {
		Session session = sessionFactory.getCurrentSession();
		T obj = (T) session.get(targetType, id);
		session.delete(obj);

	}

	public T get(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (T) session.get(targetType, id);

	}

	public List<T> list() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(targetType).list();

	}

	@SuppressWarnings("unchecked")
	public PageResult query(QueryObject qo) {

		int currentPage = qo.getCurrentPage();
		int pageSize = qo.getPageSize();

		// 查询结果集总数
		Session session = sessionFactory.getCurrentSession();
		String countHql = "select count(obj) from "
				+ targetType.getSimpleName() + " obj" + qo.getQuery();
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
		String hql = "select obj from " + targetType.getSimpleName() + " obj"
				+ qo.getQuery();
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

	public void batchDelete(List<Long> id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "delete from " + targetType.getSimpleName()
				+ " obj where obj.id in (:ids)";
		Query query = session.createQuery(hql);
		query.setParameterList("ids", id);
		query.executeUpdate();

	}

}
