package com.baidu.ssh.service.impl;

import java.util.List;

import lombok.Setter;

import com.baidu.ssh.dao.IDepartmentDAO;
import com.baidu.ssh.domain.Department;
import com.baidu.ssh.query.EmployeeQueryObject;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.service.IDepartmentService;

/**
 * @ClassName DepartmentServiceImpl
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年12月28日 上午2:53:37
 * @version 1.0.0
 */
public class DepartmentServiceImpl implements IDepartmentService {
	@Setter
	private IDepartmentDAO departmentDAO;

	public void save(Department d) {
		departmentDAO.save(d);

	}

	public void update(Department d) {
		departmentDAO.update(d);

	}

	public void delete(Long id) {
		departmentDAO.delete(id);

	}

	public Department get(Long id) {

		return departmentDAO.get(id);
	}

	public List<Department> list() {

		return departmentDAO.list();
	}

	public PageResult query(EmployeeQueryObject qo) {

		return departmentDAO.query(qo);
	}

}
