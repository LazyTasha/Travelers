<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>로그인 페이지</title>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	 <!-- Custom style for this template -->
	<link rel="stylesheet" href="style_loginForm.css">
   
  </head>
  <body class="text-center">
  	<form class="form-signin">
  		<img class="mb-4" src="img/logo.jpg" alt="" width="400" height="250">
     	<input type="text" id="inputId" class="form-control" placeholder="아이디" required autofocus>
 	 	<input type="password" id="inputPassword" class="form-control" placeholder="비밀번호" required>
  		<button class="btn btn-lg btn-secondary btn-block" type="submit">로그인</button>
  		<p class="mt-5 mb-3 text-muted">&copy; 2018-2019</p>
  	</form>
  
  </body>
</html>
   