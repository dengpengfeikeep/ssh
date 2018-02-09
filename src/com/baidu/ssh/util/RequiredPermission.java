package com.baidu.ssh.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName RequiredPermission
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年1月10日 上午8:39:36
 * @version 1.0.0
 */
@Target(ElementType.METHOD)
// 贴在方法上
@Retention(RetentionPolicy.RUNTIME)
// 存活在jvm中
public @interface RequiredPermission {
	String value();// 表示权限的名称

}
