<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_admin.css">
<script src="${project}script.js"></script>
	
<h3>|${str_list_m}</h3>
<section>
	<br>
	<input class="listbutton" type="button" value="${str_content_v}">
	<input id="on" class="listbutton" type="button" value="${str_comment_v}">
</section>
<section>
	<p><input type="checkbox" >${str_select_w}</p>
	<div class="buttonarea">
		<input class="inputbutton" type="button" value="${btn_delete}">
	</div>
</section>
<section>
	<form>
		<table>
			<tr>
				<th class="check" colspan="3"><input type="checkbox" disabled="disabled"></th>
				<th>${str_id}</th>
				<th>${str_comment}</th>
				<th>${str_reg_date}</th>
			</tr>
		</table>
	</form>
</section>