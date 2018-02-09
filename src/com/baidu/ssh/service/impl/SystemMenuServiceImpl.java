package com.baidu.ssh.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Setter;

import com.baidu.ssh.dao.ISystemMenuDAO;
import com.baidu.ssh.domain.Employee;
import com.baidu.ssh.domain.SystemMenu;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.SystemMenuQueryObject;
import com.baidu.ssh.service.ISystemMenuService;
import com.baidu.ssh.util.CurrentEmployee;
import com.baidu.ssh.vo.SystemMenuVO;

/**
 * @ClassName SystemMenuServiceImpl
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年1月24日 下午9:20:11
 * @version 1.0.0
 */
public class SystemMenuServiceImpl implements ISystemMenuService {
	@Setter
	private ISystemMenuDAO SystemMenuDAO;

	public void save(SystemMenu obj) {
		SystemMenuDAO.save(obj);

	}

	public void update(SystemMenu obj) {
		SystemMenuDAO.update(obj);

	}

	public void delete(Long id) {
		try {
			SystemMenuDAO.delete(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("该菜单对象正被引用着!");
		}
	}

	public SystemMenu get(Long id) {

		return SystemMenuDAO.get(id);
	}

	public List<SystemMenu> list() {

		return SystemMenuDAO.list();
	}

	public PageResult query(SystemMenuQueryObject qo) {

		return SystemMenuDAO.query(qo);
	}

	/** 
	 * @Description:列出所有的父菜单
	 */
	public List<SystemMenuVO> listMenus(Long parentId) {
		List<SystemMenuVO> menus = new ArrayList<>();
		SystemMenu currentMenu = SystemMenuDAO.get(parentId);
		listParents(menus, currentMenu);
		Collections.reverse(menus);
		return menus;
	}

	/**
	 * @Description 递归出所有的父菜单
	 */
	private void listParents(List<SystemMenuVO> menus, SystemMenu currentMenu) {
		if (currentMenu != null) {
			SystemMenuVO vo = new SystemMenuVO();
			vo.setId(currentMenu.getId());
			vo.setName(currentMenu.getName());
			menus.add(vo);
			listParents(menus, currentMenu.getParent());
		}

	}

	public List<SystemMenu> listChildren() {
		return SystemMenuDAO.listChildren();
	}

	/**
	 * @Description 根据id查询出自己的子菜单和角色查出自己对应的菜单
	 * @param id
	 */
	public List<SystemMenu> loadMenusById(Long id) {
		Employee currentEmployee = CurrentEmployee.getCurrentEmployee();
		Boolean admin = currentEmployee.getAdmin();
		if (admin) {
			// 如果当前用户是超级管理员,加载所有菜单
			return SystemMenuDAO.loadMenusById(id);
		}
		// 当前用户不是超级管理员,根据角色查询应该看到的菜单
		return SystemMenuDAO.loadMenusByIdAndRole(id,
				currentEmployee.getRoles());
	}
}
