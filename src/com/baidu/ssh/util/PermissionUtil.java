package com.baidu.ssh.util;

import java.lang.reflect.Method;

import com.baidu.ssh.web.action.EmployeeAction;

/**
 * @ClassName PermissionUtil
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年1月10日 上午9:48:24
 * @version 1.0.0
 */
public class PermissionUtil {

	public static String builExpression(Method m) {
		String methodName = m.getName();
		String className = m.getDeclaringClass().getName();
		return className + ":" + methodName;
	}
}
