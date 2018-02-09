package com.baidu.ssh.query;

import lombok.Data;

@Data
public class SystemMenuQueryObject extends QueryObject {
	private Long parentId = -1l;

	public void customizedQuery() {
		if (parentId > 0) {
			addQuery("obj.parent.id = ?", parentId);
		} else {
			addQuery("obj.parent is null");
		}

	}
}