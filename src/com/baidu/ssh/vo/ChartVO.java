package com.baidu.ssh.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ChartVO
 * @Description 封装了需要查询出来的信息(分组条件,订货数量,订货金额)
 * @author Administrator
 * @Date 2018年2月12日 下午7:38:06
 * @version 1.0.0
 */
@Getter
@Setter
public class ChartVO {
	private String groupType;// 分组条件
	private BigDecimal totalNumber;// 订货数量
	private BigDecimal totalAmount;// 订货金额

	public ChartVO(String groupType, BigDecimal totalNumber,
			BigDecimal totalAmount) {
		this.groupType = groupType;
		this.totalNumber = totalNumber;
		this.totalAmount = totalAmount;
	}

	public String toString() {
		return "ChartVO [groupType=" + groupType + ", totalNumber="
				+ totalNumber + ", totalAmount=" + totalAmount + "]";
	}

}
