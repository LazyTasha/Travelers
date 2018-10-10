<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/svc/setting.jsp"%>

<!DOCTYPE html>
<html lang="en">
<style>
   #map{
   		position:relative;
        height: 400px;  /* The height is 400 pixels */
        width: 90%;  /* The width is the width of the web page */
        border:1px black solid;
       }
</style>

<head>
 <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
      
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>board</title>
   
    <!-- Custom styles for boarAlbum template -->
    <link href="style_album.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Work+Sans" rel="stylesheet">
    
    <script src="//code.jquery.com/jquery.js"></script>
	<script src="${project}script.js"></script>
</head>
<body>
	<article>
		<section>
		<table border="1">
			<tr>
				<th>제목</th>
				<td>${tbDto.tb_title}</td>
			</tr>
			<tr>
				<th>글쓴이</th>
				<td>${tbDto.user_id}</td>
			</tr>
		</table>
		</section>
		
		<br>
		<!--button 영역 -->
		<section>
			<button onclick="showMap()">지도</button>
			<button onclick="showAlbum()">사진</button>
			<button onclick="attend()">참석</button><button onclick="attend()">불참</button>
			<div class="sum">
	        <div class="sumsumList"></div>
	    </div>
		</section>
		
		<!--boardAlbum영역  -->
		<c:if test="${tab eq '0'}">
			<section id="albumTab" style="display:none">
		</c:if>
		<c:if test="${tab eq '1'}">
			<section id="albumTab">
		</c:if>
		  	<div class="row" id="album">
					<jsp:include page='boardAlbum.go?tb_no=${tb_no}&start=${start}&tab=${tab}' flush='false'/>
			</div>
		</section>
	
		<!--ㅡMap영역  -->
		<c:if test="${tab eq 0}">
			<div id="mapTab">
		</c:if>
		<c:if test="${tab eq 1}">
			<div id="mapTab" style="display:none">
		</c:if>
			<div id="map">지도</div>
			<input type="hidden" value="${lat}" id="lat"/>
			<input type="hidden" value="${lng}" id="lng"/>
		</div>
		
		<!-- comment -->
		<div class="container">
		        <label for="content">comment</label>
		        <form name="commentInsertForm" method="post">
		            <div class="input-group">
		            	<input type="hidden" name="tb_no" value="${tb_no}"/>
		            	<%-- <input type="hidden" name="number" id="number" value="${tbDto.tb_no}"> --%>
		            	<%-- <input type="hidden" name="c_id" id="c_id" value="${c_id}"> --%>
		            	<%-- <input type="hidden" name="c_id" id="c_id" value="${c_id}"> --%>
		               	<input type="text" class="input" id="c_content" name="c_content" placeholder="내용을 입력하세요.">
		               
		               <span class="input-group-btn">
		                    <button class="btn btn-default" type="button" onclick="commentInsert()">등록</button>
		               </span>
		     		</div>
				</form>
			</div>
		    
		   	<div class="container">
		        <div class="commentList"></div>
		    </div>
		<!-- comment -->
	</article>
</body>
<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBnBlipOjNesyFkAIAlXO9WkkIhfiqUIi4&callback=initMap">
</script>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../assets/js/vendor/popper.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <script src="../../assets/js/vendor/holder.min.js"></script>
	
