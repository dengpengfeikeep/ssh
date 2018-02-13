package com.baidu.ssh.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * @ClassName SaleAccount
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年2月13日 下午8:23:02
 * @version 1.0.0
 */
@Data
public class SaleAccount extends BaseDomain {
	private Date vdate;// 业务时间
	private BigDecimal number;// 销售数量
	private BigDecimal costPrice;// 成本价(库存价格)
	private BigDecimal costAmount;// 成本总金额
	private BigDecimal salePrice;// 销售价
	private BigDecimal saleAmount;// 销售总金额

	private Product product;// 哪一个货品
	private Client client;// 客户
	private Employee saleman;// 谁销售的

}
