<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title>信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/style/basic_layout.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/style/common_style.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/commonAll.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/system/employee.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/system/roles.js"></script>
</head>
<body>
	<%@include file="/WEB-INF/views/common/common_msg.jsp"%>
	<s:form name="editForm" action="/employee_saveOrUpdate" method="post"
		id="editForm">
		<input type="hidden" name="employee.id"
			value="<s:property value="employee.id"/>">
		<div id="container">
			<div id="nav_links">
				<span style="color: #1A5CC6;">用户编辑</span>
				<div id="page_close">
					<a> <img src="/images/common/page_close.png" width="20"
						height="20" style="vertical-align: text-top;" />
					</a>
				</div>
			</div>
			<div class="ui_content">
				<table cellspacing="0" cellpadding="0" width="100%" align="left"
					border="0">
					<tr>
						<td class="ui_text_rt" width="140">用户名</td>
						<td class="ui_text_lt"><s:textfield name="employee.name"
								cssClass="ui_input_txt02" /></td>
					</tr>
					<s:if test="employee.id==null">
						<tr>
							<td class="ui_text_rt" width="140">密码</td>
							<td class="ui_text_lt"><s:password name="employee.password"
									id="password" cssClass="ui_input_txt02" /></td>
						</tr>
						<tr>
							<td class="ui_text_rt" width="140">验证密码</td>
							<td class="ui_text_lt"><s:password name="repassword"
									cssClass="ui_input_txt02" /></td>
						</tr>
					</s:if>
					<tr>
						<td class="ui_text_rt" width="140">E-mail</td>
						<td class="ui_text_lt"><s:textfield name="employee.email"
								cssClass="ui_input_txt02" /></td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">年龄</td>
						<td class="ui_text_lt"><s:textfield name="employee.age"
								cssClass="ui_input_txt02" /></td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">所属部门</td>
						<td class="ui_text_lt"><s:select list="#depts"
								name="employee.dept.id" listKey="id" listValue="name"
								cssClass="ui_select01" /></td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">超级管理员</td>
						<td class="ui_text_lt"><s:checkbox name="employee.admin"
								cssClass="ui_checkbox01" /></td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">角色</td>
						<td class="ui_text_lt">
							<table>
								<tr>
									<td><s:select list="#roles" listKey="id" listValue="name"
											multiple="true" cssClass="ui_multiselect01 all" /></td>
									<td align="center"><input type="button" id="select"
										value="--->" /><br /> <input type="button" id="selectAll"
										value="==>" /><br /> <input type="button" id="deselect"
										value="<---" /><br /> <input type="button" id="deselectAll"
										value="<==" /></td>
									<td><s:select list="employee.roles"
											name="employee.roles.id" listKey="id" listValue="name"
											multiple="true" cssClass="ui_multiselect01 selected" /></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="ui_text_lt">&nbsp;<input type="submit"
							value="保存编辑" class="ui_input_btn01" /> &nbsp;<input
							id="cancelbutton" type="button" value="重置" class="ui_input_btn01" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</s:form>
</body>
</html>