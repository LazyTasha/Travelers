<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>게시판</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- Custom styles for this template -->
<link href="https://fonts.googleapis.com/css?family=Work+Sans"
	rel="stylesheet">
<style type="text/css">
a {
	color: black;
}
aside {
   position: relative;
   width:20%;
   height:100%;
   min-height:800px;
   margin:auto;
   padding-top: 15px;
   padding-right: 20px;
   padding-left: 20px;
   float:left;
   border-style: dotted;
}
.board-header {
	line-height: 1;
	border-bottom: 1px solid #e5e5e5;
}
.blog-header-logo {
	font-family: "Playfair Display", Georgia, "Times New Roman", serif;
	font-size: 2.25rem;
}
.body-box { 
   padding-top: 20px;
   padding-right: 80px;
   padding-left: 80px;
}
h1, h2, h3, h4, h5, h6 {
	font-family: "Playfair Display", Georgia, "Times New Roman", serif;
}

@media ( min-width : 768px) {
	.display-4 {
		font-size: 3rem;
	}
}
.nav-scroller {
	position: relative;
	z-index: 2;
	height: 2.75rem;
	overflow-y: hidden;
}
.nav-scroller .nav {
	display: -ms-flexbox;
	display: flex;
	-ms-flex-wrap: nowrap;
	flex-wrap: nowrap;
	padding-bottom: 1rem;
	margin-top: -1px;
	overflow-x: auto;
	text-align: center;
	white-space: nowrap;
	-webkit-overflow-scrolling: touch;
}
.nav-scroller .nav-link {
	padding-top: .75rem;
	padding-bottom: .75rem;
	font-size: .875rem;
}
.card-img-right {
	height: 100%;
	border-radius: 0 3px 3px 0;
}
.flex-auto {
	-ms-flex: 0 0 auto;
	flex: 0 0 auto;
}
.h-250 {
	height: 250px;
}
@media ( min-width : 768px) {
	.h-md-250 {
		height: 250px;
	}
}
/* Footer*/
.board-footer {
	padding: 2.5rem 0;
	text-align: center;
	background-color: #f9f9f9;
	border-top: .05rem solid #e5e5e5;
}
.board-footer p:last-child {
	margin-bottom: 0;
}
</style>
</head>

<body>
<!-- header -->
      <header class="board-header py-2">
        <div class="row flex-nowrap justify-content-between align-items-center">
          <div class="col-4 pt-2 text-left">
            <a href="${project}list.go">
			   <img src="img/logo_hz.png" width="200px" height="100px" class="d-inline-block align-top">    
			</a>
          </div>
	  <c:if test="${sessionScope.memid eq null}">
		  <div class="col-4 d-flex justify-content-end align-items-center">
		    <!-- Header login/sign-up section -->	
				<a href="loginForm.jsp" class="nav-item">${page_login}</a> &nbsp; 
				| &nbsp; <a href="regForm.jsp">${page_input}</a>&nbsp;
		  </div>
	 </c:if>
         <c:if test="${sessionScope.memid ne null}">
        	<div class="col-4 d-flex justify-content-end align-items-center">
        		<!-- if user has been logged in -->
			 	<a href="userLoginForm.go" class="nav-item">${page_mypage2}</a>
		</div>
         </c:if>
        </div>
      </header>
<!-- navigation bar -->
      <nav class="nav d-flex">
        <a class="p-2 text-muted" href="#">Board</a>
        <a class="p-2 text-muted" href="#">Calendar</a>
      </nav> 
<!-- Category & Contents Box -->    
	<div class="body-box">
     	<div class="d-flex justify-content-end">
	     	<a href="${project}tripWriteForm.go">
	     		<img src="img/compose_icon.png" width="120" height="40">
	     	</a> 
     	</div>
	  	<div class="board-list">
     	<br>
	
     	<div class="row">
        	<div class="col-md-12">
          		<div class="card flex-md-row mb-3 shadow-sm h-md-250">
           			 <div class="card-body d-flex flex-column align-items-start">
			              <strong class="d-inline-block mb-2">City, Country</strong>
			              <h3 class="mb-0">
			                <a class="text-dark" href="#">Title</a>
			              </h3>
	              		  <div class="mb-1 text-muted"><i><b>With</b></i>&nbsp; Nickname</div>
	              		  <hr style="width: 100%">
	              		  <div class="d-flex justify-content-center">
							  <div class="p-2">일정: 2019/01/01~2019/01/27</div>&nbsp;
							  <div class="p-2">인원: 6</div>&nbsp;
							  <div class="p-2">조회수: 3</div>&nbsp;
						  </div>
	           		 </div>
	          		 <img class="card-img-right flex-auto d-none d-lg-block" data-src="holder.js/200x250?theme=thumb" alt="Card image cap">
         		</div>
    		</div>
    	</div><!-- row 1 -->
    	<div class="row">
        	<div class="col-md-12">
          		<div class="card flex-md-row mb-3 shadow-sm h-md-250">
           			 <div class="card-body d-flex flex-column align-items-start">
			              <strong class="d-inline-block mb-2">Barcelona, Spain</strong>
			              <h3 class="mb-0">
			                <a class="text-dark" href="#">Title : Anyone in Sitges?</a>
			              </h3>
	              		  <div class="mb-1 text-muted"><i><b>With</b></i>&nbsp; Nickname</div>
		             	      <hr size="1px" color="black" noshade>
		              		  <p class="card-text mb-auto">This is a wider card with supporting text below as a natural lead-in to additional content.</p>
		           			  <a href="#">Continue reading</a>
	           			  </div>
	          			  <img class="card-img-right flex-auto d-none d-lg-block" data-src="holder.js/200x250?theme=thumb" alt="Card image cap">
         			 </div>
    		 </div>
    	</div><!-- row 2 -->
    	<div class="row">
        	<div class="col-md-12">
          		<div class="card flex-md-row mb-3 shadow-sm h-md-250">
           			 <div class="card-body d-flex flex-column align-items-start">
			              <strong class="d-inline-block mb-2">Barcelona, Spain</strong>
			              <h3 class="mb-0">
			                <a class="text-dark" href="#">Title : Anyone in Sitges?</a>
			              </h3>
	              		  <div class="mb-1 text-muted"><i><b>With</b></i>&nbsp; Nickname</div>
		             	      <hr size="1px" color="black" noshade>
		              		  <p class="card-text mb-auto">This is a wider card with supporting text below as a natural lead-in to additional content.</p>
		           			  <a href="#">Continue reading</a>
	           			  </div>
	          			  <img class="card-img-right flex-auto d-none d-lg-block" data-src="holder.js/200x250?theme=thumb" alt="Card image cap">
         			 </div>
    		 </div>
    	</div><!-- row 3 -->
    	<button type="button" class="btn btn-dark col-md-12">Load more...</button>
    </div><!-- board list -->	
</div><!-- body box -->	
		
<!-- Footer -->   
    <footer class="board-footer">
      <p>
        <a href="">Back to top</a>
      </p>
    </footer>
</body>
</html>
