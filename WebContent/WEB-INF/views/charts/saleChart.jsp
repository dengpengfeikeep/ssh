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
		$("input[name='sqo.beginDate']").addClass("Wdate").click(function() {
			WdatePicker({
				//可选的选项
				maxDate : $("input[name='sqo.endDate']").val() || new Date(),
			});
		});
		$("input[name='sqo.endDate']").addClass("Wdate").click(function() {
			WdatePicker({
				//可选的选项
				minDate : $("input[name='sqo.beginDate']").val(),
				maxDate : new Date(),
			});
		});
		//清空查询条件
		$(".delete_query").click(function() {
			$("input[name='sqo.beginDate']").val("");
			$("input[name='sqo.endDate']").val("");
			$(".productName").val("");
			$(".client_id").val(-1);
			$(".brand_id").val(-1);
		});
		//选择查看线形图还是饼状图
		$(".select_chart").change(
				function() {
					var msg = $(this).val();
					if (msg == "line") {
						$("#searchForm")
								.prop("action", "chart_saleChartByLine")
								.submit();
					} else if (msg == "pie") {
						$("#searchForm").prop("action", "chart_saleChartByPie")
								.submit();
					}
				});
	});
</script>
<title>WMS-销售报表</title>
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
	<s:form id="searchForm" namespace="/" action="chart_saleChart"
		method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							业务时间
							<s:textfield name="sqo.beginDate" cssClass="ui_select03" />
							<s:textfield name="sqo.endDate" cssClass="ui_select03" />
							货品名称
							<s:textfield name="sqo.productName"
								cssClass="ui_select03 productName" />
							客户名称
							<s:select list="#clients" name="sqo.clientId" listKey="id"
								listValue="name" headerKey="-1" headerValue="全部"
								cssClass="ui_select03 client_id" />
							品牌
							<s:select list="#brands" name="sqo.brandId" listKey="id"
								listValue="name" headerKey="-1" headerValue="全部"
								cssClass="ui_select03 brand_id" />
							分组
							<s:select list="#saleGroupTypes" name="sqo.groupType"
								listKey="name()" listValue="groupType" cssClass="ui_select03 " />
							查看图表
							<s:select list="#{'':'请选择','line':'线形图','pie':'饼状图'}" name="XX"
								cssClass="ui_select03 select_chart" />
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
							<th>销售数量</th>
							<th>销售金额</th>
							<th>毛利润</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#saleCharts">
								<tr>
									<td><input type="checkbox" name="IDCheck"
										autocomplete="off" /></td>
									<td><s:property value="groupType" /></td>
									<td><s:property value="totalNumber" /></td>
									<td><s:property value="totalAmount" /></td>
									<td><s:property value="grossProfit" /></td>
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
