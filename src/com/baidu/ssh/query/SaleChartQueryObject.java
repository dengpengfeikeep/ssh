package com.baidu.ssh.query;

import java.util.Date;

import lombok.Data;

import com.baidu.ssh.domain.OrderBill;
import com.baidu.ssh.util.DateUtil;

/**
 * @ClassName SaleChartQueryObject
 * @Description 封装销售查询信息
 * @author Administrator
 * @Date 2018年2月12日 下午6:47:54
 * @version 1.0.0
 */
@Data
public class SaleChartQueryObject extends QueryObject {// 查询主体:saleAccount
	private Date beginDate;// 业务开始时间
	private Date endDate;// 业务结束时间
	private String productName;// 货品名称
	private Long clientId = -1l;// 客户
	private Long brandId = -1l;// 品牌(默认)
	private String groupType = "employee";// 分组条件(默认根据销售人员查询)

	protected void customizedQuery() {
		if (beginDate != null) {
			addQuery("obj.vdate >= ?", DateUtil.getBeginDate(beginDate));
		}
		if (endDate != null) {
			addQuery("obj.vdate <= ?", DateUtil.getBeginDate(endDate));
		}
		if (clientId > 0) {
			addQuery("obj.client.id = ?", clientId);
		}
		if (haslength(productName)) {
			String key = "%" + productName + "%";
			addQuery("obj.product.name like ?", key);
		}
		if (brandId > 0) {
			addQuery("obj.product.brand.id = ?", brandId);
		}

	}

}
