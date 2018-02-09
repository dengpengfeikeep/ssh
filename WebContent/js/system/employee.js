// 页面加载完成
$(function() {
	// 新增跳转
	$(".btn_input").click(function() {
		var url = $(this).data("url");
		window.location.href = url;
	});
	// 批量选中
	$("#all").click(function() {
		$(".acb").prop("checked", this.checked);
	});// 拿到选中的id数组
	$(".btn_batchDelete").click(function() {
		var ids = $.map($(".acb:checked"), function(item) {
			return $(item).data("eid");
		});
		if (ids.length == 0) {
			$.dialog({
				title : '操作提示',
				icon : 'face-sad',
				content : '请选择要删除的数据',
				cancel : true,
				ok : true,
			});
			return;
		} else if (ids.length > 0) {
			// 你确定要删除这批数据吗?
			$.dialog({
				title : '操作提示',
				icon : 'face-smile',
				content : '你确定要删除这批数据吗?',
				cancel : true,
				ok : function() {
					var url = $(".btn_batchDelete").data("url");
					var dialog = $.dialog({
						title : '操作提示',
						icon : 'face-smile',
						content : '删除成功!',
					});
					// 发送ajax请求
					$.get(url, {
						ids : ids
					}, function() {
						dialog.button({
							name : '我知道了!',
							callback : function() {
								window.location.reload();
							}
						});
					});
				},
			});
		}
	});
});

/** 输入表单校验 */
$(function() {
	$("#editForm").validate({
		rules : {
			"employee.name" : {
				rangelength : [ 2, 16 ]
			},
			"employee.password" : {
				required : true,
				minlength : 1
			},
			"repassword" : {
				equalTo : "#password"
			},
			"employee.email" : {
				required : true,
				email : true
			},
			"employee.age" : {
				required : true,
				range : [ 10, 70 ]
			}
		},
		messages : {
			"employee.name" : "账户名必须是2到16位",
			"employee.password" : {
				required : "密码必填",
				minlength : "密码不能少于1位"
			},
			"repassword" : {
				equalTo : "两次密码不一致"
			},
			"employee.email" : {
				required : "电子邮箱必填",
				email : "电子邮箱格式不正确"
			},
			"employee.age" : {
				required : "年龄必填",
				range : "年龄必须在10到70岁之间"
			}
		}
	});
});