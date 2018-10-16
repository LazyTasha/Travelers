<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
<script src="//code.jquery.com/jquery.js"></script>
<script src="${project}script.js"></script>
</head>

<body>
<!-- Category & Contents Box -->    
	<div class="body-box">
		  <div class="d-flex justify-content-end">
        <c:if test="${sessionScope.user_id eq null}">
          <a href="login.go">
        </c:if>
        <c:if test="${sessionScope.user_id ne null}">
          <a href="tripWrite.go">
        </c:if>
          <img src="${project}img/compose_icon.png" width="120" height="40">
        </a> 
     	</div>
	  	<div class="board-list">
	     	<c:if test="${count ne 0}">
	    		<c:forEach var="i" items="${tripList}">
		    		<div class="row">
			        	<div class="col-md-12">
			          		<div class="card flex-md-row mb-3 shadow-sm h-md-250">
			           			 <div class="card-body d-flex flex-column align-items-start">
					              <strong class="d-inline-block mb-2">
					              	<c:forEach var="j" items="${i.locs}">
					              		${j}
					              	</c:forEach>
					              </strong>
					              <h3 class="mb-0">
					                 <a class="text-dark" href="trip.go?tb_no=${i.tb_no}">${i.tb_title}</a>
					              </h3>
			              		  <div class="mb-1 text-muted"><i><b>With</b></i>&nbsp; ${i.user_id}</div>
				             	      <hr size="1px" color="black" noshade>
				              		  <p class="card-text mb-auto">${i.tb_content}</p>
				              		  	 <hr style="width: 100%"> 
								  <div class="d-flex justify-content-center">
				           			  	<div class="p-2">일정:2019.02.11~2019.02.21</div>&nbsp;
									<div class="p-2">인원:${i.tb_m_num}</div>&nbsp;
									<div class="p-2">조회수:${i.tb_v_count}</div>&nbsp;
									 <div class="p-2">
				           			  	<c:forEach var="j" items="${i.tags}">
				           			  		<label class="btn btn-secondary">
				           			  			# ${j}
				           			  		</label>
				           			  	</c:forEach>
				           			  </div>
				           			  </div>
			           			  </div>
			          			  <img class="card-img-right flex-auto d-none d-lg-block" data-src="holder.js/200x250?theme=thumb" alt="Card image cap">
		         			 </div>
			    		 </div>
			    	</div>
	    		</c:forEach>
	    		<form name="tripListInfo">
	    			<input type="hidden" name="last_row" value="${last_row}">
	    		</form>
	    		<div class="row" id="board-append-list">
	    		</div>
	    	</c:if>
    	<button type="button" class="btn btn-dark col-md-12" onclick="loadMoreList(${last_row})">Load more...</button>
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
