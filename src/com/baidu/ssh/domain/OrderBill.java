package com.baidu.ssh.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @ClassName OrderBill
 * @Description 订单
 * @author Administrator
 * @Date 2018年1月26日 下午4:03:40
 * @version 1.0.0
 */
@Data
public class OrderBill extends BaseDomain {

	public static final Integer NORMAL = 0;// 未审核
	public static final Integer CHECK = 1;// 已审核

	private String sn;// 订单编码
	private Date vdate; // 订单日期
	private Integer status = OrderBill.NORMAL;// 订单状态,默认是未审核
	private BigDecimal totalAmount;// 订单金额
	private BigDecimal totalNumber;// 订单数量
	private Date auditTime;// 订单审核时间
	private Date inputTime;// 制单时间
	private Employee inputUser;// 制单人
	private Employee auditor;// 审核人
	private Supplier supplier;// 供应商

	private List<OrderBillItem> items = new ArrayList<>();// 订单明细

}
