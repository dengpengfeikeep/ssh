package com.baidu.ssh.domain;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @ClassName StockOutcomeBillItem
 * @Description  销售出库单明细
 * @author Administrator
 * @Date 2018年1月26日 下午4:21:33
 * @version 1.0.0
 */
@Data
public class StockOutcomeBillItem extends BaseDomain {
	private BigDecimal salePrice;// 销售价格
	private BigDecimal number;// 销售数量
	private BigDecimal amount;// 销售小计
	private String remark;// 备注

	private Product product;// 货品
	private StockOutcomeBill bill;// 订单
}
