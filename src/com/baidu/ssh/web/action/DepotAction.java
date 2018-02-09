package com.baidu.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com.baidu.ssh.domain.Depot;
import com.baidu.ssh.query.DepotQueryObject;
import com.baidu.ssh.service.IDepotService;
import com.baidu.ssh.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class DepotAction extends BaseAction {

	@Setter
	private IDepotService depotService;

	@Getter
	private Depot depot = new Depot();

	@Getter
	private DepotQueryObject qo = new DepotQueryObject();

	@RequiredPermission("仓库列表")
	@InputConfig(methodName = "input")
	public String execute() throws Exception {
		try {
			putContext("pageResult", depotService.query(qo));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	@RequiredPermission("仓库编辑")
	public String input() throws Exception {
		if (depot.getId() != null) {
			depot = depotService.get(depot.getId());
		}
		return "input";
	}

	@RequiredPermission("仓库删除")
	public String delete() throws Exception {
		if (depot.getId() != null) {
			depotService.delete(depot.getId());
			putContextText("删除成功!");
		}
		return "success";
	}

	@RequiredPermission("仓库保存或更新")
	public String saveOrUpdate() throws Exception {
		try {
			if (depot.getId() == null) {
				depotService.save(depot);
				addActionMessage("保存成功!");
			} else {
				depotService.update(depot);
				addActionMessage("更改成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "success";
	}

	public void prepareSaveOrUpdate() throws Exception {
		if (depot.getId() != null) {
			depot = depotService.get(depot.getId());
		}
	}
}
