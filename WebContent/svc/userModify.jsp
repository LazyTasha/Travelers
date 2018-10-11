<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_member.css">
<script src="${project}script.js"></script>
<c:if test="${result eq 1}">
	<body onload="userModify(${user_id})">
		<div class="container" style="width: 600px">
			<div class="text-center">
				<a href="tripList.go"> <img class="mb-4"
					src="${project}img/logo_c.png" alt="" width="100" height="100">
				</a>
				<h4>${page_modify}</h4>
				<br>
			</div>
			<form class="form-horizontal" name="userMod">
				<div class="form-group row">
					<label for="nickname" class="col-sm-2 col-form-label">${str_nickname}</label>
					<div class="col-sm-8">&nbsp; ${userDto.user_name}</div>
					<input type="hidden" name="user_id" value="${userDto.user_name}">
				</div>
				<div class="form-group row">
					<label for="inputNickname" class="col-sm-2 col-form-label">${str_nickname}</label>
					<div class="col-sm-8">
						<input type="text" name="user_name" class="form-control"
							id="name_val" placeholder="Nickname" required>
						<h4 style="color: red;" id="passwordCheckMessageggg"></h4>
					</div>
					<div class="col-sm-2">
						<button type="button" class="btn btn-md btn-secondary"
							onclick="over()">${btn_confirm}</button>
					</div>
				</div>
				<div class="form-group row">
					<label for="gender" class="col-sm-2 col-form-label">${str_gender}</label>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="gender"
							id="gridRadios1" value="1" checked> <label
							class="form-check-label" for="gridRadios1">
							${str_gender_f} </label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="gender"
							id="gridRadios2" value="0"> <label
							class="form-check-label" for="gridRadios2">
							${str_gender_m} </label>
					</div>
				</div>
				<div class="form-group row">
					<label for="email" class="control-label col-sm-2">${str_email}
					</label>
					<div class="col-sm-8">&nbsp; ${userDto.email}</div>
				</div>
				<div class="form-group row">
					<label for="tag" class="control-label col-sm-2">${str_tag}
					</label>
					<div class="col-sm-8">
						<div class="btn-group btn-group-sm" role="group" aria-label="...">
							<c:if test="${tagList} ne null">
								<c:forEach var="i" items="${tagList}">
									<button type="button" class="btn btn-default" name="tags"
										disabled>#${i.value}&nbsp;</button>
								</c:forEach>
							</c:if>
						</div>
					</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-2"></div>
					<div class="col-sm-8">
						<button type="submit" class="btn btn-lg btn-secondary btn-block"
							onclick="location='userModPro.go'">${btn_modify}</button>
						<button type="button" class="btn btn-lg btn-secondary btn-block"
							onclick="location='myPage.go'">${btn_cancel}</button>
					</div>
				</div>
			</form>
		</div>
	</body>
</c:if>
<c:if test="${result eq 0}">
	<script type="text/javascript">
		erroralert(${modError_passwd});
	</script>
</c:if>
<c:if test="${result eq -1}">
	<script type="text/javascript">
		erroralert(${modError_noUser});
	</script>
</c:if>