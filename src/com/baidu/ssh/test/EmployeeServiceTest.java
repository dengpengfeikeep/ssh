package com.baidu.ssh.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baidu.ssh.domain.Employee;
import com.baidu.ssh.query.EmployeeQueryObject;
import com.baidu.ssh.service.IEmployeeService;
import com.baidu.ssh.service.IPermissionService;
import com.baidu.ssh.service.ISystemMenuService;

/**
 * @ClassName EmployeeServiceTest
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年12月28日 上午3:01:49
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml",
		"classpath*:applicationContext-service.xml",
		"classpath*:applicationContext-dao.xml",
		"classpath*:applicationContext-action.xml" })
public class EmployeeServiceTest {
	@Autowired
	private IEmployeeService service;
	@Autowired
	private IPermissionService permissionService;
	@Autowired
	private ISystemMenuService systemMenuService;

	@Test
	public void test2() {
		systemMenuService.delete(28l);
	}

	@Test
	public void testdd() throws Exception {
		EmployeeQueryObject qo = new EmployeeQueryObject();
		qo.setKeyword("admin");
		service.query(qo);
	}

	@Test
	public void testDelete() {
		Long[] ids = { 72l, 73l, 74l, 75l };
		List<Long> id = Arrays.asList(ids);
		service.batchDelete(id);

	}

	@Test
	public void test() {
		for (int i = 0; i < 30; i++) {
			Employee e = new Employee();
			e.setName("test" + i);
			service.save(e);
		}
	}

	@Test
	public void testreload() {
		permissionService.reload();
	}
}
