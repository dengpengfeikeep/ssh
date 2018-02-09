package com.baidu.ssh.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

import com.alibaba.fastjson.JSON;
import com.baidu.ssh.util.FileUploadUtil;

/**
 * @ClassName Product
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年1月25日 下午3:29:49
 * @version 1.0.0
 */
@Data
public class Product extends BaseDomain {
	private String name;
	private String sn;
	private BigDecimal costPrice;
	private BigDecimal salePrice;
	private String imagePath;
	private String intro;
	private Brand brand;

	public String getSmallImagePath() {
		if (imagePath == null) {
			return "";
		}
		int index = imagePath.lastIndexOf(".");
		return imagePath.substring(0, index) + FileUploadUtil.suffix
				+ imagePath.substring(index);
	}

}
