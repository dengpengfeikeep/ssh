package com.baidu.ssh.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.baidu.ssh.dao.ISystemMenuDAO;
import com.baidu.ssh.domain.Role;
import com.baidu.ssh.domain.SystemMenu;

public class SystemMenuDAOImpl extends GenericDAOImpl<SystemMenu> implements
		ISystemMenuDAO {
	@SuppressWarnings("rawtypes")
	public void delete(Long id) {
		// 先查询有没有人的老爸是这个吊毛,如果有,显示这个吊毛正被应用,如果没有,把这个吊毛查出来,删除
		Session session = sessionFactory.getCurrentSession();
		String sql = "select * from systemmenu where parent_id = ?";
		SQLQuery query = session.createSQLQuery(sql);
		query.setLong(0, id);
		List list = query.list();
		if (list.size() > 0) {
			throw new RuntimeException("该菜单对象正被引用着!");
		} else {
			Object object = session.get(SystemMenu.class, id);
			session.delete(object);
		}
	}

	public List<SystemMenu> listChildren() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select obj from SystemMenu obj where obj.parent is not null";
		Query query = session.createQuery(hql);
		return query.list();
	}

	/**
	 * @Description 根据id查询出自己的子菜单
	 * @param id
	 * @return
	 */
	public List<SystemMenu> loadMenusById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select obj from SystemMenu obj where obj.parent.id =?";
		Query query = session.createQuery(hql);
		query.setLong(0, id);
		return query.list();
	}

	/**
	 * 根据id查询出自己的子菜单和角色查出自己对应的菜单
	 */
	public List<SystemMenu> loadMenusByIdAndRole(Long id, List<Role> roles) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select m from Role r join r.menus m where m.parent.id = :parentId and r in (:roles)";
		Query query = session.createQuery(hql);
		// 设置占位符参数
		query.setParameter("parentId", id);
		// 注意:这里要用list参数设置
		query.setParameterList("roles", roles);
		return query.list();
	}
}
