package com.baidu.ssh.dao;

import java.util.List;

import com.baidu.ssh.domain.Role;
import com.baidu.ssh.domain.SystemMenu;

public interface ISystemMenuDAO extends IGenericDAO<SystemMenu> {

	/**
	 * @Description 查询所有非根菜单的菜单
	 * @return
	 */
	List<SystemMenu> listChildren();

	/**
	 * @Description 根据id查询出自己的子菜单
	 * @param id
	 * @return
	 */
	List<SystemMenu> loadMenusById(Long id);

	/**
	 * @Description 根据id查询出自己的子菜单和角色查出自己对应的菜单
	 * @param id
	 * @param roles
	 * @return
	 */
	List<SystemMenu> loadMenusByIdAndRole(Long id, List<Role> roles);

}
