package com.baidu.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com.baidu.ssh.domain.Department;
import com.baidu.ssh.query.EmployeeQueryObject;
import com.baidu.ssh.service.IDepartmentService;
import com.baidu.ssh.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * @ClassName DepartmentAction
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年12月30日 上午9:58:06
 * @version 1.0.0
 */
public class DepartmentAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Setter
	private IDepartmentService departmentService;

	@Getter
	private Department department = new Department();

	@Getter
	private EmployeeQueryObject qo = new EmployeeQueryObject();

	// 列表界面
	@InputConfig(methodName = "put")
	public String execute() throws Exception {
		try {
			putContext("pageResult", departmentService.query(qo));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	// 删除界面
	@RequiredPermission("部门删除")
	public String delete() throws Exception {
		if (department.getId() != null) {
			departmentService.delete(department.getId());
		}
		return NONE;
	}

	// 编辑界面
	public String input() throws Exception {
		if (department.getId() != null) {
			department = departmentService.get(department.getId());
		}
		return "input";
	}

	// 保存与更新
	public String saveOrUpdate() throws Exception {
		try {
			if (department.getId() == null) {
				departmentService.save(department);
				addActionMessage("保存成功!");
			} else {
				departmentService.update(department);
				addActionMessage("修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

}
