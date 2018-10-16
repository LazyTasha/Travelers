<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/svc/setting.jsp"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- Custom styles for boarAlbum template -->
<link rel="stylesheet" href="${project}travelers_style.css">
<!-- Custom styles for boarAlbum template -->
<link href="style_album.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Work+Sans"
	rel="stylesheet">
<script src="//code.jquery.com/jquery.js"></script>
<script src="${project}script.js"></script>
</head>
<body>
	<div class="container" style="width: 800px;">
		<div class="row">
			<div id=button_area class="float-right">
				<!--수정/삭제 button -->
				<c:if test="${isOwner eq 1}">
					<section>
						<input type="button" class="btn-sm" value="${btn_mod}" onclick="modifyBoard(${tb_no})"> 							
						<input type="button" class="btn-sm" value="${btn_delete}" onclick="deleteBoard(${tb_no})">							
					</section>
				</c:if>
				<c:if test="${user_level eq 9}">
					<section>
						<input type="button" class="btn" value="${btn_delete}" onclick="deleteBoard(${tb_no})">							
						<input type="button" class="btn" value="${btn_back_admin}" onclick="goAdminPage()">							
					</section>
				</c:if>
			</div><!-- button_area -->
			
			<!-- Hidden info toss -->
			<br> <input type="hidden" value="${tbDto.tb_notice}" id="notice" />
			
			<!----- Notice ----->
			<article>
				<section>
					<c:if test="${tbDto.tb_notice == 1}">
						<img class="mb-4" src="${project}img/logo_c.png" alt=""
							width="100" height="100">
					</c:if>

					<div id="trip_title">
						<div class="row">
							<div class="col-12 form-control form-control-lg">
								<h2>제목 : ${tbDto.tb_title}</h2>
							</div>
						</div>
						
						<div id="trip_content">
							<div class="row">
								<div>글쓴이 : ${tbDto.user_id}</div>
							</div>						
							<div class="row">
								<div>인원 : ${tbDto.tb_v_count}/${tbDto.tb_m_num}</div>
							</div>
							<div class="row">
								<c:forEach var="i" items="${locDtoList}">
									<div>일정 : ${i.cal_start_date}~${i.cal_end_date}</div>
									<div>좌표 : ${i.coord_long}~${i.coord_lat}</div>
									<div>나라 이름: ${i.country_name}</div>
									<button onclick="attend()">참석</button>
									<button onclick="attend()">불참</button>
								</c:forEach>
							</div>							
							<div class="row">
								카카오톡 :<a href="${tbDto.tb_talk}" target="_blank"> 카카오톡</a>
							</div>
							<div class="row">
								${trip_content}<textarea class="col-12" rows="10" readonly>${tbDto.tb_content}</textarea>
							</div>							
						</div>
					</div>
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
					<div id="mapTab"></div>
				</c:if>
				<c:if test="${tab eq 1}">
					<div id="mapTab" style="display: none"></div>
				</c:if>
				<div id="map">지도</div>
				<input type="hidden" value="${lat}" id="lat" /> <input
					type="hidden" value="${lng}" id="lng" />
			</article>
			<!-- comment -->
			<c:if test="${sessionScope.user_id != null}">
				<div class="container">
					<label for="content">comment</label>
					<form name="commentInsertForm" method="post">
						<div class="input-group">
								<input type="text" class="input" id="c_content" name="c_content" placeholder="내용을 입력하세요."> 
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
	</div><!-- container -->
</body>
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBnBlipOjNesyFkAIAlXO9WkkIhfiqUIi4&callback=initMap">
</script>
</html>