package com.baidu.ssh.web.action;

import java.io.File;

import org.apache.struts2.ServletActionContext;

import lombok.Getter;
import lombok.Setter;

import com.baidu.ssh.domain.Product;
import com.baidu.ssh.query.ProductQueryObject;
import com.baidu.ssh.service.IBrandService;
import com.baidu.ssh.service.IProductService;
import com.baidu.ssh.util.FileUploadUtil;
import com.baidu.ssh.util.RequiredPermission;

public class ProductAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Setter
	private IProductService productService;
	@Setter
	private IBrandService brandService;
	@Setter
	private File pic;// 上传的图片文件(数据)
	@Setter
	private String picFileName;// 上传文件的原始名称
	@Getter
	private Product product = new Product();
	@Getter
	private ProductQueryObject qo = new ProductQueryObject();

	// 列表界面
	@RequiredPermission("货品列表")
	public String execute() throws Exception {
		putContext("brands", brandService.list());
		putContext("pageResult", productService.query(qo));
		return "list";
	}

	@RequiredPermission("货品选择列表")
	public String selectProductList() throws Exception {
		putContext("brands", brandService.list());
		putContext("pageResult", productService.query(qo));
		return "selectProductList";
	}

	// 删除界面
	@RequiredPermission("货品删除")
	public String delete() throws Exception {
		if (product.getId() != null) {
			// 把货品的图片删除了
			product = productService.get(product.getId());
			if (product.getImagePath() != null) {
				FileUploadUtil.deleteFile(product.getImagePath());
			}
			productService.delete(product.getId());
			putContextText("删除成功!");
		}
		return NONE;
	}

	// 编辑界面
	@RequiredPermission("货品编辑")
	public String input() throws Exception {
		putContext("brands", brandService.list());
		if (product.getId() != null) {
			product = productService.get(product.getId());
		}
		return "input";
	}

	//

	/**
	 *返回请求商品的json格式数据
	 */
	public String json() throws Exception {
		if (product.getId() != null) {
			String json = productService.getToJson(product.getId());
			ServletActionContext.getResponse().setContentType(
					"text/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(json);
		}
		return NONE;
	}

	// 保存与更新
	@RequiredPermission("货品保存与更新")
	public String saveOrUpdate() throws Exception {
		if (product.getId() != null && pic != null
				&& product.getImagePath() != null) {
			FileUploadUtil.deleteFile(product.getImagePath());
		}
		if (pic != null) {
			// 返回图片在服务器的保存路径
			String uploadFile = FileUploadUtil.uploadFile(pic, picFileName);
			product.setImagePath(uploadFile);
		}
		if (product.getId() == null) {
			productService.save(product);
			addActionMessage("保存成功");
		} else {
			productService.update(product);
			addActionMessage("更新成功");
		}
		return SUCCESS;
	}

	public void prepareSaveOrUpdate() throws Exception {
		if (product.getId() != null) {
			product = productService.get(product.getId());
			product.setBrand(null);
		}
	}

}
