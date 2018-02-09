package com.baidu.ssh.domain;

import lombok.Data;

/**
 * @ClassName Depot
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年2月4日 下午8:59:47
 * @version 1.0.0
 */
@Data
public class Depot extends BaseDomain {
	private String name;
	private String location;
}
