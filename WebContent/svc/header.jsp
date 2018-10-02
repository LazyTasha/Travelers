<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" type="text/css"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<link href="https://fonts.googleapis.com/css?family=Work+Sans" rel="stylesheet">
	
<style type="text/css">
.login_section a{ 
	text-align: right !important;
	color: white;
}
a { 
	text-decoration:none; 
}
a:hover{
	background:none;
	color:inherit
	}

</style>		
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

<!-- Toggler button -->
	 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
  	  <span class="navbar-toggler-icon"></span>
     </button>
  	
  	
<!-- Image and text -->

	 <a class="navbar-brand" href="${project}list.go">
	   <img src="img/logo_c.png" width="30" height="30" class="d-inline-block align-top">
	    Travelers
	 </a>
	
<!-- Navbar content -->
   <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="#">${page_board_eng}</a>
      </li>
      <li class="nav-item">	
        <a class="nav-link" href="#">${page_calendar_eng}</a>
      </li>
    </ul>
   </div>
   
<!-- Navbar login/sign-up section -->
   <div class="login_section">
	 	<a href="loginForm.jsp" class="nav-item">${page_login}</a> | <a href="regForm.jsp">${page_input}</a>
   </div>

</nav>
</body>
</html>