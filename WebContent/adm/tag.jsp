<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_admin.css">
<script src="${project}script.js"></script>
<jsp:include page='head.jsp' flush="false"/>
<jsp:include page='list.jsp' flush="false"/>	
<article>
	<h3>|${str_tag_m}</h3>
	<section>
		<br>
		<input id="on" class="listbutton" type="button" value="${str_tag_v}">
	</section>
	<section>
		<p><input type="checkbox" id="checkAll">${str_select_all}</p>
	
			<div class="buttonarea">
				<input class="inputbutton" type="submit" value="${btn_delete}">
				<input class="inputbutton" type="button" value="${btn_modify}" onclick="goModTag()">
				<input class="inputbutton" type="button" value="${btn_add}" onclick="goAddTag()">
			</div>

	</section>
	<section>
		<form id="tagForm">
			<table>
				<tr>
					<th class="check"><input type="checkbox" disabled="disabled" ></th>
					<th>${str_tagid}</th>
					<th>${str_tag}</th>
				</tr>
				<c:if test="${count ne 0}">	
					<c:forEach var="tag" items="${tags}">
						<tr>
							<td class="check" align="center"><input type="checkbox"></td>
							<td>${tag.tag_id}</td>
							<td>${tag.tag_value}</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<br>
				<c:if test="${count ne 0}">
					<c:if test="${startPage gt pageBlock}">
						<a href="adminTag.go">[◀◀] </a>
						<a href="adminTag.go?pageNum=${startPage-pageBlock}">[◀] </a>
					</c:if>
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:if test="${i eq currentPage}">
							<p>[${i}]<p>
						</c:if>
						<c:if test="${i ne currentPage}">					
							<a href="adminTag.go?pageNum=${i}">[${i}] </a>
						</c:if>	
					</c:forEach>
					<c:if test="${pageCount gt endPage}">
						<a href ="adminTag.go?pageNum=${startPage+pageBlock}">[▶]</a>
						<a href ="adminTag.go?pageNum=${pageCount}">[▶▶]</a>
					</c:if>	
				</c:if>
			</form>
	</section>
</article>