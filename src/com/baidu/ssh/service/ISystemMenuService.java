package com.baidu.ssh.service;

import java.util.List;

import com.baidu.ssh.domain.SystemMenu;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.SystemMenuQueryObject;
import com.baidu.ssh.vo.SystemMenuVO;

public interface ISystemMenuService {
	void save(SystemMenu obj);

	void update(SystemMenu obj);

	void delete(Long id);

	SystemMenu get(Long id);

	List<SystemMenu> list();

	PageResult query(SystemMenuQueryObject obj);

	/**
	 * @Description 查询当前菜单的父菜单
	 * @param parentId
	 * @return
	 */
	List<SystemMenuVO> listMenus(Long parentId);

	/**
	 * @Description 查询所有非根菜单的菜单
	 * @return
	 */
	List<SystemMenu> listChildren();

	/**
	 * @Description 根据id查询出自己的子菜单
	 * @param id
	 */
	List<SystemMenu> loadMenusById(Long id);

}
