package com.baidu.ssh.service;

import java.util.List;

import com.baidu.ssh.domain.Permission;
import com.baidu.ssh.query.EmployeeQueryObject;
import com.baidu.ssh.query.PageResult;

/**
 * @ClassName IPermissionDAO
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年12月28日 上午1:59:17
 * @version 1.0.0
 */
public interface IPermissionService {
	void save(Permission p);

	void update(Permission p);

	void delete(Long id);

	Permission get(Long id);

	List<Permission> list();

	PageResult query(EmployeeQueryObject qo);

	// 加载权限
	void reload();

}
