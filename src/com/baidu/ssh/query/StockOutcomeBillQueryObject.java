package com.baidu.ssh.query;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.baidu.ssh.util.DateUtil;

@Getter
@Setter
public class StockOutcomeBillQueryObject extends QueryObject {

	private Date beginDate;// 业务开始时间
	private Date endDate;// 业务结束时间
	private Long depotId = -1l;// 仓库id
	private Integer status = -1;// 订单状态
	private Long clientId = -1l;// 客户id

	public void customizedQuery() {
		if (depotId > 0) {
			addQuery("obj.depot.id = ?", depotId);
		}
		if (clientId > 0) {
			addQuery("obj.client.id = ?", clientId);
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
