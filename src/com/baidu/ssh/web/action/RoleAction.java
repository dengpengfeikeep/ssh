package com.baidu.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com.baidu.ssh.domain.Permission;
import com.baidu.ssh.domain.Role;
import com.baidu.ssh.query.EmployeeQueryObject;
import com.baidu.ssh.service.IPermissionService;
import com.baidu.ssh.service.IRoleService;
import com.baidu.ssh.service.ISystemMenuService;
import com.baidu.ssh.util.RequiredPermission;

/**
 * @ClassName RoleAction
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年12月30日 上午9:58:06
 * @version 1.0.0
 */
public class RoleAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Setter
	private IRoleService roleService;
	@Setter
	private IPermissionService permissionService;
	@Setter
	private ISystemMenuService systemMenuService;

	@Getter
	private Role role = new Role();
	@Getter
	private EmployeeQueryObject qo = new EmployeeQueryObject();
	@Getter
	private Permission permission = new Permission();

	// 列表界面
	public String execute() throws Exception {

		putContext("pageResult", roleService.query(qo));
		return "list";
	}

	// 删除界面
	@RequiredPermission("员工删除")
	public String delete() throws Exception {
		if (role.getId() != null) {
			roleService.delete(role.getId());
		}
		return "success";
	}

	// 编辑界面
	public String input() throws Exception {
		putContext("permissions", permissionService.list());
		putContext("menus", systemMenuService.listChildren());
		if (role.getId() != null) {
			role = roleService.get(role.getId());
		}
		return "input";
	}

	// 保存与更新
	public String saveOrUpdate() throws Exception {
		if (role.getId() == null) {
			roleService.save(role);
		} else {
			roleService.update(role);
		}
		return SUCCESS;
	}

	public void prepareSaveOrUpdate() throws Exception {
		if (role.getId() != null) {
			role = roleService.get(role.getId());
		}
		// 清除第一次传递的数据
		role.getPermissions().clear();
		role.getMenus().clear();
	}

}
