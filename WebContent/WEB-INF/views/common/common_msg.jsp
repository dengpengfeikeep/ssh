<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript">
	<s:if test="hasActionMessages()">
	$.dialog({
		title:'操作提示',
		icon:'face-smile',
		content:'${actionMessages}',
		ok:true,
	});
	</s:if>
	
	<s:if test="hasActionErrors()">
	var msg = '<s:property value="actionErrors[0]" />';
	$.dialog({
		title:'操作提示',
		icon:'face-sad',
		content:msg,
		ok:true,
	});
	</s:if>
</script>
