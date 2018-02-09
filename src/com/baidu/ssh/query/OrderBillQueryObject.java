package com.baidu.ssh.query;

import java.util.Date;

import com.baidu.ssh.util.DateUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderBillQueryObject extends QueryObject {

	private Date beginDate;// 业务开始时间
	private Date endDate;// 业务结束时间
	private Long supplierId = -1l;// 供应商id
	private Integer status = -1;// 订单状态

	public void customizedQuery() {
		if (supplierId > 0) {
			addQuery("obj.supplier.id = ?", supplierId);
		}
		if (status >= 0) {
			addQuery("obj.status = ?", status);
		}
		if (beginDate != null) {
			addQuery("obj.vdate >= ?", DateUtil.getBeginDate(beginDate));
		}
		if (endDate != null) {
			addQuery("obj.vdate <= ?", DateUtil.getBeginDate(endDate));
		}
	}
}
