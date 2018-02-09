package com.baidu.ssh.domain;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * @ClassName SystemMenu
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年1月23日 下午3:41:16
 * @version 1.0.0
 */
@Data
public class SystemMenu extends BaseDomain implements IJson {
	private String name;
	private String url;// 菜单链接,子菜单有,父菜单没有
	private String sn;// 菜单编码,子菜单没有,父菜单有
	private SystemMenu parent;// 父菜单

	public String getParentName() {
		return this.parent == null ? "根菜单" : this.parent.getName();
	}

	public Map<String, Object> toJson() {
		Map<String, Object> menuMap = new HashMap<>();
		menuMap.put("name", name);
		menuMap.put("id", id);
		menuMap.put("pId", parent.getId() != null ? parent.getId() : null);
		menuMap.put("action", url);
		return menuMap;
	}
}
