package com.baidu.ssh.dao;

import java.util.List;

import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.QueryObject;

/**
 * @ClassName IGenericDAO
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年1月11日 下午12:02:18
 * @version 1.0.0
 */
public interface IGenericDAO<T> {
	void save(T obj);

	void update(T obj);

	void delete(Long id);

	T get(Long id);

	List<T> list();

	PageResult query(QueryObject qo);

	void batchDelete(List<Long> id);

}
