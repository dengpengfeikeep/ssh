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
		$(".select").click(
				function() {
					var id = $(this).data("id");
					//发送ajax请求,返回该商品的json格式数据
					$.get("product_json", {
						'product.id' : id
					},
							function(data) {
								//回传数据给input.jsp
								/* 	window.opener.window
											.$(
													"input[tag='costPrice']")
											.val(
													window.opener.window
															.$(
																	"input[tag='costPrice']")
															.val()
															+ data.costPrice) */
								window.opener.window
										.$("input[tag='salePrice']").val(
												data.costPrice);
								window.opener.window.$("input[tag='pid']").val(
										data.id);
								window.opener.window.$("input[tag='name']")
										.val(data.name);
								window.opener.window.$("input[tag='brand']")
										.val(data.brandName);
								window.close();
							});
				});
	})
</script>
<title>PSS-货品管理</title>
<style>
.alt td {
	background: black !important;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/common_msg.jsp"%>
	<s:form id="searchForm" action="#" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							关键词
							<s:textfield name="qo.keyWord" cssClass="ui_input_txt02" />
							品牌
							<s:select list="#brands" name="qo.brandId" headerKey="-1"
								headerValue="-请选择-" listKey="id" listValue="name"
								cssClass="ui_select03">
							</s:select>
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 page_btn" />
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%"
						align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>货品图片</th>
							<th>货品名称</th>
							<th>货品编码</th>
							<th>货品品牌</th>
							<th>成本价格</th>
							<th>销售价格</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#pageResult.result">
								<tr>
									<td><input type="checkbox" autocomplete="off"
										name="IDCheck" class="acb" data-eid=<s:property value="id" /> /></td>
									<td><img src="<s:property value="smallImagePath" />"></td>
									<td><s:property value="name" /></td>
									<td><s:property value="sn" /></td>
									<td><s:property value="brand.name" /></td>
									<td><s:property value="costPrice" /></td>
									<td><s:property value="salePrice" /></td>
									<td><input type="button" value="选择该商品" class="select"
										data-id="${id}"></td>
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
