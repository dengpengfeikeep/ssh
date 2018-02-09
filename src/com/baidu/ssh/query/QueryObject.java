package com.baidu.ssh.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName QueryObject
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年1月18日 上午11:11:11
 * @version 1.0.0
 */
public class QueryObject {
	@Getter
	@Setter
	private int currentPage = 1;
	@Getter
	@Setter
	private int pageSize = 10;

	private List<String> conditions = new ArrayList<>();
	private List<Object> params = new ArrayList<>();

	public String getQuery() {
		this.customizedQuery();
		if (conditions.size() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder(80);
		for (int index = 0; index < conditions.size(); index++) {
			if (index == 0) {
				sb.append(" where ");
			} else {
				sb.append(" and ");
			}
			sb.append(conditions.get(index));
		}
		return sb.toString();
	}

	public List<Object> getParameters() {
		return params;
	}

	protected boolean haslength(String str) {
		return str != null && !"".equals(str.trim());
	}

	// 供子类访问
	protected void customizedQuery() {

	}

	protected void addQuery(String condition, Object... args) {
		this.conditions.add(condition);
		this.params.addAll(Arrays.asList(args));
	}

}
