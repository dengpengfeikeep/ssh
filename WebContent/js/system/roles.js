$(function() {
	// 全部从左到右
	$("#selectAll").click(function() {
		$(".all option").appendTo(".selected");
	});
	// 全部从右到左
	$("#deselectAll").click(function() {
		$(".selected option").appendTo(".all");
	});
	// 选中从左到右
	$("#select").click(function() {
		$(".all option:selected").appendTo(".selected");
	});
	// 选中从右到左
	$("#deselect").click(function() {
		$(".selected option:selected").appendTo(".all");
	});

	/*
	 * 从权限列表中去除已经选择的权限
	 */
	// 把已经分配好的权限的id值存到一个数组中
	var ids = $.map($(".selected option"), function(item) {
		return item.value;
	});
	// 迭代所有的权限id
	$.each($(".all option"), function(index, item) {
		var id = item.value;
		if ($.inArray(id, ids) >= 0) {
			$(item).remove();
		}
	});

	/*
	 * 选择的时候自动选定:selected
	 */
	$("#editForm").submit(function() {
		$(".selected option").prop("selected", true);
	});
	// .................................................
	// 全部从左到右
	$("#mselectAll").click(function() {
		$(".menus_all option").appendTo(".menus_selected");
	});
	// 全部从右到左
	$("#mdeselectAll").click(function() {
		$(".menus_selected option").appendTo(".menus_all");
	});
	// 选中从左到右
	$("#mselect").click(function() {
		$(".menus_all option:selected").appendTo(".menus_selected");
	});
	// 选中从右到左
	$("#mdeselect").click(function() {
		$(".menus_selected option:selected").appendTo(".menus_all");
	});

	/*
	 * 从权限列表中去除已经选择的权限
	 */
	// 把已经分配好的权限的id值存到一个数组中
	var ids = $.map($(".menus_selected option"), function(item) {
		return item.value;
	});
	// 迭代所有的权限id
	$.each($(".menus_all option"), function(index, item) {
		var id = item.value;
		if ($.inArray(id, ids) >= 0) {
			$(item).remove();
		}
	});

	/*
	 * 选择的时候自动选定:selected
	 */
	$("#editForm").submit(function() {
		$(".menus_selected option").prop("selected", true);
	});
});
