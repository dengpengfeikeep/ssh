package com.baidu.ssh.service;

import java.util.List;

import com.baidu.ssh.domain.Department;
import com.baidu.ssh.query.EmployeeQueryObject;
import com.baidu.ssh.query.PageResult;

/**
 * @ClassName IDepartmentDAO
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年12月28日 上午1:59:17
 * @version 1.0.0
 */
public interface IDepartmentService {
	void save(Department d);

	void update(Department d);

	void delete(Long id);

	Department get(Long id);

	List<Department> list();

	PageResult query(EmployeeQueryObject qo);

}
