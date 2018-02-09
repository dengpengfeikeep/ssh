package com.baidu.ssh.domain;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @ClassName StockIncomeBillItem
 * @Description  到货入库单明细
 * @author Administrator
 * @Date 2018年1月26日 下午4:21:33
 * @version 1.0.0
 */
@Data
public class StockIncomeBillItem extends BaseDomain {
	private BigDecimal costPrice;// 入库成本价
	private BigDecimal number;// 入库数量
	private BigDecimal amount;// 入库小计
	private String remark;// 备注

	private Product product;// 货品
	private StockIncomeBill bill;// 订单
}
