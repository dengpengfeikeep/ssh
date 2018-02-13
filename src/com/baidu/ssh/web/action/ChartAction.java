package com.baidu.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com.baidu.ssh.query.ChartQueryObject;
import com.baidu.ssh.service.IBrandService;
import com.baidu.ssh.service.IChartService;
import com.baidu.ssh.service.ISupplierService;
import com.baidu.ssh.vo.OrderGroupByType;

/**
 * @ClassName ChartAction
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年2月12日 下午8:05:25
 * @version 1.0.0
 */
public class ChartAction extends BaseAction {

	/**
	 * @Field @serialVersionUID : TODO(这里用一句话描述这个类的作用)
	 */
	private static final long serialVersionUID = 1L;
	@Setter
	private ISupplierService supplierService;
	@Setter
	private IBrandService brandService;
	@Setter
	private IChartService chartService;
	@Getter
	private ChartQueryObject oqo = new ChartQueryObject();

	public String orderChart() throws Exception {
		try {
			putContext("suppliers", supplierService.list());
			putContext("brands", brandService.list());
			putContext("orderGroupTypes", OrderGroupByType.values());// 把枚举传递到前台(一个数组)
			putContext("orderCharts", chartService.queryCharts(oqo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
}
