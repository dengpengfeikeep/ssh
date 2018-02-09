// 加载当前日期
function loadDate() {
	var time = new Date();
	var myYear = time.getFullYear();
	var myMonth = time.getMonth() + 1;
	var myDay = time.getDate();
	if (myMonth < 10) {
		myMonth = "0" + myMonth;
	}
	document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "."
			+ myDay;
}

$(function() {
	// 文档加载完成,默认加载business的菜单
	loadMenus('business');
	loadDate();
	// 切换菜单按钮样式和标题
	$("#TabPage2 li").click(
			function() {
				// 每次点击,加载不同的菜单
				var sn = $(this).data("rootmenu");
				loadMenus(sn);
				// 每次都删除全部样式
				$.each($("#TabPage2 li"), function(index, item) {
					$(item).removeClass("selected").children("img").prop(
							"src",
							"/ssh/images/common/" + ($(item).index() + 1)
									+ ".jpg");
				});
				var index = $(this).index() + 1;// 第N个菜单,从0开始
				$(this).addClass("selected");
				$(this).children("img").prop("src",
						"/ssh/images/common/" + index + "_hover.jpg");
				// 切换标题
				$("#nav_module").children("img").prop("src",
						"/ssh/images/common/module_" + index + ".png");
			});
});

// 设置简单json风格
var setting = {
	async : {
		enable : true,
		url : "systemMenu_loadMenus",
		autoParam : [ "id=systemMenu.id" ],
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick : function(event, treeId, treeNode) {
			$("#here_area").html("当前位置：系统&nbsp;>&nbsp;" + treeNode.name);
			$("#rightMain").prop("src", treeNode.action);
		}
	}
};
// 菜单树
var zNodes = {
	'system' : [ {
		id : 1,
		pId : 0,
		name : "系统管理",
		isParent : true
	} ],
	'business' : [ {
		id : 2,
		pId : 0,
		name : "业务模块",
		isParent : true
	} ],
	'chart' : [ {
		id : 3,
		pId : 0,
		name : "报表管理",
		isParent : true
	} ]
};
// 加载菜单
function loadMenus(sn) {
	$.fn.zTree.init($("#dleft_tab1"), setting, zNodes[sn]);
};
