//禁用将表单参数序列化
jQuery.ajaxSettings.traditional = true;

/** table鼠标悬停换色* */
$(function() {
	// 新增跳转
	$(".btn_input").click(function() {
		var url = $(this).data("url");
		window.location.href = url;
	});
	// 如果鼠标移到行上时，执行函数
	$(".table tr").mouseover(function() {
		$(this).css({
			background : "#CDDAEB"
		});
		$(this).children('td').each(function(index, ele) {
			$(ele).css({
				color : "#1D1E21"
			});
		});
	}).mouseout(function() {
		$(this).css({
			background : "#FFF"
		});
		$(this).children('td').each(function(index, ele) {
			$(ele).css({
				color : "#909090"
			});
		});
	});
});

// 删除提示
$(function() {
	$(".btn_delete").click(function() {
		var url = $(this).data("url");
		$.dialog({
			title : '操作提示',
			icon : 'face-smile',
			content : '你确定要删除吗?',
			cancel : true,
			ok : function() {
				$.get(url, function(data) {
					var dialog = $.dialog({
						title : '操作提示',
						icon : 'face-smile',
					});
					dialog.content(data).button({
						name : '我知道了!',
						callback : function() {
							window.location.reload();
						},
					});
				});
			},
		});
	});
});
