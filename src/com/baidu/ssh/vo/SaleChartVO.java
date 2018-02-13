package com.baidu.ssh.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName SaleChartVO
 * @Description 封装了销售账表需要查询出来的信息(分组条件,订货数量,订货金额)
 * @author Administrator
 * @Date 2018年2月12日 下午7:38:06
 * @version 1.0.0
 */
@Getter
@Setter
public class SaleChartVO {
	private String groupType;// 分组条件
	private BigDecimal totalNumber;// 订货数量
	private BigDecimal totalAmount;// 订货金额
	private BigDecimal grossProfit;// 毛利润

	public SaleChartVO(String groupType, BigDecimal totalNumber,
			BigDecimal totalAmount, BigDecimal grossProfit) {
		super();
		this.groupType = groupType;
		this.totalNumber = totalNumber;
		this.totalAmount = totalAmount;
		this.grossProfit = grossProfit;
	}

	public String toString() {
		return "SaleChartVO [groupType=" + groupType + ", totalNumber="
				+ totalNumber + ", totalAmount=" + totalAmount
				+ ", grossProfit=" + grossProfit + "]";
	}

}
