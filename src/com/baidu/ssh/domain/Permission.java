package com.baidu.ssh.domain;

import lombok.Data;

/**
 * @ClassName Permission
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年1月11日 下午2:15:39
 * @version 1.0.0
 */

@Data
public class Permission extends BaseDomain {

	private String name;
	private String expression;
}
