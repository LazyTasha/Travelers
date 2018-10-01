<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<script src="${project}script.js"></script>
<h2>adminUserDel</h2>
<c:if test="${result eq 0}">
	<script type="text/javascript">
		<!--
			alert(deleteerror);
			
		//-->
	</script>
</c:if>
<c:if test="${result eq 1}">
	<script type="text/javascript">
		<!--
			window.opener.location.href=window.opener.document.URL;
			self.close();
		//-->
	</script>
</c:if>

