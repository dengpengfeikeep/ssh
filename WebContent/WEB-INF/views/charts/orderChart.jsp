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
		//加上My97Date控件
		$("input[name='oqo.beginDate']").addClass("Wdate").click(function() {
			WdatePicker({
				//可选的选项
				maxDate : $("input[name='oqo.endDate']").val() || new Date(),
			});
		});
		$("input[name='oqo.endDate']").addClass("Wdate").click(function() {
			WdatePicker({
				//可选的选项
				minDate : $("input[name='oqo.beginDate']").val(),
				maxDate : new Date(),
			});
		});
		//清空查询条件
		$(".delete_query").click(function() {
			$("input[name='oqo.beginDate']").val("");
			$("input[name='oqo.endDate']").val("");
			$(".productName").val("");
			$(".supplier_id").val(-1);
			$(".brand_id").val(-1);
		});
	});
</script>
<title>WMS-订货报表</title>
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
	<s:form id="searchForm" namespace="/" action="chart_orderChart"
		method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							业务时间
							<s:textfield name="oqo.beginDate" cssClass="ui_select03" />
							<s:textfield name="oqo.endDate" cssClass="ui_select03" />
							货品名称
							<s:textfield name="oqo.productName"
								cssClass="ui_select03 productName" />
							供应商
							<s:select list="#suppliers" name="oqo.supplierId" listKey="id"
								listValue="name" headerKey="-1" headerValue="全部"
								cssClass="ui_select03 supplier_id" />
							品牌
							<s:select list="#brands" name="oqo.brandId" listKey="id"
								listValue="name" headerKey="-1" headerValue="全部"
								cssClass="ui_select03 brand_id" />
							分组
							<s:select list="#orderGroupTypes" name="oqo.groupType"
								listKey="name()" listValue="groupType" cssClass="ui_select03 " />
						</div>
						<div id="box_bottom">
							<input type="button" value="清空条件"
								class="ui_input_btn01 delete_query" /> <input type="submit"
								value="查询" class="ui_input_btn01 " />
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
							<th>分组类型</th>
							<th>订货数量</th>
							<th>订货金额</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#orderCharts">
								<tr>
									<td><input type="checkbox" name="IDCheck"
										autocomplete="off" /></td>
									<td><s:property value="groupType" /></td>
									<td><s:property value="totalNumber" /></td>
									<td><s:property value="totalAmount" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</s:form>
</body>
</html>
