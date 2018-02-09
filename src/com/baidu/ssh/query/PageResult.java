package com.baidu.ssh.query;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.baidu.ssh.domain.Employee;

/**
 * @ClassName PageResult
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年1月9日 上午9:33:42
 * @version 1.0.0
 */
@Getter
@Setter
@ToString
public class PageResult {
	// 这两条是发hql查询的
	private int totalCount;
	private List<Employee> result;

	// 这两条是用户传入的
	private int currentPage;
	private int pageSize;

	// 这三条是计算出来的
	private int totalPage;
	private int prevPage;
	private int nextPage;

	public PageResult(int totalCount, List<Employee> result, int currentPage,
			int pageSize) {
		super();
		this.totalCount = totalCount;
		this.result = result;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		if (pageSize != 0) {
			totalPage = totalCount % pageSize == 0 ? totalCount / pageSize
					: totalCount / pageSize + 1;
		}

		prevPage = currentPage - 1 > 0 ? currentPage - 1 : currentPage;
		nextPage = currentPage + 1 <= totalPage ? currentPage + 1 : totalPage;
	}

}
