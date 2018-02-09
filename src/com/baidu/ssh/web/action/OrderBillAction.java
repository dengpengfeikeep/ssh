package com.baidu.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com.baidu.ssh.domain.OrderBill;
import com.baidu.ssh.query.OrderBillQueryObject;
import com.baidu.ssh.service.IOrderBillService;
import com.baidu.ssh.service.ISupplierService;
import com.baidu.ssh.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class OrderBillAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Setter
	private IOrderBillService orderBillService;
	@Setter
	private ISupplierService supplierService;

	@Getter
	private OrderBill orderBill = new OrderBill();

	@Getter
	private OrderBillQueryObject qo = new OrderBillQueryObject();

	@RequiredPermission("采购订单列表")
	@InputConfig(methodName = "input")
	public String execute() throws Exception {
		try {
			putContext("pageResult", orderBillService.query(qo));
			putContext("suppliers", supplierService.list());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	@RequiredPermission("采购订单编辑")
	public String input() throws Exception {
		putContext("suppliers", supplierService.list());
		if (orderBill.getId() != null) {
			orderBill = orderBillService.get(orderBill.getId());
		}
		return "input";
	}

	@RequiredPermission("采购订单审核")
	public String audit() throws Exception {
		if (orderBill.getId() != null) {
			orderBillService.audit(orderBill.getId());
			putContextText("审核成功");
		}
		return "success";
	}

	@RequiredPermission("审核查看")
	public String show() throws Exception {
		if (orderBill.getId() != null) {
			orderBill = orderBillService.get(orderBill.getId());
		}
		return "show";
	}

	@RequiredPermission("采购订单删除")
	public String delete() throws Exception {
		if (orderBill.getId() != null) {
			orderBillService.delete(orderBill.getId());
			putContextText("删除成功!");
		}
		return SUCCESS;
	}

	@RequiredPermission("采购订单保存或更新")
	public String saveOrUpdate() throws Exception {
		try {
			if (orderBill.getId() == null) {
				orderBillService.save(orderBill);
				addActionMessage("保存成功!");
			} else {
				orderBillService.update(orderBill);
				addActionMessage("更改成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	public void prepareSaveOrUpdate() throws Exception {
		if (orderBill.getId() != null) {
			orderBill = orderBillService.get(orderBill.getId());
			orderBill.setSupplier(null);// 这样修改供应商才可以
			orderBill.getItems().clear();// 打破明细关系,明细成为孤儿
		}
	}
}
