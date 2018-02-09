package com.baidu.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com.baidu.ssh.domain.StockIncomeBill;
import com.baidu.ssh.query.StockIncomeBillQueryObject;
import com.baidu.ssh.service.IDepotService;
import com.baidu.ssh.service.IStockIncomeBillService;
import com.baidu.ssh.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class StockIncomeBillAction extends BaseAction {

	/**
	 * @Field @serialVersionUID : TODO(这里用一句话描述这个类的作用)
	 */
	private static final long serialVersionUID = 1L;

	@Setter
	private IStockIncomeBillService stockIncomeBillService;

	@Setter
	private IDepotService depotService;

	@Getter
	private StockIncomeBill stockIncomeBill = new StockIncomeBill();

	@Getter
	private StockIncomeBillQueryObject qo = new StockIncomeBillQueryObject();

	@RequiredPermission("到货入库单列表")
	@InputConfig(methodName = "input")
	public String execute() throws Exception {
		try {
			putContext("pageResult", stockIncomeBillService.query(qo));
			putContext("depots", depotService.list());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	@RequiredPermission("到货入库单编辑")
	public String input() throws Exception {
		putContext("depots", depotService.list());
		if (stockIncomeBill.getId() != null) {
			stockIncomeBill = stockIncomeBillService.get(stockIncomeBill
					.getId());
		}
		return "input";
	}

	@RequiredPermission("审核")
	public String audit() throws Exception {
		try {
			if (stockIncomeBill.getId() != null) {
				stockIncomeBillService.audit(stockIncomeBill.getId());
				putContextText("审核成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putContextText(e.getMessage());
		}
		return NONE;
	}

	@RequiredPermission("审核查看")
	public String show() throws Exception {
		if (stockIncomeBill.getId() != null) {
			stockIncomeBill = stockIncomeBillService.get(stockIncomeBill
					.getId());
		}
		return "show";
	}

	@RequiredPermission("到货入库单删除")
	public String delete() throws Exception {
		if (stockIncomeBill.getId() != null) {
			stockIncomeBillService.delete(stockIncomeBill.getId());
			putContextText("删除成功!");
		}
		return "success";
	}

	@RequiredPermission("到货入库单保存或更新")
	public String saveOrUpdate() throws Exception {
		try {
			if (stockIncomeBill.getId() == null) {
				stockIncomeBillService.save(stockIncomeBill);
				addActionMessage("保存成功!");
			} else {
				stockIncomeBillService.update(stockIncomeBill);
				addActionMessage("更改成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "success";
	}

	public void prepareSaveOrUpdate() throws Exception {
		if (stockIncomeBill.getId() != null) {
			stockIncomeBill = stockIncomeBillService.get(stockIncomeBill
					.getId());
			stockIncomeBill.setDepot(null);// 这样修改才可以
			stockIncomeBill.getItems().clear();// 打破关联关系
		}
	}
}
