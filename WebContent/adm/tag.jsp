<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_admin.css">
<script src="${project}script.js"></script>
	
<h3>|태그관리</h3>
<section>
	<br>
	<input id="on" class="listbutton" type="button" value="${str_tag_v}">
</section>
<section>
	<p><input type="checkbox" >${str_select_tag}</p>
	<div class="buttonarea">
		<input class="inputbutton" type="button" value="${btn_delete}">
		<input class="inputbutton" type="button" value="${btn_modify}">
		<input class="inputbutton" type="button" value="${btn_add}">
	</div>
</section>
<section>
	<form>
		<table>
			<tr>
				<th class="check" colspan="3"><input type="checkbox" disabled="disabled" ></th>
				<th>${str_tagid}</th>
				<th>${str_tag}</th>
			</tr>
		</table>
	</form>
</section>