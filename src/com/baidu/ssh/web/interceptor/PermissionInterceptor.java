package com.baidu.ssh.web.interceptor;

import java.lang.reflect.Method;
import java.util.Set;

import com.baidu.ssh.util.CurrentEmployee;
import com.baidu.ssh.util.PermissionUtil;
import com.baidu.ssh.util.RequiredPermission;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @ClassName PermissionInterceptor
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年1月12日 下午1:44:17
 * @version 1.0.0
 */
public class PermissionInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		// 如果是超级管理员
		Boolean admin = CurrentEmployee.getCurrentEmployee().getAdmin();
		if (admin) {
			return invocation.invoke();
		}

		// 判断该方法上有没有注解
		String methodName = invocation.getProxy().getMethod();
		Method method = invocation.getProxy().getAction().getClass()
				.getDeclaredMethod(methodName);
		RequiredPermission annotation = method
				.getAnnotation(RequiredPermission.class);
		// 如果该方法没有注解.放行
		if (annotation == null) {
			return invocation.invoke();
		}
		// 有,拿到表达式,跟session中的对比
		String expression = PermissionUtil.builExpression(method);
		Set<String> expressions = (Set<String>) invocation
				.getInvocationContext().getSession()
				.get("permissions_in_session");
		if (expressions.contains(expression)) {
			return invocation.invoke();
		}
		return "nopermission";
	}

}
