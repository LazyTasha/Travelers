<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="setting.jsp" %>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Album</title>
	<script src="${project}script.js"></script>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="style_album.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Work+Sans" rel="stylesheet">
  </head>
  <body id="album">
    <header>

      <div class="navbar navbar-dark bg-dark shadow-sm">
        <div class="container d-flex justify-content-between">
          <a href="#" class="navbar-brand d-flex align-items-center">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="mr-2"><path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"></path><circle cx="12" cy="13" r="4"></circle></svg>
               Album
          </a>
        </div>
      </div>
    </header>

   <main role="main">
      <section class="jumbotron text-center">
        <div class="container">
          <h1 class="jumbotron-heading">Travelers Album</h1>
          <p class="lead text-muted">순간의 순간</p>
          <p>
         <%--	<c:if test="${isMember eq true}"> --%>
           	 	<a class="btn btn-primary my-2" onclick="uploadPhotos()">사진 업로드</a><br>
           	 	<a id="select" class="btn btn-primary my-2" onclick="selectPhotos()">사진 선택 </a>
           	 	<a id="download" class="btn btn-primary my-2" onclick="downloadPhotos()"  style="display:none">사진 다운로드</a>
           	<%-- </c:if> --%>
            <form id="uploadForm" action="albumPro.go" method="post" enctype="multipart/form-data">
               	<input type="file" name="files" multiple="multiple" id="file" accept=".gif, .jpg, .png" style="display:none"/>
               	<input type="hidden" name="tb_no" value="${tb_no}"/>
 			 </form>
            <a href="#" class="btn btn-secondary my-2">둘러보기</a>
          </p>
        </div>
      </section>

      <div class="album py-5 bg-light">
        <div class="container">
			<c:if test="${count gt 0}">
	          <div class="row">
		          <c:forEach var="photo" items="${album}">

		           <div class="col-md-4" id="photoArea">
		           <input type="checkbox" name='check1' style="display:none">
		              <div class="card mb-4 shadow-sm">
		                <img class="card-img-top" data-src="holder.js/100px225?theme=thumb&bg=55595c&fg=eceeef&text=Thumbnail" alt="Card image cap" src="${photo.photo_url}">
		                <div class="card-body">
		                  <p class="card-text">프랑스</p>
		                  <div class="d-flex justify-content-between align-items-center">
		                    <div class="btn-group">
		                      <button type="button" class="btn btn-sm btn-outline-secondary" disabled>#해시태그</button>
		                      <button type="button" class="btn btn-sm btn-outline-secondary" disabled>#해시태그</button>
		                    </div>
		                    <small class="text-muted"><fmt:formatDate value="${photo.alb_reg_date}" pattern="yyyy-MM-dd HH:mm"/></small>
		                  </div>
		                </div>
		              </div>
		            </div>
	             </c:forEach>
	          </div>
	         
          </c:if>
        </div>
      </div>

    </main>

    <footer class="text-muted">
      <div class="container">
        <p class="float-right">
          <a href="#">Back to top</a>
        </p>
        <p>New to Travelers? <a href="../../">Visit the homepage</a></p>
      </div>
    </footer>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../assets/js/vendor/popper.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <script src="../../assets/js/vendor/holder.min.js"></script>
  </body>
</html>
 
