<%@page import="db.UserDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<script src="${project}script.js"></script>

<h2> ${page_modify} </h2>

<c:if test="${result ne 1}">
	<script type="text/javascript">
		<!--
		alert( modifyerror );
		//-->
	</script>
	<meta http-equiv="refresh" content="0; url=list.go">
</c:if>
<c:if test="${result eq 1}">
	<c:redirect url="list.go"/>	
</c:if>


















