<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/svc/setting.jsp"%>
<table border="1">
	<tr>
		<th>제목</th>
		<td>${tbDto.tb_title}</td>
	</tr>
	<tr>
		<th>글쓴이</th>
		<td>${tbDto.user_id}</td>
	</tr>
</table>