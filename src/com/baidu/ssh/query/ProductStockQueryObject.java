package com.baidu.ssh.query;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductStockQueryObject extends QueryObject {

	private String productName;// 货品名称
	private Long depotId = -1l;// 仓库(默认是全部)
	private Long brandId = -1l;// 品牌(默认是全部)
	private BigDecimal maxPrice;// 阈值

	public void customizedQuery() {
		if (haslength(productName)) {
			String key = "%" + productName + "%";
			addQuery("obj.product.name like ?", key);
		}
		if (depotId > 0) {
			addQuery("obj.depot.id = ?", depotId);
		}
		if (brandId > 0) {
			addQuery("obj.product.brand.id = ?", brandId);
		}
		if (maxPrice != null) {
			addQuery("obj.price <= ?", maxPrice);
		}
	}
}
