package com.baidu.ssh.service;

import java.util.List;

import com.baidu.ssh.domain.Employee;
import com.baidu.ssh.query.EmployeeQueryObject;
import com.baidu.ssh.query.PageResult;

/**
 * @ClassName IEmployeeDAO
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年12月28日 上午1:59:17
 * @version 1.0.0
 */
public interface IEmployeeService {
	void save(Employee e);

	void update(Employee e);

	void delete(Long id);

	Employee get(Long id);

	List<Employee> list();

	PageResult query(EmployeeQueryObject qo);

	void checkLogin(String name, String password);

	void batchDelete(List<Long> id);

}
