<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_admin.css">
<script src="${project}script.js"></script>
<h3>|${str_tag_mod}</h3>
<form method="post" action="adminTagMng.go">
	<input type="hidden" value="${state}"/>
	<section>
			<div class="buttonarea">
				<input class="inputbutton" type="button" value="${btn_cancel}" onclick="setCancel()">
				<input class="inputbutton" type="button" value="${btn_check}">
			</div>
	</section>
	<section>
			<table>
				<tr> 
					<th>${str_tagid}</th>
					<th>${str_tag}</th>
				</tr>
				<tr>
					<td><input class="input" type="text" autofocus="autofocus"></td>
					<td><input class="input" type="text" autofocus="autofocus"></td>
				</tr>
			</table>
	</section>
</form>