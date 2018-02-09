package com.baidu.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com.baidu.ssh.domain.ProductStock;
import com.baidu.ssh.query.ProductStockQueryObject;
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

	@Getter
	private ProductStock productStock = new ProductStock();

	@Getter
	private ProductStockQueryObject qo = new ProductStockQueryObject();

	@RequiredPermission("库存列表")
	@InputConfig(methodName = "input")
	public String execute() throws Exception {
		try {
			putContext("pageResult", productStockService.query(qo));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	@RequiredPermission("库存编辑")
	public String input() throws Exception {
		if (productStock.getId() != null) {
			productStock = productStockService.get(productStock.getId());
		}
		return "input";
	}

	@RequiredPermission("库存删除")
	public String delete() throws Exception {
		if (productStock.getId() != null) {
			productStockService.delete(productStock.getId());
			putContextText("删除成功!");
		}
		return "success";
	}

	@RequiredPermission("库存保存或更新")
	public String saveOrUpdate() throws Exception {
		try {
			if (productStock.getId() == null) {
				productStockService.save(productStock);
				addActionMessage("保存成功!");
			} else {
				productStockService.update(productStock);
				addActionMessage("更改成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "success";
	}

	public void prepareSaveOrUpdate() throws Exception {
		if (productStock.getId() != null) {
			productStock = productStockService.get(productStock.getId());
		}
	}
}
