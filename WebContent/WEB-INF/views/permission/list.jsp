<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath }/style/basic_layout.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/style/common_style.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/commonAll.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/common_page.js"></script>
<script type="text/javascript">
	$(function() {
		$(".loadpermission_btn").click(function() {
			var url = $(this).data("href");
			$.dialog({
				title : '操作提示',
				icon : 'face-smile',
				content : '你确定要加载权限吗?',
				cancel : true,
				ok : function() {
					$.ajax({
						url : url,
						type : "POST",
						dataType : 'json',
						success : function(result) {
							art.dialog({
								icon : 'succeed',
								title : '提示',
								drag : false,
								resize : false,
								content : '加载成功',
								ok : function() {
									window.location.reload();
								}
							});
						}
					});
				}
			});

		});
	})
</script>
<title>PSS-权限管理</title>
<style>
.alt td {
	background: black !important;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/common_msg.jsp"%>
	<s:form id="searchForm" action="permission" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
							<!-- <input type="button" value="查询" class="ui_input_btn01 page_btn"/> -->
							<input type="button" value="加载权限"
								class="ui_input_btn01 loadpermission_btn"
								data-href='<s:url action="permission_reload" />' />
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%"
						align="center" border="0">
						<tr>
							<th width="30"></th>
							<th>编号</th>
							<th>权限表达式</th>
							<th>权限名称</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#pageResult.result">
								<tr>
									<td></td>
									<td><s:property value="id" /></td>
									<td style="text-align: left; padding-left: 50px;"><s:property
											value="expression" /></td>
									<td><s:property value="name" /></td>
									<td><s:url namespace="/" action="permission_delete"
											var="deleteUrl">
											<s:param name="permission.id" value="id" />
										</s:url> <a href="#" class="btn_delete" data-url="${deleteUrl }">删除</a></td>
								</tr>

							</s:iterator>
						</tbody>
					</table>
				</div>
				<%@include file="/WEB-INF/views/common/common_page.jsp"%>
			</div>
		</div>
	</s:form>
</body>
</html>
