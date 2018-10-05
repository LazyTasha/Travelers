<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/logo_c.png">

    <title>Travelers</title>

    <!-- Bootstrap core CSS -->
  	<link rel="stylesheet" type="text/css"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <!-- Custom styles for this template -->
    <style>    
	body {
	  min-height: 75rem;
	  padding-top: 4.5rem;
	}  
	a.nav-item { 
		color: white !important; 
	} 
    </style>
  </head>

  <body>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
      <a class="navbar-brand" href="${project}list.go">
     	<img src="img/logo_c.png" width="30" height="30" class="d-inline-block align-top"> 
     	Travelers
      </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link" href="${project}list.go">${page_main_eng} <span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${project}list.go">${page_board_eng}</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">${page_calendar_eng}</a>
          </li>
        </ul>
        <form class="form-inline mt-2 mt-md-0 login-section">
          <a href="${project}loginForm.go" class="nav-item">${page_login}</a> 
          &nbsp;|&nbsp; 
          <a href="${project}regForm.go" class="nav-item">${page_input}</a>
        </form>
      </div>
    </nav>

	<!-- Bootstrap core JavaScript (**Essential for Toggler action)
	================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	</body>
<<<<<<< HEAD
</html>
=======
</html>
>>>>>>> master
