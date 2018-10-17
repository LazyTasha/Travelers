<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/svc/setting.jsp"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<style>
#map {
	position: relative;
	height: 400px; /* The height is 400 pixels */
	width: 90%; /* The width is the width of the web page */
	border: 1px black solid;
}
</style>

<head>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Custom styles for boarAlbum template -->
<link href="https://fonts.googleapis.com/css?family=Work+Sans"
	rel="stylesheet">
<link rel="stylesheet" href="${project}travelers_style.css">
<script src="//code.jquery.com/jquery.js"></script>
<script src="${project}script.js"></script>
</head>

<body class="trip">
	<div class="container" style="width: 800px;">
		<div>
			<div id=button_area class="d-flex justify-content-end">
				<!-- 목록 button -->
					<div><input type="button" value="${btn_list}"  onclick="location='tripList.go'" class="btn btn-sm">&nbsp;</div>
				<!--수정/삭제 button -->
				<c:if test="${isOwner eq 1}">
					<div>
						<input type="button" value="${btn_mod}" onclick="modifyBoard(${tb_no})" class="btn btn-sm"> 							
						<input type="button" value="${btn_delete}" onclick="deleteBoard(${tb_no})" class="btn btn-sm">							
					</div>
				</c:if>
				<c:if test="${user_level eq 9}">
					<div>
						<input type="button" value="${btn_delete}" onclick="deleteBoard(${tb_no})"> 
						<input type="button" value="${btn_back_admin}" onclick="goAdminPage()">							
					</div>
				</c:if>
			</div>
			<!--  -->
			<br><input type="hidden" value="${tbDto.tb_notice}" id="notice" />
			<!----- 공지 ----->
			<article>
				<section>
					<c:if test="${tbDto.tb_notice == 1}">
						<img class="mb-4" src="${project}img/logo_c.png" alt=""
							width="100" height="100">
					</c:if>

					<div id="trip_title">
						<div class="row">
							<input type="text" name="trip_title" class="col-12 form-control form-control-lg" value="${tbDto.tb_title}" readonly="readonly">																			
						</div>						
						<div class="row">
								<div class="text-muted">
									<i><b>With</b></i>&nbsp; ${tbDto.user_id}
								</div>
						</div>
						<div class="row pt-2 pb-2">
								<label class="col-2">참여 인원</label>${tbDto.tb_v_count}/${tbDto.tb_m_num}
						</div>						
						<c:forEach var="i" items="${locDtoList}">
						<div class="container" style="width:100%">
								<div class="row">									
									<c:set var="order" value="${i.coord_order}"/>
										<label class="col-2">${trip_schedule}</label>
										<input type="text" class="col-3" value="${i.cal_start_date}" readonly="readonly"/> 
										~
										<input type="text" class="col-3" value="${i.cal_end_date}" readonly="readonly"/>									
								</div><!-- 날짜 일정 -->										
								<div class="row">
									<div class="col-12 offset-2">
										<div class="loc" name="coord">
												<input type="text" name="trip_location${order}" id="address${order}" class="col-8 pt-3" readonly="readonly">
												<input type="hidden" name="coord_long" value="${i.coord_long}">
												<input type="hidden" name="coord_lat" value="${i.coord_lat}">
												<input type="hidden" value="${i.country_name}">																						
												<button onclick="attend()" class="btn btn-sm">참석</button>
												<button onclick="absent()" class="btn btn-sm">불참</button>
										</div><!-- 장소 -->
									</div><!-- column -->
								</div><!-- row -->
							<div id="trip_member_list"></div>
						</div><!-- 일정 Container box-->
						</c:forEach>
						
						<div class="row pt-3 pb-1">								
								<label class="col-2">${tb_talk}</label>
								<a href="${tbDto.tb_talk}" target="_blank">${tbDto.tb_talk}</a>								
						</div>					
					<div id="trip_content">						
						<div class="row">
								<textarea class="col-12" rows="8" readonly>${tbDto.tb_content}</textarea>
						</div>																						
					</div><!-- id: trip_content -->
					</div><!-- id: trip_title -->
				</section>


				<br>
				<!--button 영역 -->
				<section>
					<button onclick="showMap()">지도</button>
					<button onclick="showAlbum()">사진</button>
				</section>

				<!--boardAlbum영역  -->
				<c:if test="${tab eq '0'}">
					<section id="albumTab" style="display: none">
				</c:if>
				<c:if test="${tab eq '1'}">
					<section id="albumTab">
				</c:if>
					<div class="row" id="album">
						<jsp:include
							page='boardAlbum.go?tb_no=${tb_no}&start=${start}&tab=${tab}'
							flush='false' />
					</div>
				</section>

				<!--ㅡMap영역  -->
				<c:if test="${tab eq 0}">
					<div id="mapTab">
				</c:if>
				<c:if test="${tab eq 1}">
					<div id="mapTab" style="display: none">
				</c:if>
					<div id="map">지도</div>
			    	<input type="hidden" value="${lat}" id="lat" /> 
            <input type="hidden" value="${lng}" id="lng" />
					</div>
			</article>

			<!-- comment -->
			<c:if test="${sessionScope.user_id != null}">
				<div class="container">
					<label for="content">comment</label>
					<form name="commentInsertForm" method="post">
						<div class="input-group">
							<input type="hidden" name="tb_no" value="${tb_no}" /> 
							<input type="hidden" name="session" value="${user_id}" /> 
								<input type="text" class="input col-11" id="c_content" name="c_content" placeholder="${trip_entercontent}"> 
								<span class="input-group-btn">
								<button class="btn btn-default" type="button" 	onclick="commentInsert()">등록</button>
							</span>
						</div>
					</form>
				</div>
			</c:if>
			<div class="commentList"></div>
			<!-- comment -->
		</div>
	</div>
</body>
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBnBlipOjNesyFkAIAlXO9WkkIhfiqUIi4&callback=initMap">
</script>

<!-- Bootstrap core JavaScript
    ==================================================
    Placed at the end of the document so the pages load faster -->
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="../../assets/js/vendor/popper.min.js"></script>
<script src="../../dist/js/bootstrap.min.js"></script>
<script src="../../assets/js/vendor/holder.min.js"></script>
