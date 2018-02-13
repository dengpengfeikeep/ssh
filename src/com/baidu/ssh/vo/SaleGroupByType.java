package com.baidu.ssh.vo;

import lombok.Getter;

/**
 * @ClassName SaleGroupByType
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年2月13日 下午9:19:27
 * @version 1.0.0
 */
@Getter
public enum SaleGroupByType {
	EMPLOYEE("销售人员", "obj.saleman", "obj.saleman.name"), //
	PRODUCT("货品名称", "obj.product", "obj.product.name"), //
	BRAND("货品品牌", "obj.product.brand", "obj.product.brand.name"), //
	SUPPLIER("客户名称", "obj.client", "obj.client.name"), //
	MONTH("销售日期(月)", "date_format(obj.vdate,'%Y-%m')",
			"date_format(obj.vdate,'%Y-%m')"), //
	DAY("销售日期(日)", "date_format(obj.vdate,'%Y-%m-%d')",
			"date_format(obj.vdate,'%Y-%m-%d')");

	private SaleGroupByType(String groupType, String groupBy, String groupValue) {
		this.groupType = groupType;
		this.groupBy = groupBy;
		this.groupValue = groupValue;
	}

	private String groupType;// 分组类型(订货人员)
	private String groupBy;// 分组依据(obj.saleman)
	private String groupValue;// 分组名称(obj.saleman.name)

}
