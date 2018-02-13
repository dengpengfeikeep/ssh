package com.baidu.ssh.vo;

import lombok.Getter;

/**
 * @ClassName OrderGroupByType
 * @Description 每一个枚举常量封装了当前分组的信息
 * @author Administrator
 * @Date 2018年2月13日 下午1:36:05
 * @version 1.0.0
 */
@Getter
public enum OrderGroupByType {
	EMPLOYEE("订货人员", "obj.bill.inputUser", "obj.bill.inputUser.name"), //
	PRODUCT("货品名称", "obj.product", "obj.product.name"), //
	BRAND("货品品牌", "obj.product.brand", "obj.product.brand.name"), //
	SUPPLIER("供应商名称", "obj.bill.supplier", "obj.bill.supplier.name"), //
	MONTH("订货日期(月)", "date_format(obj.bill.vdate,'%Y-%m')",
			"date_format(obj.bill.vdate,'%Y-%m')"), //
	DAY("订货日期(日)", "date_format(obj.bill.vdate,'%Y-%m-%d')",
			"date_format(obj.bill.vdate,'%Y-%m-%d')");

	private OrderGroupByType(String groupType, String groupBy, String groupValue) {
		this.groupType = groupType;
		this.groupBy = groupBy;
		this.groupValue = groupValue;
	}

	private String groupType;// 分组类型(订货人员)
	private String groupBy;// 分组依据(obj.bill.inputUser)
	private String groupValue;// 分组名称(obj.bill.inputUser.name)

}
