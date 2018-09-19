<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_admin.css">
<script src="${project}script.js"></script>
<h3>|${str_tag_add}</h3>

<section>
		<p><input type="checkbox" id="checkAll">${str_select_all}</p>
		<div class="buttonarea">
			<input class="inputbutton" type="button" value="${btn_cancel}" onclick="setCancel()">
			<input class="inputbutton" type="button" value="${btn_check}">
		</div>
</section>
<section>
		<table>
			<tr> 
				<th class="check" ><input type="checkbox" disabled="disabled"></th>
				<th >${str_tag}</th>
			</tr>
			<tr>
				<td><input type="checkbox" ></td>
				<td><input class="input" type="text" autofocus="autofocus"></td>
			</tr>
			<tbody id="t">
			</tbody>
			<tr>
				<td colspan="2">
						<button onclick="addRow()">
							<img  class="btn_img" src="/Travelers/adm/images/addbutton.png"> 
						</button>
				</td>
			</tr>
		</table>
</section>