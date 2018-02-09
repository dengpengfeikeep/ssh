$(function() {
	// 首页,尾页,下一页,上一页
	$(".page_btn").click(function() {
		var page = $(this).data("page");
		$("input[name='qo.currentPage']").val(page);
		$("#searchForm").submit();
	});
	// 选择一页显示多少
	$(":input[name='qo.pageSize']").change(function() {
		$("#searchForm").submit();
	});
	// 手动选择页码
	$(".page_submit").click(function() {
		$("#searchForm").submit();
	});
});