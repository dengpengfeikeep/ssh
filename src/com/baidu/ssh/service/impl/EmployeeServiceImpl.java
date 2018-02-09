package com.baidu.ssh.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Setter;

import com.baidu.ssh.dao.IEmployeeDAO;
import com.baidu.ssh.domain.Employee;
import com.baidu.ssh.domain.Permission;
import com.baidu.ssh.domain.Role;
import com.baidu.ssh.query.EmployeeQueryObject;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.service.IEmployeeService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName EmployeeServiceImpl
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年12月28日 上午2:53:37
 * @version 1.0.0
 */
public class EmployeeServiceImpl implements IEmployeeService {
	@Setter
	private IEmployeeDAO employeeDAO;

	public void save(Employee e) {
		employeeDAO.save(e);

	}

	public void update(Employee e) {
		employeeDAO.update(e);

	}

	public void delete(Long id) {
		employeeDAO.delete(id);

	}

	public Employee get(Long id) {

		return employeeDAO.get(id);
	}

	public List<Employee> list() {

		return employeeDAO.list();
	}

	public PageResult query(EmployeeQueryObject qo) {

		return employeeDAO.query(qo);
	}

	// 登录检查
	public void checkLogin(String name, String password) {

		Employee employee = employeeDAO.checkLogin(name, password);
		if (employee == null) {
			throw new RuntimeException("账户或者密码错误");
		}
		// 把当前用户存储在session中
		ActionContext.getContext().getSession()
				.put("user_in_session", employee);
		Set<String> expressions = new HashSet<>();
		// 查询当前用户的权限
		List<Role> roles = employee.getRoles();
		for (Role role : roles) {
			List<Permission> permissions = role.getPermissions();
			for (Permission permission : permissions) {
				expressions.add(permission.getExpression());
			}
		}
		ActionContext.getContext().getSession()
				.put("permissions_in_session", expressions);
	}

	public void batchDelete(List<Long> id) {
		employeeDAO.batchDelete(id);

	}
}
