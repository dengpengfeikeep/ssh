package com.baidu.ssh.domain;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @ClassName OrderBillItem
 * @Description 采购订单明细
 * @author Administrator
 * @Date 2018年1月26日 下午4:21:33
 * @version 1.0.0
 */
@Data
public class OrderBillItem extends BaseDomain {
	private BigDecimal costPrice;// 采购成本价
	private BigDecimal number;// 采购数量
	private BigDecimal amount;// 采购小计
	private String remark;// 备注

	private Product product;// 货品
	private OrderBill bill;// 订单
}
