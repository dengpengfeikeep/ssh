package com.baidu.ssh.service.impl;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.baidu.ssh.dao.IPermissionDAO;
import com.baidu.ssh.domain.Permission;
import com.baidu.ssh.query.EmployeeQueryObject;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.service.IPermissionService;
import com.baidu.ssh.util.PermissionUtil;
import com.baidu.ssh.util.RequiredPermission;
import com.baidu.ssh.web.action.BaseAction;

/**
 * @ClassName PermissionServiceImpl
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年12月28日 上午2:53:37
 * @version 1.0.0
 */
public class PermissionServiceImpl implements IPermissionService {
	@Setter
	private IPermissionDAO permissionDAO;
	@Autowired
	private ApplicationContext ctx;

	public void save(Permission p) {
		permissionDAO.save(p);

	}

	public void update(Permission p) {
		permissionDAO.update(p);

	}

	public void delete(Long id) {
		permissionDAO.delete(id);

	}

	public Permission get(Long id) {

		return permissionDAO.get(id);
	}

	public List<Permission> list() {

		return permissionDAO.list();
	}

	public PageResult query(EmployeeQueryObject qo) {

		return permissionDAO.query(qo);
	}

	public void reload() {

		// 0.查询所有数据库中的表达式
		List<Permission> list = permissionDAO.list();
		Set<String> expressions = new HashSet<>();
		for (Permission p : list) {
			expressions.add(p.getExpression());
		}
		// 1.遍历每一个baseAction的子类
		Map<String, BaseAction> map = ctx.getBeansOfType(BaseAction.class);
		Collection<BaseAction> actions = map.values();
		// 2.遍历每一个类中的方法,判断方法上是否有注解,得到该注解对象
		for (BaseAction baseAction : actions) {
			Method[] methods = baseAction.getClass().getDeclaredMethods();
			for (Method method : methods) {
				String exp = PermissionUtil.builExpression(method);
				RequiredPermission annotation = method
						.getAnnotation(RequiredPermission.class);
				if (annotation != null && !expressions.contains(exp)) {
					Permission permission = new Permission();
					permission.setName(annotation.value());
					permission.setExpression(exp);
					permissionDAO.save(permission);
				}
			}
		}
	}

}
