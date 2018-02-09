package com.baidu.generator;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import com.baidu.ssh.domain.BaseDomain;
import com.baidu.ssh.domain.Employee;

/**
 * @ClassName ClassInfo
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年1月17日 下午1:29:19
 * @version 1.0.0
 */
@Getter
public class ClassInfo {
	private String basePkg;// 基础包名
	private String className;// 类名
	private String objectName;// 对象名
	List<String> props = new ArrayList<>();// 存放属性

	public ClassInfo(Class<?> clazz) throws Exception {
		className = clazz.getSimpleName();
		basePkg = clazz.getPackage().getName()
				.substring(0, clazz.getPackage().getName().lastIndexOf("."));
		objectName = clazz.getSimpleName().substring(0, 1).toLowerCase()
				+ clazz.getSimpleName().substring(1);
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz, BaseDomain.class);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor prop : pds) {
			props.add(prop.getName());
		}
	}

	public static void main(String[] args) throws Exception {
		ClassInfo classInfo = new ClassInfo(Employee.class);
		System.out.println(classInfo.basePkg);
		System.out.println(classInfo.objectName);
		System.out.println(classInfo.props);
	}

}
