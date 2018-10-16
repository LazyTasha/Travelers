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
<link href="style_album.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Work+Sans"
	rel="stylesheet">

<script src="//code.jquery.com/jquery.js"></script>
<script src="${project}script.js"></script>
</head>

<body>
	<div class="container" style="width: 800px;">
		<div>
			<div id=button_area>
				<!--수정/삭제 button -->
				<c:if test="${isOwner eq 1}">
					<section>
						<input type="button" value="${btn_mod}"
							onclick="modifyBoard(${tb_no})"> <input type="button"
							value="${btn_delete}" onclick="deleteBoard(${tb_no})">
					</section>
				</c:if>
				<c:if test="${user_level eq 9}">
					<section>
						<input type="button" value="${btn_delete}"
							onclick="deleteBoard(${tb_no})"> <input type="button"
							value="${btn_back_admin}" onclick="goAdminPage()">
					</section>
				</c:if>
			</div>
			<!--  -->
			<br> <input type="hidden" value="${tbDto.tb_notice}" id="notice" />
			<!----- 공지 ----->
			<article>
				<section>
					<c:if test="${tbDto.tb_notice == 1}">
						<img class="mb-4" src="${project}img/logo_c.png" alt=""
							width="100" height="100">
					</c:if>

					<div id="trip_title">
						<div>
							<div>
							<h2>제목 : ${tbDto.tb_title}</h2>
							</div>
						</div>
						
						<div id="trip_content">
							<div>
								<div>글쓴이 : ${tbDto.user_id}</div>
							</div>

							<div>
								<div>본문 : ${tbDto.tb_content}</div>
							</div>

							<div>
								<div>인원 : ${tbDto.tb_v_count}/${tbDto.tb_m_num}</div>
							</div>

							<div>

								<c:forEach var="i" items="${locDtoList}">
									<div>일정 : ${i.cal_start_date}~${i.cal_end_date}</div>
									<div name="coord">
										<div id="mapinfo">주소:</div>
										<input type="hidden" name="coord_long" value="${i.coord_long}">
										<input type="hidden" name="coord_lat" value="${i.coord_lat}">
									</div>	
									<div>나라 이름: ${i.country_name}</div>
									<button onclick="attend()">참석</button>
									<button onclick="attend()">불참</button>
								</c:forEach>
							</div>

							<div>
								<div>
									카카오톡 :<a href="${tbDto.tb_talk}" target="_blank"> 카카오톡</a>
								</div>
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
							<input type="hidden" name="tb_no" value="${tb_no}" /> 
							<input type="hidden" name="session" value="${user_id}" /> 
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