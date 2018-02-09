<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
	src="${pageContext.request.contextPath }/js/plugins/jquery-validate/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
</head>
<body>
	<!--====================================-->
	<%@include file="/WEB-INF/views/common/common_msg.jsp"%>
	<!--====================================-->
	<s:form name="editForm" action="client_saveOrUpdate" method="post"
		id="editForm">
		<s:hidden name="client.id" />
		<div id="container">
			<div id="nav_links">
				<span style="color: #1A5CC6;">客户编辑</span>
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
						<td class="ui_text_rt" width="140">客户编码</td>
						<td class="ui_text_lt"><s:textfield name="client.sn"
								cssClass="ui_input_txt02" /></td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">客户姓名</td>
						<td class="ui_text_lt"><s:textfield name="client.name"
								cssClass="ui_input_txt02" /></td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">联系电话</td>
						<td class="ui_text_lt"><s:textfield name="client.phone"
								cssClass="ui_input_txt02" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="ui_text_lt">&nbsp;<input type="submit"
							value="确定保存" class="ui_input_btn01" /> &nbsp;<input
							id="cancelbutton" type="button" value="重置" class="ui_input_btn01" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</s:form>
</body>
</html>