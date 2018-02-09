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
	src="${pageContext.request.contextPath }/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<!--====================================-->
	<%@include file="/WEB-INF/views/common/common_msg.jsp"%>
	<!--====================================-->
	<s:form name="editForm" action="stockIncomeBill_saveOrUpdate"
		method="post" id="editForm">
		<s:hidden name="stockIncomeBill.id" />
		<div id="container">
			<div id="nav_links">
				<span style="color: #1A5CC6;">采购订单查看</span>
				<div id="page_close">
					<a> <img
						src="${pageContext.request.contextPath }/images/common/page_close.png"
						width="20" height="20" style="vertical-align: text-top;" />
					</a>
				</div>
			</div>
			<div class="ui_content">
				<table cellspacing="0" cellpadding="0" width="100%" align="left"
					border="0">
					<tr>
						<td class="ui_text_rt" width="140">订单编号</td>
						<td class="ui_text_lt"><s:textfield name="stockIncomeBill.sn"
								cssClass="ui_input_txt02" /></td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">仓库</td>
						<td class="ui_text_lt"><s:textfield
								name="stockIncomeBill.depot.name" cssClass="ui_input_txt02" /></td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">业务时间</td>
						<td class="ui_text_lt"><s:textfield
								name="stockIncomeBill.vdate" cssClass="ui_input_txt02" /></td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">单据明细</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<table class="edit_table" cellspacing="0" cellpadding="0"
								border="0" style="width: auto">
								<thead>
									<tr>
										<th width="10"></th>
										<th width="200">货品</th>
										<th width="120">仓库</th>
										<th width="80">价格</th>
										<th width="80">数量</th>
										<th width="80">金额小计</th>
										<th width="150">备注</th>
										<th width="60"></th>
									</tr>
								</thead>
								<tbody id="edit_table_body">
									<s:iterator value="stockIncomeBill.items">
										<tr>
											<td></td>
											<td><s:textfield disabled="true" readonly="true"
													cssClass="ui_input_txt04" tag="name" name="product.name" />
												<s:hidden name="product.id" tag="pid" /></td>
											<td><s:textfield tag="depot"
													name="stockIncomeBill.depot.name" cssClass="ui_input_txt04" /></td>
											<td><s:textfield tag="costPrice" name="costPrice"
													cssClass="ui_input_txt04" /></td>
											<td><s:textfield tag="number" name="number"
													cssClass="ui_input_txt04" /></td>
											<td><s:textfield tag="amount" name="amount"
													cssClass="ui_input_txt04" /></td>
											<td><s:textfield tag="remark" name="remark"
													cssClass="ui_input_txt02" /></td>
											<td><a href="javascript:;" class="removeItem">删除明细</a></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</td>
					</tr>

					<tr>
						<td>&nbsp;</td>
						<td class="ui_text_lt">&nbsp;<input type="button"
							value="返回上一级" class="ui_input_btn01 " onclick="history.back()" />
							&nbsp;
						</td>
					</tr>
				</table>
			</div>
		</div>
	</s:form>
</body>
</html>