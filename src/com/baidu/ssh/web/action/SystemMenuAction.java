package com.baidu.ssh.web.action;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.baidu.ssh.domain.SystemMenu;
import com.baidu.ssh.query.SystemMenuQueryObject;
import com.baidu.ssh.service.ISystemMenuService;

public class SystemMenuAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Setter
	private ISystemMenuService systemMenuService;

	@Getter
	private SystemMenu systemMenu = new SystemMenu();
	@Getter
	private SystemMenuQueryObject qo = new SystemMenuQueryObject();

	// 列表界面
	public String execute() throws Exception {

		putContext("menus", systemMenuService.listMenus(qo.getParentId()));
		putContext("pageResult", systemMenuService.query(qo));
		return "list";
	}

	// 删除界面
	public String delete() throws Exception {
		try {
			if (systemMenu.getId() != null) {
				systemMenuService.delete(systemMenu.getId());
				putContextText("删除成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putContextText(e.getMessage());
		}
		return NONE;
	}

	// 编辑界面
	public String input() throws Exception {
		// 如果当前菜单是根菜单
		if (qo.getParentId() < 0) {
			putContext("parentName", "根菜单");
		} else {
			putContext("parentName", systemMenuService.get(qo.getParentId())
					.getName());
		}

		if (systemMenu.getId() != null) {
			systemMenu = systemMenuService.get(systemMenu.getId());
		}
		return "input";
	}

	// 保存与更新
	public String saveOrUpdate() throws Exception {
		if (qo.getParentId() > 0) {
			SystemMenu parent = systemMenuService.get(qo.getParentId());
			systemMenu.setParent(parent);
		}

		if (systemMenu.getId() == null) {
			systemMenuService.save(systemMenu);
		} else {
			systemMenuService.update(systemMenu);
		}
		return SUCCESS;
	}

	public String loadMenus() throws Exception {
		List<SystemMenu> menus = systemMenuService.loadMenusById(systemMenu
				.getId());
		List<Object> jsonList = new ArrayList<>();// 封装systemMenu转换成json之后的对象
		for (SystemMenu menu : menus) {
			jsonList.add(menu.toJson());// 转换成前台需要属性名,注意:不是json
		}
		ServletActionContext.getResponse().setContentType(
				"text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter()
				.print(JSON.toJSONString(jsonList));// 转换成json格式
		return NONE;
	}

	public void prepareSaveOrUpdate() throws Exception {
		if (systemMenu.getId() != null) {
			systemMenu = systemMenuService.get(systemMenu.getId());
		}
	}

}
