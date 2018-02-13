<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>小码哥PSS（演示版）</title>
<link href="${pageContext.request.contextPath }/style/error_css.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/system/login.js"></script>
</head>
<body>
	<div id="login_center">
		<div id="login_area">
			<div id="login_box">
				<div id="login_form">
					<H2>出错啦！</H2>
					<span>执行过程中发生了异常：</span> <span class="error"><s:property
							value="exception.message" /></span> <span>请联系管理员解决！</span> <span>联系电话：XXX</span>
					<span>联系邮件：XXX@XXX.com</span> <span>&copy;美国太空探索技术有限公司</span>
				</div>
			</div>
		</div>
	</div>
</body>
</html>