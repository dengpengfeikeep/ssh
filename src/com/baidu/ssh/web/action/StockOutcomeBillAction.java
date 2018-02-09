package com.baidu.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com.baidu.ssh.domain.StockOutcomeBill;
import com.baidu.ssh.query.StockOutcomeBillQueryObject;
import com.baidu.ssh.service.IClientService;
import com.baidu.ssh.service.IDepotService;
import com.baidu.ssh.service.IStockOutcomeBillService;
import com.baidu.ssh.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class StockOutcomeBillAction extends BaseAction {

	/**
	 * @Field @serialVersionUID : TODO(这里用一句话描述这个类的作用)
	 */
	private static final long serialVersionUID = 1L;

	@Setter
	private IStockOutcomeBillService stockOutcomeBillService;

	@Setter
	private IClientService clientService;

	@Setter
	private IDepotService depotService;

	@Getter
	private StockOutcomeBill stockOutcomeBill = new StockOutcomeBill();

	@Getter
	private StockOutcomeBillQueryObject qo = new StockOutcomeBillQueryObject();

	@RequiredPermission("到货入库单列表")
	@InputConfig(methodName = "input")
	public String execute() throws Exception {
		try {
			putContext("pageResult", stockOutcomeBillService.query(qo));
			putContext("depots", depotService.list());
			putContext("clients", clientService.list());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	@RequiredPermission("到货入库单编辑")
	public String input() throws Exception {
		putContext("depots", depotService.list());
		putContext("clients", clientService.list());
		if (stockOutcomeBill.getId() != null) {
			stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill
					.getId());
		}
		return "input";
	}

	@RequiredPermission("到货出库单审核")
	public String audit() throws Exception {
		try {
			if (stockOutcomeBill.getId() != null) {
				stockOutcomeBillService.audit(stockOutcomeBill.getId());
				putContextText("审核成功!");
			}
		} catch (Exception e) {
			e.getMessage();
			putContextText(e.getMessage());
		}
		return NONE;
	}

	@RequiredPermission("审核查看")
	public String show() throws Exception {
		if (stockOutcomeBill.getId() != null) {
			stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill
					.getId());
		}
		return "show";
	}

	@RequiredPermission("到货入库单删除")
	public String delete() throws Exception {
		if (stockOutcomeBill.getId() != null) {
			stockOutcomeBillService.delete(stockOutcomeBill.getId());
			putContextText("删除成功!");
		}
		return "success";
	}

	@RequiredPermission("到货入库单保存或更新")
	public String saveOrUpdate() throws Exception {
		try {
			if (stockOutcomeBill.getId() == null) {
				stockOutcomeBillService.save(stockOutcomeBill);
				addActionMessage("保存成功!");
			} else {
				stockOutcomeBillService.update(stockOutcomeBill);
				addActionMessage("更改成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "success";
	}

	public void prepareSaveOrUpdate() throws Exception {
		if (stockOutcomeBill.getId() != null) {
			stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill
					.getId());
			stockOutcomeBill.setDepot(null);// 这样修改才可以
			stockOutcomeBill.getItems().clear();// 打破关联关系
		}
	}
}
