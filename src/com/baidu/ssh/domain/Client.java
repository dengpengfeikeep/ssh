package com.baidu.ssh.domain;

import lombok.Data;

/**
 * @ClassName Client
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年2月9日 上午1:54:18
 * @version 1.0.0
 */
@Data
public class Client extends BaseDomain {
	private String name;
	private String sn;
	private String phone;

}
