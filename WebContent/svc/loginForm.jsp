<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="setting.jsp" %>
    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	 <!-- Custom style for this template -->
	<link rel="stylesheet" href="${project}style_loginForm.css">
	<link href="https://fonts.googleapis.com/css?family=Work+Sans" rel="stylesheet">
	<script src="${project}script.js"></script>
	
    <title>${page_login}</title>
   
  </head>
  
  <body class="text-center">
  	<form class="form-signin" method="post" action="loginPro.go" name="loginform" onsubmit="" >
  	
  		<a href="${project}list.jsp">
  			<img class="mb-4" src="img/logo.jpg" alt="logo" width="400" height="250">
  		</a>
     	<input type="text" name="user_id" id="inputId" class="form-control" 
     		placeholder="${str_id}" required autofocus>
 	 	<input type="password" name="passwd" id="inputPassword" class="form-control" 
 	 		placeholder="${str_passwd}" required>
  		<button class="btn btn-lg btn-secondary btn-block" type="submit">${btn_login}</button>
  		
  		<p class="mt-5 mb-3 text-muted">&copy; 2018-2019</p>
  		
  	</form>
  
  </body>
</html>
   