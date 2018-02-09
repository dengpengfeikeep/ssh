package com.baidu.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com.baidu.ssh.domain.Cat;
import com.baidu.ssh.query.CatQueryObject;
import com.baidu.ssh.service.ICatService;

public class CatAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Setter
	private ICatService catService;

	@Getter
	private Cat cat = new Cat();
	@Getter
	private CatQueryObject qo = new CatQueryObject();

	// 列表界面
	public String execute() throws Exception {
		try {
			putContext("pageResult", catService.query(qo));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	// 删除界面
	public String delete() throws Exception {
		if (cat.getId() != null) {
			catService.delete(cat.getId());
		}
		return "success";
	}

	// 编辑界面
	public String input() throws Exception {
		if (cat.getId() != null) {
			cat = catService.get(cat.getId());
		}
		return "input";
	}

	// 保存与更新
	public String saveOrUpdate() throws Exception {
		if (cat.getId() == null) {
			catService.save(cat);
		} else {
			catService.update(cat);
		}
		return SUCCESS;
	}

	public void prepareSaveOrUpdate() throws Exception {
		if (cat.getId() != null) {
			cat = catService.get(cat.getId());
		}
	}

}
