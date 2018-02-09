package com.baidu.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com.baidu.ssh.domain.Client;
import com.baidu.ssh.query.ClientQueryObject;
import com.baidu.ssh.service.IClientService;
import com.baidu.ssh.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class ClientAction extends BaseAction {

	/**
	 * @Field @serialVersionUID : TODO(这里用一句话描述这个类的作用)
	 */
	private static final long serialVersionUID = 1L;

	@Setter
	private IClientService clientService;

	@Getter
	private Client client = new Client();

	@Getter
	private ClientQueryObject qo = new ClientQueryObject();

	@RequiredPermission("客户列表")
	@InputConfig(methodName = "input")
	public String execute() throws Exception {
		try {
			putContext("pageResult", clientService.query(qo));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	@RequiredPermission("客户编辑")
	public String input() throws Exception {
		if (client.getId() != null) {
			client = clientService.get(client.getId());
		}
		return "input";
	}

	@RequiredPermission("客户删除")
	public String delete() throws Exception {
		if (client.getId() != null) {
			clientService.delete(client.getId());
			putContextText("删除成功!");
		}
		return "success";
	}

	@RequiredPermission("客户保存或更新")
	public String saveOrUpdate() throws Exception {
		try {
			if (client.getId() == null) {
				clientService.save(client);
				addActionMessage("保存成功!");
			} else {
				clientService.update(client);
				addActionMessage("更改成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "success";
	}

	public void prepareSaveOrUpdate() throws Exception {
		if (client.getId() != null) {
			client = clientService.get(client.getId());
		}
	}
}
