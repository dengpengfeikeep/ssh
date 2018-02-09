package com.baidu.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com.baidu.ssh.domain.Brand;
import com.baidu.ssh.query.BrandQueryObject;
import com.baidu.ssh.service.IBrandService;
import com.baidu.ssh.util.RequiredPermission;

public class BrandAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Setter
	private IBrandService brandService;

	@Getter
	private Brand brand = new Brand();
	@Getter
	private BrandQueryObject qo = new BrandQueryObject();

	// 列表界面
	@RequiredPermission("品牌列表")
	public String execute() throws Exception {
		putContext("pageResult", brandService.query(qo));
		return "list";
	}

	// 删除界面
	@RequiredPermission("品牌删除")
	public String delete() throws Exception {
		if (brand.getId() != null) {
			brandService.delete(brand.getId());
		}
		return "success";
	}

	// 编辑界面
	@RequiredPermission("品牌编辑")
	public String input() throws Exception {
		if (brand.getId() != null) {
			brand = brandService.get(brand.getId());
		}
		return "input";
	}

	// 保存与更新
	@RequiredPermission("品牌保存与更新")
	public String saveOrUpdate() throws Exception {
		if (brand.getId() == null) {
			brandService.save(brand);
		} else {
			brandService.update(brand);
		}
		return SUCCESS;
	}

	public void prepareSaveOrUpdate() throws Exception {
		if (brand.getId() != null) {
			brand = brandService.get(brand.getId());
		}
	}

}
