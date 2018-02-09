package com.baidu.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com.baidu.ssh.domain.Permission;
import com.baidu.ssh.query.EmployeeQueryObject;
import com.baidu.ssh.service.IPermissionService;
import com.baidu.ssh.util.RequiredPermission;

/**
 * @ClassName PermissionAction
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年12月30日 上午9:58:06
 * @version 1.0.0
 */
public class PermissionAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Setter
	private IPermissionService permissionService;

	@Getter
	private Permission permission = new Permission();

	@Getter
	private EmployeeQueryObject qo = new EmployeeQueryObject();

	// 列表界面
	public String execute() throws Exception {

		putContext("pageResult", permissionService.query(qo));
		return "list";
	}

	// 加载
	public String reload() throws Exception {

		permissionService.reload();
		return NONE;
	}

	// 删除界面
	@RequiredPermission("员工删除")
	public String delete() throws Exception {
		if (permission.getId() != null) {
			permissionService.delete(permission.getId());
		}
		return NONE;
	}

}
