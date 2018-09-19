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
	<form name="tagForm" method="post" action="adminTagMng.go">
	<section>
		<p><input type="checkbox" id="checkAll">${str_select_all}</p>
	
			<div class="buttonarea">
				<input class="inputbutton" type="submit" value="${btn_delete}">
				<input class="inputbutton" type="button" value="${btn_modify}" onclick="modTag()">
				<input class="inputbutton" type="button" value="${btn_add}" onclick="addTag()">
			</div>

	</section>
	<section>
			<table>
				<tr>
					<th class="check"><input type="checkbox" disabled="disabled" ></th>
					<th>${str_tagid}</th>
					<th>${str_tag}</th>
				</tr>
			</table>
		</form>
	</section>
</article>