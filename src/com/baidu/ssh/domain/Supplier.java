package com.baidu.ssh.domain;

import lombok.Data;

/**
 * @ClassName Supplier
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年1月25日 下午2:42:14
 * @version 1.0.0
 */
@Data
public class Supplier extends BaseDomain {
	private String name;
	private String address;
	private String phone;

}
