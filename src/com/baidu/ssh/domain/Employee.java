package com.baidu.ssh.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @ClassName Employee
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年12月28日 上午1:32:29
 * @version 1.0.0
 */
@Data
public class Employee extends BaseDomain {
	private String name;
	private Integer age;
	private String password;
	private String email;
	private Boolean admin;
	private Department dept;
	private List<Role> roles = new ArrayList<>();

	public String getRoleName() {
		if (this.roles.size() == 0) {
			return "[暂无角色]";
		}
		if (this.admin) {
			return "[admin]";
		}

		StringBuilder sb = new StringBuilder(40);
		sb.append("[");
		for (Role role : roles) {
			sb.append(role.getName());
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();

	}
}
