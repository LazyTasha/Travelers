<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<script src="${project}script.js"></script>
<h2>photoDel</h2>
<c:if test="${result eq 0}">
	<script type="text/javascript">
		<!--
			erroralert(photodeleteerror);
		//-->
	</script>
</c:if>
<c:if test="${result eq 1}">
	<script type="text/javascript">
		<!--
			<c:redirect url="trip.go?tb_no=${tb_no}&tab=1"/>
		//-->
	</script>
</c:if>
