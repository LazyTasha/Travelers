<%@page import="handler.SvcProHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@include file="setting.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- Custom style for this template -->
<link rel="stylesheet" href="${project}style_loginForm.css">
<link href="https://fonts.googleapis.com/css?family=Work+Sans" rel="stylesheet">
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) -->
<script src="//code.jquery.com/jquery.js"></script>
<script src="${project}script.js"></script>
</head>

<body>
<h2>이메일인증</h2>

<c:if test="${result eq 0}"> <!-- 이메일이 없을때-->
	<form method="post" action="" name="emailCheck">
	<table>
		<tr>
			<th colspan="2">인증번호를 입력하세요.</th>
		</tr>
		<tr>
			<td>
				<input name="input" type="text" name="emailconfirm" id="EmailVlaue" >
				<input name="inputbutton" type="button" value="인증" 
                 onclick="confirmeMail('${authNum}')">
                 <input type="hidden" name="confirm" value="0">	<!-- 인증성공시 1로변함 -->
			</td>
		</tr>
	</table>
	</form>
</c:if>

<c:if test="${result eq 1}"><!-- 이메일이 존재할때 -->
	<table>
			<tr>
				<td align="center">
					<span>${email}</span>이미사용중입니다
				</td>
			</tr>
			<tr>
				<th>
					<input class="inputbutton" type="button" value="확인"
						onclick="EmailClose()">
				</th>
			</tr>
		</table>		
</c:if>
</body>