package com.baidu.ssh.service;

import java.util.List;

import com.baidu.ssh.domain.Cat;
import com.baidu.ssh.query.CatQueryObject;
import com.baidu.ssh.query.PageResult;

public interface ICatService {
	void save(Cat obj);

	void update(Cat obj);

	void delete(Long id);

	Cat get(Long id);

	List<Cat> list();

	PageResult query(CatQueryObject obj);

}
