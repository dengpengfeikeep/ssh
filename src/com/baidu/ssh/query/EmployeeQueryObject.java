package com.baidu.ssh.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName EmployeeQueryObject
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年1月8日 下午3:17:36
 * @version 1.0.0
 */
public class EmployeeQueryObject extends QueryObject {
	@Getter
	@Setter
	private String keyword;
	@Getter
	@Setter
	private Long deptId = -1l;

	public void customizedQuery() {
		if (haslength(keyword)) {
			String key = "%" + keyword + "%";
			super.addQuery("(obj.name like ? or obj.email like ?)", key, key);
		}
		if (deptId > 0) {
			super.addQuery("obj.dept.id = ?", deptId);
		}
	}

}