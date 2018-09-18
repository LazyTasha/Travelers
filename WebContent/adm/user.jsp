<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_admin.css">
<script src="${project}script.js"></script>
	
<h3>|${str_member_m}</h3>
<section>
	<br>
	<input id="on" class="listbutton" type="button" value="${str_member_v}">
</section>
<section>
	<p><input type="checkbox">${str_select_member}</p>
	<div class="buttonarea">
		<input class="inputbutton" type="button" value="${btn_delete}">
	</div>
</section>
<section>
	<form id="user_table">
		<table>
			<tr>
				<th class="check" colspan="6"><input type="checkbox" disabled="disabled" ></th>
				<th>${str_id}</th>
				<th>${str_name}</th>
				<th>${str_email}</th>
				<th>${str_gender}</th>
				<th>${str_join_date}</th>
			</tr>
<%-- 			
			<c:forEach var="user" items="${users}">
				<tr>
					<td class="check" colspan="6" align="center"><input type="checkbox"></td>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.email}</td>
					<td></td>
					<td><fmt:formatDate value="${user.reg_date}" pattern="yyyy-MM-dd HH:mm"/></td>
				</tr>
			</c:forEach>
			 --%>
		</table>
	</form>
</section>