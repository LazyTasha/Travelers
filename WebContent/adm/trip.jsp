<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_admin.css">
<script src="${project}script.js"></script>
<jsp:include page='head.jsp' flush="false"/>
<jsp:include page='list.jsp' flush="false"/>	
<article>
	<h3>|${str_list_m}</h3>
	<section>
		<br>
		<input id="on" class="listbutton" type="button" value="${str_content_v}">
		<input class="listbutton" type="button" value="${str_comment_v}">
	</section>
	<section>
		<p><input type="checkbox" id="checkAll">${str_select_all}</p>
		<div class="buttonarea">
			<input class="inputbutton" type="button" value="${btn_delete}">
			<input class="inputbutton" type="button" value="${btn_notice}">
		</div>
	</section>
	<section>
		<form>
			<table>
				<tr>
					<th class="check"><input type="checkbox" disabled="disabled"></th>
					<th>${str_num}</th>
					<th>${str_comment}</th>
					<th>${str_title}</th>
					<th>${str_writer}</th>
					<th>${str_content}</th>
					<th>${str_reg_date}</th>
					<th>${str_notice}</th>
					<th>${str_readcount}</th>
				</tr>
			</table>
		</form>
	</section>
</article>