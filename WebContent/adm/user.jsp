<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_admin.css">
<script src="${project}script.js"></script>
<jsp:include page='head.jsp' flush="false"/>
<jsp:include page='list.jsp' flush="false"/>	
<article>
	<h3>|${str_member_m}</h3>
	<section>
		<br>
		<input id="on" class="listbutton" type="button" value="${str_member_v}">
	</section>
	<section>
		<p><input type="checkbox" id="checkAll">${str_select_all}</p>
		<div class="buttonarea">
			<input class="inputbutton" type="button" value="${btn_delete}">
		</div>
	</section>
	<section>
		<form id="user_table">
			<table>
				<tr>
					<th class="check"><input type="checkbox" disabled="disabled" ></th>
					<th>${str_id}</th>
					<th>${str_name}</th>
					<th>${str_email}</th>
					<th>${str_gender}</th>
					<th>${str_join_date}</th>
				</tr>
				<c:if test="${count ne 0}">	
					<c:forEach var="user" items="${users}">
						<tr>
							<td class="check" align="center"><input type="checkbox"></td>
							<td>${user.user_id}</td>
							<td>${user.user_name}</td>
							<td>${user.email}</td>
							<td>${user.gender}</td>
							<td><fmt:formatDate value="${user.reg_date}" pattern="yyyy-MM-dd HH:mm"/></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<br>
				<c:if test="${count ne 0}">
					<c:if test="${startPage gt pageBlock}">
						<a href="adminUser.go">[◀◀] </a>
						<a href="adminUser.go?pageNum=${startPage-pageBlock}">[◀] </a>
					</c:if>
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:if test="${i eq currentPage}">
							<p>[${i}]<p>
						</c:if>
						<c:if test="${i ne currentPage}">					
							<a href="adminUser.go?pageNum=${i}">[${i}] </a>
						</c:if>	
					</c:forEach>
					<c:if test="${pageCount gt endPage}">
						<a href ="adminUser.go?pageNum=${startPage+pageBlock}">[▶]</a>
						<a href ="adminUser.go?pageNum=${pageCount}">[▶▶]</a>
					</c:if>	
				</c:if>
		</form>
	</section>
</article>