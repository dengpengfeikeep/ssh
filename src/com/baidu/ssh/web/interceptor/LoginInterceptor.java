package com.baidu.ssh.web.interceptor;

import com.baidu.ssh.domain.Employee;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @ClassName LoginInterceptor
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年1月12日 下午1:04:38
 * @version 1.0.0
 */
public class LoginInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		// 判断seesion中是否有当前用户
		Employee employee = (Employee) invocation.getInvocationContext()
				.getSession().get("user_in_session");
		if (employee == null) {
			return "login";
		}
		return invocation.invoke();
	}

}
