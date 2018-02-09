package com.baidu.ssh.domain;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @ClassName ProductStock
 * @Description 库存
 * @author Administrator
 * @Date 2018年2月9日 上午12:47:24
 * @version 1.0.0
 */
@Data
public class ProductStock extends BaseDomain {
	private Product product;// 表示哪一个货品
	private Depot depot;// 表示哪一个仓库
	private BigDecimal price;// 表示货品库存价格
	private BigDecimal amount;// 表示库存金额
	private BigDecimal stockNumber;// 表示库存数量
}
