package com.baidu.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com.baidu.ssh.domain.ProductStock;
import com.baidu.ssh.query.ProductStockQueryObject;
import com.baidu.ssh.service.IBrandService;
import com.baidu.ssh.service.IDepotService;
import com.baidu.ssh.service.IProductStockService;
import com.baidu.ssh.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class ProductStockAction extends BaseAction {

	/**
	 * @Field @serialVersionUID : TODO(这里用一句话描述这个类的作用)
	 */
	private static final long serialVersionUID = 1L;

	@Setter
	private IProductStockService productStockService;

	@Setter
	private IDepotService depotService;

	@Setter
	private IBrandService brandService;

	@Getter
	private ProductStock productStock = new ProductStock();

	@Getter
	private ProductStockQueryObject qo = new ProductStockQueryObject();

	@RequiredPermission("库存列表")
	@InputConfig(methodName = "input")
	public String execute() throws Exception {
		putContext("depots", depotService.list());
		putContext("brands", brandService.list());
		try {
			putContext("pageResult", productStockService.query(qo));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}
}
