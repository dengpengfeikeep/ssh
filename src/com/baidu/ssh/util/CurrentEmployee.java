package com.baidu.ssh.util;

import org.apache.struts2.ServletActionContext;

import com.baidu.ssh.domain.Employee;

/**
 * @ClassName CurrentEmployee
 * @Description  根据session查出当前登录员工的信息
 * @author Administrator
 * @Date 2018年1月25日 下午12:42:34
 * @version 1.0.0
 */
public class CurrentEmployee {
	public static Employee getCurrentEmployee() {
		return (Employee) ServletActionContext.getContext().getSession()
				.get("user_in_session");
	}
}
