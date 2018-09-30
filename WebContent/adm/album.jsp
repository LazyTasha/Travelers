<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_admin.css">
<script src="${project}script.js"></script>

<jsp:include page='head.jsp' flush="false"/>
<jsp:include page='list.jsp' flush="false"/>	
<article>
	<h3>|${str_photo_m}</h3>
	<section>
		<p><input type="checkbox" id="checkAll">${str_select_all}</p>
		<div class="buttonarea">
			<input class="inputbutton" type="button" value="${btn_delete}">
		</div>
	</section>
	<section>
		<form>
			<c:forEach var="i" begin="1" end="20" step="1"> 
				<div class="photoarea">
					<input type="checkbox" name='check1'>
					<span class="photo">
						<img class="img" src="/Travelers/adm/images/image.JPG"/>
					</span>
				</div>
			</c:forEach>
		</form>
	</section>
</article>