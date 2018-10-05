<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/svc/setting.jsp"%>
<script src="//code.jquery.com/jquery.js"></script>
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
		<div class="row">
		<c:if test="${start gt size}">
			<button onclick="previous(${start},${size})">◀</button>
		</c:if>
			<jsp:include page='boardAlbum.go?tb_no=${tb_no}&start=${start}&end=${end}&tab=${tab}' flush="false"/>
		<c:if test="${start lt count}">
			<button onclick="next(${start},${size})">▶</button>
		</c:if>
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

</article>
<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBnBlipOjNesyFkAIAlXO9WkkIhfiqUIi4&callback=initMap">
</script>

<!-- comment -->
<div class="container">
        <label for="content">comment</label>
        <form name="commentInsertForm" method="post">
            <div class="input-group">
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
