package com.baidu.ssh.query;

import lombok.Data;

@Data
public class ProductQueryObject extends QueryObject {

	private String keyWord;// 关键词
	private Long brandId = -1l;// 品牌ID

	public void customizedQuery() {
		if (haslength(keyWord)) {
			String key = "%" + keyWord + "%";
			addQuery("obj.name like ?", key);
		}
		if (brandId > 0) {
			addQuery("obj.brand.id = ?", brandId);
		}
	}
}