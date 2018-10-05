<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/svc/setting.jsp"%>
<script src="${project}script.js"></script>
<style>
   #map {
   		position:relative;
        height: 400px;  /* The height is 400 pixels */
        width: 100%;  /* The width is the width of the web page */
        border:1px black solid;
       }
</style>
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
	</section>
	
	<!--boardAlbum영역  -->
	<section id="albumTab" style="display:none">
		<jsp:include page='boardAlbum.go?tb_no=${tb_no}' flush="false"/>
	</section>
	<!--ㅡMap영역  -->
	<div id="mapTab">
		<div id="map">지도</div>
		<input type="hidden" value="${lat}" id="lat"/>
		<input type="hidden" value="${lng}" id="lng"/>
	</div>
</article>
<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBnBlipOjNesyFkAIAlXO9WkkIhfiqUIi4&callback=initMap">
</script>
	
