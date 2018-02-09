package com.baidu.ssh.service.impl;

import java.util.List;

import lombok.Setter;

import com.baidu.ssh.dao.IRoleDAO;
import com.baidu.ssh.domain.Role;
import com.baidu.ssh.query.EmployeeQueryObject;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.service.IRoleService;

/**
 * @ClassName RoleServiceImpl
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年12月28日 上午2:53:37
 * @version 1.0.0
 */
public class RoleServiceImpl implements IRoleService {
	@Setter
	private IRoleDAO RoleDAO;

	public void save(Role role) {
		RoleDAO.save(role);

	}

	public void update(Role role) {
		RoleDAO.update(role);

	}

	public void delete(Long id) {
		RoleDAO.delete(id);

	}

	public Role get(Long id) {

		return RoleDAO.get(id);
	}

	public List<Role> list() {

		return RoleDAO.list();
	}

	public PageResult query(EmployeeQueryObject qo) {

		return RoleDAO.query(qo);
	}

}
