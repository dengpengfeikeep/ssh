package com.baidu.ssh.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @ClassName Role
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年1月11日 下午3:59:48
 * @version 1.0.0
 */

@Data
public class Role extends BaseDomain {

	private String name;
	private String sn;
	private List<Permission> permissions = new ArrayList<>();
	private List<SystemMenu> menus = new ArrayList<>();
}
