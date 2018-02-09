package com.baidu.ssh.dao;

import com.baidu.ssh.domain.Employee;

/**
 * @ClassName IEmployeeDAO
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年12月28日 上午1:59:17
 * @version 1.0.0
 */
public interface IEmployeeDAO extends IGenericDAO<Employee> {

	Employee checkLogin(String name, String password);

}
