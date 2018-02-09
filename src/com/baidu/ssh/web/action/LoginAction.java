package com.baidu.ssh.web.action;

import lombok.Setter;

import com.baidu.ssh.service.IEmployeeService;

/**
 * @ClassName loginAction
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年1月12日 上午10:46:39
 * @version 1.0.0
 */
public class LoginAction extends BaseAction {
	@Setter
	private IEmployeeService employeeService;
	@Setter
	private String username;
	@Setter
	private String password;

	// 登录检查
	public String execute() {
		try {
			employeeService.checkLogin(username, password);
		} catch (RuntimeException e) {
			super.addActionError(e.getMessage());
			return "login";
		}
		return "success";

	}
}
