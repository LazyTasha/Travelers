<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_admin.css">
<script src="${project}script.js"></script>
	
<h3>|${str_photo_m}</h3>
<section>
	<p><input type="checkbox" >${str_select_photo}</p>
	<div class="buttonarea">
		<input class="inputbutton" type="button" value="${btn_delete}">
	</div>
</section>
<section>
	<form>
		<c:forEach var="i" begin="1" end="6" step="1"> 
			<div class="photoarea">
				<input type="checkbox">
				<span class="photo">
					<img class="img" src="/Travelers/adm/image.JPG"/>
				</span>
			</div>
		</c:forEach>
	</form>
</section>