<%@page import="java.text.SimpleDateFormat"%>
<%@page import="db.UserDataBean"%>
<%@page import="db.UserDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<link rel="stylesheet" type="text/css" href="${project}style_member.css">
<script src="${project}script.js"></script>

<h2> ${page_modify} </h2>

<c:if test="${result eq 1}">
	<body onload="modifyfocus()">
		<form method="post" id="modifyform" action="userModifyPro.go" name="modifyform" onsubmit="return modifycheck()">
			<table>
				<tr>
					<th colspan="2">
						${msg_modify}
					</th>
				</tr>
				<tr>
					<th> ${str_id} </th>
					<td> &nbsp;${userDto.user_id} </td>
				</tr>
				<tr>
					<th rowspan="2"> 
						${str_passwd} 
					</th>
					<td>
						<input class="input" type="password" name="passwd" maxlength="15"
							value="${userDto.passwd}">
					</td>
				</tr>
				<tr>
					<td>
						<input class="input" type="password" name="repasswd" maxlength="15"
							value="${userDto.passwd}">
					</td>
				</tr>
				<tr>
					<th> ${str_nickname} </th>
					<td> 
					<input class="input" type="text" name="user_name" maxlength="15"
							value="${userDto.user_name}" id="val_name2">
					<input class="inputbutton" type="button" value="중복확인" onclick="namecheck()">
					<h4 style="color: red;" id="passwordCheckMessageggggg"></h4>
					</td>
				</tr>

				<tr>	
					<th> ${str_email} </th>
					<td>
					&nbsp;${userDto.email}
					</td>
				</tr>
				<tr>
					<th> ${str_reg_date} </th>
					<td>	
						<fmt:formatDate value="${userDto.reg_date}" pattern="yyyy-MM-dd HH:mm"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<input class="inputbutton" type="submit" value="${btn_mod}">
						<input class="inputbutton" type="reset" value="${btn_cancel}">
						<input class="inputbutton" type="button" value="${btn_mod_cancel}"
							onclick="location='tripList.go'">
					</th>
				</tr>
			</table>
		</form>		
	</body>
</c:if>
<c:if test="${result ne 1}">		
	<script type="text/javascript">
		//<!--
		erroralert( loginpasswderror );
		//-->
	</script>
</c:if>