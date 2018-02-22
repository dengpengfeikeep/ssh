package com.baidu.ssh.web.action;

import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName LogoutAction
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年2月22日 下午2:43:18
 * @version 1.0.0
 */
public class LogoutAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		ActionContext.getContext().getSession().clear();
		return "success";
	}

}
