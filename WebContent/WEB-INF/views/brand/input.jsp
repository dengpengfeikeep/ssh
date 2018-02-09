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
	src="${pageContext.request.contextPath }/js/commonAll.js"></script>
</head>
<body>
	<%@include file="/WEB-INF/views/common/common_msg.jsp"%>
	<form name="editForm" namespace="/" action="brand_saveOrUpdate"
		method="post" id="editForm">
		<s:hidden name="brand.id" />
		<div id="container">
			<div id="nav_links">
				<span style="color: #1A5CC6;">品牌编辑</span>
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
						<td class="ui_text_rt" width="140">品牌名称</td>
						<td class="ui_text_lt"><s:textfield name="brand.name"
								cssClass="ui_input_txt02">
							</s:textfield></td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">品牌编码</td>
						<td class="ui_text_lt"><s:textfield name="brand.sn"
								cssClass="ui_input_txt02">
							</s:textfield></td>
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
	</form>
</body>
</html>