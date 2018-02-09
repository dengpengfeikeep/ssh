package com.baidu.ssh.service.impl;

import java.util.List;

import lombok.Setter;

import com.baidu.ssh.dao.ICatDAO;
import com.baidu.ssh.domain.Cat;
import com.baidu.ssh.query.CatQueryObject;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.service.ICatService;

public class CatServiceImpl implements ICatService {
	@Setter
	private ICatDAO CatDAO;

	public void save(Cat obj) {
		CatDAO.save(obj);

	}

	public void update(Cat obj) {
		CatDAO.update(obj);

	}

	public void delete(Long id) {
		CatDAO.delete(id);

	}

	public Cat get(Long id) {

		return CatDAO.get(id);
	}

	public List<Cat> list() {

		return CatDAO.list();
	}

	public PageResult query(CatQueryObject qo) {

		return CatDAO.query(qo);
	}

}
