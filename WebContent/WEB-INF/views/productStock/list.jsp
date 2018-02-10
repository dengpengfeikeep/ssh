<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
	src="${pageContext.request.contextPath }/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/commonAll.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/common_page.js"></script>
<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath }/js/artDialog/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function() {
		$("input[name='qo.maxPrice']").change(function() {
			var v = $(this).val();
			if (v <= 0) {
				$.dialog({
					title : '操作提示',
					icon : 'face-sad',
					content : '阈值必须是正数',
					cancel : true,
				});
			}
		});
	});
</script>
<title>WMS-即时库存报表</title>
<style>
.alt td {
	background: black !important;
}
</style>
</head>
<body>
	<!--====================================-->
	<%@include file="/WEB-INF/views/common/common_msg.jsp"%>
	<!--====================================-->
	<s:form id="searchForm" namespace="/" action="productStock"
		method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							货品
							<s:textfield name="qo.productName" cssClass="ui_select03" />
							仓库
							<s:select list="#depots" name="qo.depotId" listKey="id"
								listValue="name" headerKey="-1" headerValue="全部"
								cssClass="ui_select03 " />
							品牌
							<s:select list="#brands" name="qo.brandId" listKey="id"
								listValue="name" headerKey="-1" headerValue="全部"
								cssClass="ui_select03 " />
							阈值
							<s:textfield name="qo.maxPrice" cssClass="ui_select03 " />
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 page_btn"
								data-page="1" />
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
							<th>编号</th>
							<th>货品</th>
							<th>仓库</th>
							<th>品牌</th>
							<th>库存价格</th>
							<th>库存数量</th>
							<th>库存总金额</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#pageResult.result">
								<tr>
									<td><input type="checkbox" name="IDCheck"
										autocomplete="off" class="acb"
										data-eid="<s:property value="id"/>" /></td>
									<td><s:property value="product.sn" /></td>
									<td><s:property value="product.name" /></td>
									<td><s:property value="depot.name" /></td>
									<td><s:property value="product.brand.name" /></td>
									<td><s:property value="price" /></td>
									<td><s:property value="stockNumber" /></td>
									<td><s:property value="amount" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<!--引入分页条-->
				<%@include file="/WEB-INF/views/common/common_page.jsp"%>
			</div>
		</div>
	</s:form>
</body>
</html>
