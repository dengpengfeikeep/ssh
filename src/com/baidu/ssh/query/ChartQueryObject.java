package com.baidu.ssh.query;

import java.util.Date;

import lombok.Data;

import com.baidu.ssh.domain.OrderBill;
import com.baidu.ssh.util.DateUtil;
import com.baidu.ssh.vo.OrderGroupByType;

/**
 * @ClassName ChartQueryObject
 * @Description 封装查询信息
 * @author Administrator
 * @Date 2018年2月12日 下午6:47:54
 * @version 1.0.0
 */
@Data
public class ChartQueryObject extends QueryObject {// 查询主体:orderBillItem
	private Date beginDate;// 业务开始时间
	private Date endDate;// 业务结束时间
	private String productName;// 货品名称
	private Long supplierId = -1l;// 供应商(默认全部)
	private Long brandId = -1l;// 品牌(默认)
	private String groupType = "employee";// 分组条件(默认根据订货人员查询)

	protected void customizedQuery() {
		if (beginDate != null) {
			addQuery("obj.bill.vdate >= ?", DateUtil.getBeginDate(beginDate));
		}
		if (endDate != null) {
			addQuery("obj.bill.vdate <= ?", DateUtil.getBeginDate(endDate));
		}
		if (supplierId > 0) {
			addQuery("obj.bill.supplier.id = ?", supplierId);
		}
		if (haslength(productName)) {
			String key = "%" + productName + "%";
			addQuery("obj.product.name like ?", key);
		}
		if (brandId > 0) {
			addQuery("obj.product.brand.id = ?", brandId);
		}
		// 已经审核的才可以被查询出来
		addQuery("obj.bill.status = ?", OrderBill.CHECK);

	}

}
