package com.baidu.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com.baidu.ssh.domain.Supplier;
import com.baidu.ssh.query.SupplierQueryObject;
import com.baidu.ssh.service.ISupplierService;
import com.baidu.ssh.util.RequiredPermission;

public class SupplierAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Setter
	private ISupplierService supplierService;

	@Getter
	private Supplier supplier = new Supplier();
	@Getter
	private SupplierQueryObject qo = new SupplierQueryObject();

	// 列表界面
	@RequiredPermission("供应商列表")
	public String execute() throws Exception {
		putContext("pageResult", supplierService.query(qo));
		return "list";
	}

	// 删除界面
	@RequiredPermission("供应商删除")
	public String delete() throws Exception {
		if (supplier.getId() != null) {
			supplierService.delete(supplier.getId());
		}
		return "success";
	}

	// 编辑界面
	@RequiredPermission("供应商编辑")
	public String input() throws Exception {
		if (supplier.getId() != null) {
			supplier = supplierService.get(supplier.getId());
		}
		return "input";
	}

	// 保存与更新
	@RequiredPermission("供应商保存与更新")
	public String saveOrUpdate() throws Exception {
		if (supplier.getId() == null) {
			supplierService.save(supplier);
		} else {
			supplierService.update(supplier);
		}
		return SUCCESS;
	}

	public void prepareSaveOrUpdate() throws Exception {
		if (supplier.getId() != null) {
			supplier = supplierService.get(supplier.getId());
		}
	}

}
