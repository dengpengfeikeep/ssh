package com.baidu.ssh.web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * @ClassName BaseAction
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年1月11日 上午11:28:17
 * @version 1.0.0
 */
public class BaseAction extends ActionSupport implements Preparable {

	/**
	 * @Field @serialVersionUID : TODO(这里用一句话描述这个类的作用)
	 */
	private static final long serialVersionUID = 1L;

	public void prepare() throws Exception {

	}

	public void putContext(String name, Object value) {
		ActionContext.getContext().put(name, value);
	}

	public void putContextText(String msg) throws Exception {
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(msg);
	}

}
