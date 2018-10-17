<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="setting.jsp" %>
<link rel="stylesheet" type="text/css"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
 <c:if test="${start gt size}">
		<button onclick="previous(${start},${size})">◀</button>
</c:if>
<main role="main">
	 <section class="jumbotron text-center">
	   <div class="container">
	     <h1 class="jumbotron-heading">Album</h1>
	     	<c:if test="${isMember eq true}">
			     <p>
		      	 	<a class="btn btn-primary my-2" onclick="uploadPhotos()">사진 업로드</a>
		      	 	<a id="select" class="btn btn-primary my-2" onclick="selectPhotos()">사진 선택 </a>
		      	 	<a id="download" class="btn btn-primary my-2" onclick="downloadPhotos()" style="display:none">사진 다운로드</a>
		      	 	<a class="btn btn-primary my-2" onclick="downloadAlbum()">앨범 전체 다운로드</a>
			     </p>	 	
	 	    </c:if> 
	        <form id="uploadForm" action="boardAlbumPro.go" method="post" enctype="multipart/form-data">
	          	<input type="file" name="files" multiple="multiple" id="file" accept=".gif, .jpg, .png" style="display:none"/>
	          	<input type="hidden" name="tb_no" value="${tb_no}"/>
			</form>
			<form id="downloadForm" action="download.go" method="post">
			</form>
			<form id="downloadAlbumForm" action="downloadAlbum.go" method="post">
				<input type="hidden" name="tb_no" value="${tb_no}"/>
			</form>
	   </div>
	 </section>
	
	<div class="album py-5 bg-light">
	   <div class="container">
	<c:if test="${count gt 0}">
	       <div class="row">
	        <c:forEach var="photo" items="${album}">
	         <div class="col-md-4" id="photoArea">
		         <input type="checkbox" name='check1' style="display:none">
		         <input type="hidden" name="photo_url" value="${photo.photo_url}"/>
		            <div class="card mb-4 shadow-sm">
		              <img class="card-img-top" data-src="holder.js/100px225?theme=thumb&bg=55595c&fg=eceeef&text=Thumbnail" alt="Card image cap" src="${photo.photo_url}">
		                <div class="d-flex justify-content-between align-items-center">
		                  <small class="text-muted"><fmt:formatDate value="${photo.alb_reg_date}" pattern="yyyy-MM-dd HH:mm"/></small>
		                 	<c:if test="${isMember eq true}">	             
		                  	<button type="button" onclick="deletePhoto(${tb_no},${photo.photo_id},${start})" >${btn_delete}</button>
		                 	</c:if>
	                </div>
	            </div>
	          </div>
	          </c:forEach>
	       </div>
	     </c:if>
	   </div>
	 </div>
 </main>
 <c:if test="${start lt last}">
		<button onclick="next(${start},${size})">▶</button>
</c:if>
 
