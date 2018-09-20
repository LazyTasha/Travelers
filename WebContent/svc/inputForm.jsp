<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="setting.jsp" %>
<link rel="stylesheet" type="text/css" href="${project}style_member.css">
<script src="${project}script.js"></script>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

<h2> ${page_input} </h2>

<body onload="inputfocus()">
	<form name="inputform" id="inputform" method="post" action="inputPro.go" onsubmit="return inputcheck()">
		<table>
			<tr>
				<th colspan="2">
					${msg_input}
				</th>
			</tr>
			<tr>
				<th> * ${str_id} </th>
				<td>
					<input class="input" type="text" name="user_id" maxlength="15" id="id_val">
					<input class="inputbutton" type="button" value="중복확인" onclick="overlapCheck()">
						<h4 style="color: red;" id="passwordCheckMessagegg"></h4>
						<!-- <div id="divInputId"></div> -->
				</td>
			</tr>
			<tr>
				<th rowspan="2"> * ${str_passwd} </th>
				<td>
					<input class="input" type="password" name="passwd" maxlength="15" id="userPassword1"
						onkeyup="passwordCheckFunction()">
				</td>
			</tr>
			<tr>
				<td>
					<input class="input" type="password" name="repasswd" maxlength="15" id="userPassword2"
						onkeyup="passwordCheckFunction()">
						<h4 style="color: red;" id="passwordCheckMessage"></h4>
				</td>				
			</tr>
			<tr>
				<th> * ${str_n_name} </th>
				<td>
					<input class="input" type="text" name="user_name" maxlength="20" id="name_val">
					<input class="inputbutton" type="button" value="중복확인" onclick="over()" >
						<h4 style="color: red;" id="passwordCheckMessageggg"></h4>
				</td>
			</tr>
			
			<tr>
				<th> ${str_gender}</th>
				<td>
					<input type="radio" name="gender" value="1">남자
					<input type="radio" name="gender" value="2">여자
				</td>
			</tr>
			
			<tr>
				<th> ${str_email} </th>
				<td>
					<input class="input" type="text" name="email1" maxlength="15" style="width: 100px">
					@ 
					<select name="email2">
						<option value="0"> ${str_manual} </option>
						<option value="naver.com"> naver.com </option>
						<option value="daum.net"> daum.net </option>
						<option value="nate.com"> nate.com </option>
						<option value="gmail.co.kr"> google.com </option>
					</select>
				</td>
			</tr>
			
			<tr>
				<th colspan="2">
					<input class="inputbutton" type="submit" value="${btn_join}">
					<input class="inputbutton" type="reset" value="${btn_cancel}">
					<input class="inputbutton" type="button" value="${btn_join_cancel}"
						onclick="location='main.go'">					 
				</th>
			</tr>			
		</table>
	</form>
</body>














    