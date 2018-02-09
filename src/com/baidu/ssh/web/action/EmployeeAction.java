package com.baidu.ssh.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;

import lombok.Getter;
import lombok.Setter;

import com.baidu.ssh.domain.Department;
import com.baidu.ssh.domain.Employee;
import com.baidu.ssh.query.EmployeeQueryObject;
import com.baidu.ssh.service.IDepartmentService;
import com.baidu.ssh.service.IEmployeeService;
import com.baidu.ssh.service.IRoleService;
import com.baidu.ssh.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * @ClassName EmployeeAction
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年12月30日 上午9:58:06
 * @version 1.0.0
 */
public class EmployeeAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Setter
	private IEmployeeService employeeService;
	@Setter
	private IDepartmentService departmentService;
	@Setter
	private IRoleService roleService;

	@Getter
	private Employee employee = new Employee();
	@Getter
	private Department department = new Department();
	@Getter
	private EmployeeQueryObject qo = new EmployeeQueryObject();
	@Getter
	private List<Long> ids = new ArrayList<>();

	// 列表界面
	@InputConfig(methodName = "input")
	public String execute() throws Exception {
		try {
			putContext("pageResult", employeeService.query(qo));
			putContext("depts", departmentService.list());

		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	// 批量删除
	public String batchDelete() throws Exception {
		if (ids.size() >= 0) {
			employeeService.batchDelete(ids);
		}
		return NONE;

	}

	// 删除界面
	@RequiredPermission("员工删除")
	public String delete() throws Exception {
		if (employee.getId() != null) {
			employeeService.delete(employee.getId());
			putContextText("删除成功!");
		}
		return NONE;
	}

	// 编辑界面
	public String input() throws Exception {
		putContext("depts", departmentService.list());
		putContext("roles", roleService.list());
		if (employee.getId() != null) {
			employee = employeeService.get(employee.getId());
		}
		return "input";
	}

	// 保存与更新
	public String saveOrUpdate() throws Exception {
		try {
			if (employee.getId() == null) {
				employeeService.save(employee);
				addActionMessage("保存成功!");
			} else {
				employeeService.update(employee);
				addActionMessage("更改成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	// 解决密码丢失问题
	public void prepareSaveOrUpdate() throws Exception {
		if (employee.getId() != null) {
			employee = employeeService.get(employee.getId());
		}
		employee.getRoles().clear();
	}

}
