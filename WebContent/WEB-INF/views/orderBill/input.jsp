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
<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath }/js/artDialog/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function removeTrData(tr) {
		tr.find("[tag=name]").val("");
		tr.find("[tag=pid]").val("");
		tr.find("[tag=number]").val("");
		tr.find("[tag=remark]").val("");
		tr.find("[tag=brand]").val("");
		tr.find("[tag=costPrice]").val("");
		tr.find("[tag=amount]").text("");
	}

	$(function() {
		//选择商品
		$(".searchproduct").click(
				function() {
					window.open("product_selectProductList", "",
							"modal=yes,width=900,height=800");
					//在子页面中返回数据
				});
		//加上My97Date控件
		$("input[name='orderBill.vdate']").addClass("Wdate").click(function() {
			WdatePicker({
				//可选的选项
				maxDate : new Date(),
			});
		});
		//计算小计
		$("#edit_table_body").on("change",
				"input[tag='costPrice'],input[tag='number']", function() {
					var costPrice = $("input[tag='costPrice']").val();
					var number = $("input[tag='number']").val();
					$("input[tag='amount']").val(number * costPrice);
				});

		//添加明细
		$(".appendRow").click(function() {
			var tr = $("#edit_table_body tr:first").clone(true);//克隆
			//清除数据
			removeTrData(tr);
			tr.appendTo($("#edit_table_body"));
		});
		//清除明细
		$(".removeItem").click(function() {
			var tr = $(this).closest("tr");//往上找直到找到最近的tr
			if ($("#edit_table_body tr").size() == 1) {
				//如果只有一个tr,清除明细
				removeTrData(tr);
			} else {
				tr.remove();//删除当前行
			}
		});
		//重置表单
		$("#cancelbutton").click(function() {
			$.each($("#edit_table_body tr"), function(index, item) {
				if ($("#edit_table_body tr").size() == 1) {
					removeTrData(tr);
				} else {
					item.remove();
				}
			});
		});
		//提交表单
		$(".btn_submit").click(
				function() {
					//改变每一个明细的name
					$.each($("#edit_table_body tr"), function(index, item) {
						$(item).find("[tag=pid]").prop("name",
								"orderBill.items[" + index + "].product.id");
						$(item).find("[tag=number]").prop("name",
								"orderBill.items[" + index + "].number");
						$(item).find("[tag=remark]").prop("name",
								"orderBill.items[" + index + "].remark");
						$(item).find("[tag=costPrice]").prop("name",
								"orderBill.items[" + index + "].costPrice");
						$(item).find("[tag=amount]").prop("name",
								"orderBill.items[" + index + "].amount");
					});
					$("#editForm").submit();
				});
	});
</script>
</head>
<body>
	<!--====================================-->
	<%@include file="/WEB-INF/views/common/common_msg.jsp"%>
	<!--====================================-->
	<s:form name="editForm" action="orderBill_saveOrUpdate" method="post"
		id="editForm">
		<s:hidden name="orderBill.id" />
		<div id="container">
			<div id="nav_links">
				<span style="color: #1A5CC6;">采购订单编辑</span>
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
						<td class="ui_text_lt"><s:textfield name="orderBill.sn"
								cssClass="ui_input_txt02" /></td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">供应商</td>
						<td class="ui_text_lt"><s:select list="#suppliers"
								name="orderBill.supplier.id" listKey="id" listValue="name"
								cssClass="ui_select03" /></td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">业务时间</td>
						<td class="ui_text_lt"><s:textfield name="orderBill.vdate"
								cssClass="ui_input_txt02" /></td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">明细</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="button" value="添加明细"
							class="ui_input_btn01 appendRow" />
							<table class="edit_table" cellspacing="0" cellpadding="0"
								border="0" style="width: auto">
								<thead>
									<tr>
										<th width="10"></th>
										<th width="200">货品</th>
										<th width="120">品牌</th>
										<th width="80">价格</th>
										<th width="80">数量</th>
										<th width="80">金额小计</th>
										<th width="150">备注</th>
										<th width="60"></th>
									</tr>
								</thead>
								<tbody id="edit_table_body">
									<!-- 新增 -->
									<s:if test="orderBill.id==null">
										<tr>
											<td></td>
											<td><s:textfield disabled="true" readonly="true"
													cssClass="ui_input_txt04" tag="name" /> <img
												src="${pageContext.request.contextPath }/images/common/search.png"
												class="searchproduct" /> <s:hidden
													name="orderBill.items.product.id" tag="pid" /></td>
											<td><s:textfield tag="brand"
													name="orderBill.items.brand.name" cssClass="ui_input_txt04"
													readonly="true" /></td>
											<td><s:textfield tag="costPrice"
													name="orderBill.items.costPrice" cssClass="ui_input_txt04" /></td>
											<td><s:textfield tag="number"
													name="orderBill.items.number" cssClass="ui_input_txt04" /></td>
											<td><s:textfield tag="amount"
													name="orderBill.items.amount" cssClass="ui_input_txt04"
													readonly="true" /></td>
											<td><s:textfield tag="remark"
													name="orderBill.items.remark" cssClass="ui_input_txt02" /></td>
											<td><a href="javascript:;" class="removeItem">删除明细</a></td>
										</tr>
									</s:if>
									<!--更新  -->
									<s:else>
										<s:iterator value="orderBill.items"></s:iterator>
										<tr>
											<td></td>
											<td><s:textfield disabled="true" readonly="true"
													cssClass="ui_input_txt04" tag="name" name="product.name" />
												<img
												src="${pageContext.request.contextPath }/images/common/search.png"
												class="searchproduct" /> <s:hidden name="product.id"
													tag="pid" /></td>
											<td><s:textfield tag="brand" name="product.brand.name"
													cssClass="ui_input_txt04" readonly="true" /></td>
											<td><s:textfield tag="costPrice" name="costPrice"
													cssClass="ui_input_txt04" /></td>
											<td><s:textfield tag="number" name="number"
													cssClass="ui_input_txt04" /></td>
											<td><s:textfield tag="amount" name="amount"
													cssClass="ui_input_txt04" readonly="true" /></td>
											<td><s:textfield tag="remark" name="remark"
													cssClass="ui_input_txt02" /></td>
											<td><a href="javascript:;" class="removeItem">删除明细</a></td>
										</tr>
									</s:else>
								</tbody>
							</table></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="ui_text_lt">&nbsp;<input type="button"
							value="确定保存" class="ui_input_btn01 btn_submit" /> &nbsp;<input
							id="cancelbutton" type="button" value="重置"
							class="ui_input_btn01 " />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</s:form>
</body>
</html>