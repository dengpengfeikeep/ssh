package com.baidu.ssh.service;

import java.util.List;

import com.baidu.ssh.domain.Role;
import com.baidu.ssh.query.EmployeeQueryObject;
import com.baidu.ssh.query.PageResult;

/**
 * @ClassName IRoleDAO
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年12月28日 上午1:59:17
 * @version 1.0.0
 */
public interface IRoleService {
	void save(Role role);

	void update(Role role);

	void delete(Long id);

	Role get(Long id);

	List<Role> list();

	PageResult query(EmployeeQueryObject qo);

}
