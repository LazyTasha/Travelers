<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="setting.jsp" %>
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
   <main role="main">
      <section class="jumbotron text-center">
        <div class="container">
          <h1 class="jumbotron-heading">Album</h1>
          <p>
         <%--	<c:if test="${isMember eq true}"> --%>
           	 	<a class="btn btn-primary my-2" onclick="uploadPhotos()">사진 업로드</a>
           	 	<a id="select" class="btn btn-primary my-2" onclick="selectPhotos()">사진 선택 </a>
           	 	<a id="download" class="btn btn-primary my-2" onclick="downloadPhotos()"  style="display:none">사진 다운로드</a>
           	<%-- </c:if> --%>
            <form id="uploadForm" action="boardAlbumPro.go" method="post" enctype="multipart/form-data">
               	<input type="file" name="files" multiple="multiple" id="file" accept=".gif, .jpg, .png" style="display:none"/>
               	<input type="hidden" name="tb_no" value="${tb_no}"/>
 			 </form>
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

		                  <div class="d-flex justify-content-between align-items-center">
		                    <small class="text-muted"><fmt:formatDate value="${photo.alb_reg_date}" pattern="yyyy-MM-dd HH:mm"/></small>
		                  </div>
		              </div>
		            </div>
	             </c:forEach>
	          </div>
	         
          </c:if>
        </div>
      </div>

    </main>

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
 
