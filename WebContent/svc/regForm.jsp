<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>회원 가입</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- Custom style for this template -->
<link rel="stylesheet" href="style_loginForm.css">
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) -->
<script src="//code.jquery.com/jquery.js"></script>
</head>


 
<body>
	<div class="container">
	
		<div class="text-center">
			<a href="list.jsp"><img class="mb-4" src="img/logo_c.png" alt="" width="100" height="100"></a>
			<h1>회원 가입</h1>
		</div>
		
		 <form class="form-horizontal" action="/action_page.php">
			<div class="form-group row">
				<label for="inputID" class="col-sm-2 col-form-label">아이디</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="inputID" 
						placeholder="ID" required autofocus>					
				</div>
				<div class="col-sm-2">
					<button type="button" class="btn btn-md btn-secondary" onclick="">중복 확인</button>			
				</div>
			</div>

			<div class="form-group row">
				<label for="inputPassword" class="col-sm-2 col-form-label">비밀번호</label>
				<div class="col-sm-8">
					<input type="password" class="form-control" id="inputPassword"
						placeholder="Password" required>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="inputNickname" class="col-sm-2 col-form-label">닉네임</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="inputNickname" 
						placeholder="Nickname" required>					
				</div>
				<div class="col-sm-2">
					<button type="button" class="btn btn-md btn-secondary" onclick="">중복 확인</button>			
				</div>
			</div>

			<fieldset class="form-group">
				<div class="row">
					<legend class="col-form-label col-sm-2 pt-0">성별</legend>
					<div class="col-sm-10">
						<div class="form-check">
						
							<input class="form-check-input" type="radio" name="gridRadios"
								id="gridRadios1" value="option1" checked> <label
								class="form-check-label" for="gridRadios1"> 여성 </label>
						</div>

						<div class="form-check">
							<input class="form-check-input" type="radio" name="gridRadios"
								id="gridRadios2" value="option2"> <label
								class="form-check-label" for="gridRadios2"> 남성 </label>
						</div>
					</div>
				</div>
			</fieldset>


			<div class="form-group">
			 	<div class="row">
		    		 <label for="email" class="control-label col-sm-2" >이메일</label>
		     	 		<div class="col-sm-8">
		      	  		<input type="email" class="form-control" id="email" placeholder="Enter email" name="email" required>
		      	    	</div>
		      	    	<div class="col-sm-2">
							<button type="button" class="btn btn-md btn-secondary" onclick="">본인 인증</button>			
					</div>
		      	</div>
		    </div>
		    
			<div class="form-group row">
				<div class="col-sm-2"></div>
				<div class="col-sm-10">
					<div class="form-check">
						<input class="form-check-input" type="checkbox" id="gridCheck1">
						<label class="form-check-label" for="gridCheck1"> Agree to
							<a href="#">Terms and Conditions</a></label>
					</div>
				</div>
			</div>
			
			<div class="form-group row">
				<div class="col-sm-2"></div>
				<div class="col-sm-8">
					<button type="submit" class="btn btn-lg btn-secondary btn-block">가입하기</button>
				</div>
			</div>
			
		</form>
	</div>
	<!-- container -->

</body>
</html>
