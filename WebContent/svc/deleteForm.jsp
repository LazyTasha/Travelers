<%@page import="db.UserDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<link rel="stylesheet" type="text/css" href="${project}style_member.css">
<script src="${project}script.js"></script>

<h2> ${page_delete} </h2>

<body onload="passwdfocus()">
	<form name="passwdform" method="post" action="memberdeletePro.go" onsubmit="return passwdcheck()">
		<table>
			<tr>
				<th colspan="2">
					${msg_passwdform}
				</th>
			</tr>
			<tr>
				<th> ${str_passwd} </th>
				<td> 
					<input class="input" type="password" name="passwd" maxlength="15">
				</td>				
			</tr>
			<tr>
				<th colspan="2">
					<input class="inputbutton" type="submit" value="${btn_del}">
					<input class="inputbutton" type="button" value="${btn_del_cancel}"
						onclick="location='membermain.go'">
				</th>
			</tr>
		</table>
	</form>	
</body>











