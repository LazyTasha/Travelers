/* 회원 관리 */
var emailerror = "이메일 형식에 맞지 않습니다";
var confirmerror = "아이디 중복확인 해 주세요";
var gendererror = "성별을 선택해 주세요";

var inputerror = "회원가입에 실패했습니다.\n잠시 후 다시 시도하세요.";
var loginiderror = "입력하신 아이디가 없습니다.\n아이디를 다시 확인하세요.";
var loginpasswderror = "입력하신 비밀번호가 다릅니다.\n비밀번호를 다시 확인하세요.";
var deleteerror = "회원탈퇴에 실패했습니다.\n잠시 후 다시 시도하세요.";

var extensionerror="jpg, gif, png 확장자만 업로드 가능합니다.";
var sizeerror="이미지 용량은 5M이하만 가능합니다."

var filesize=5*1024*1024;

function erroralert( msg ) {
	alert( msg );
	history.back();
}

// 회원 정보 수정
function modifyfocus() {
	modifyform.passwd.focus();
}
function modifycheck() {
	if (!modifyform.passwd.value) {
		alert(passwderror);
		modifyform.passwd.focus();
		return false;
	} else if (modifyform.passwd.value != modifyform.repasswd.value) {
		alert(repasswderror);
		modifyform.repasswd.focus();
		return false;
	}

	if (!modifyform.user_name.value) {
		alert(nameerror);
		modifyform.user_name.focus();
		return false;
	}

	if (modifyform.email1.value || modifyform.email2.value) {
		if ((modifyform.email1.value && !modifyform.email2.value)
				|| (!modifyform.email1.value && modifyform.email2.value)
				|| (modifyform.email1.value.indexOf("@") != -1 || modifyform.email2.value
						.indexOf("@") != -1)) {
			alert(emailerror);
			modifyform.email1.focus();
			return false;
		}
	}
}

// 회원 탈퇴
function passwdfocus() {
	passwdform.passwd.focus();
}
function passwdcheck() {
	if (!passwdform.passwd.value) {
		alert(passwderror);
		passwdform.passwd.focus();
		return false;
	}
}

// 로그인
function loginfocus() {
	loginform.user_id.focus();
}
function logincheck() {
	if (!loginform.user_id.value) {
		alert(iderror);
		loginform.user_id.focus();
		return false;
	} else if (!loginform.passwd.value) {
		alert(passwderror);
		loginform.passwd.focus();
		return false;
	}
}

//AJAX 또는 DOM

function passwordCheckFunction() {
	var userPassword1 = $('#userPassword1').val();
	var userPassword2 = $('#userPassword2').val();

	if (userPassword1 != userPassword2) {
		$('#passwordCheckMessage').html("비밀번호가 일치하지 않습니다");
	} else {
		$('#passwordCheckMessage').html(" ");
	}
}

//아이디
var idck = 0;
function overlapCheck() {
	var user_id = $("#id_val").val();
	if (user_id) {
		$.ajax({
			async : true,
			type : 'POST',
			data : user_id,
			url : "idCheck.go",
			dataType : "json",
			/* contentType : "application/json", */
			success : function(data) {
				if (data.cnt > 0) {
					$('#passwordCheckMessagegg').html(
							"아이디가 존재합니다. 다른 아이디를 입력해주세요.")
					/*
					 * alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
					 * $("#divInputId").addClass("has-error")
					 * $("#divInputId").removeClass("has-success")
					 * $("#id_val").focus();
					 */

				} else {
					$('#passwordCheckMessagegg').html("사용가능한 아이디입니다.")

					/*
					 * alert("사용가능한 아이디입니다.");
					 * $("#divInputId").addClass("has-success")
					 * $("#divInputId").removeClass("has-error")
					 * $("#passwd").focus();
					 */
					// 아이디가 중복하지 않으면 idck = 1
					idck = 1;

				}
			},
			error : function(error) {
				alert("error : " + error);
			}
		});
	}
}
// /////////

// 닉네임
var genck = 0;
function over() {
	var name_val = $("#name_val").val();
	if (name_val) {
		$.ajax({
			async : true,
			type : 'POST',
			data : name_val,
			url : "nameCheck.go",
			dataType : "json",
			/* contentType : "application/json", */
			success : function(data) {
				if (data.cntt > 0) {
					$('#passwordCheckMessageggg').html("닉네임이 존재합니다.")
					/*  alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
					  $("#divInputId").addClass("has-error")
					  $("#divInputId").removeClass("has-success")
					  $("#id_val").focus();*/

				} else {
					$('#passwordCheckMessageggg').html("사용가능한 닉네임입니다.")

					/*alert("사용가능한 아이디입니다.");	                    
					$("#divInputId").addClass("has-success")
					$("#divInputId").removeClass("has-error")
					$("#passwd").focus();*/
					//아이디가 중복하지 않으면  idck = 1 
					genck = 1;

				}
			},
			error : function(error) {
				alert("error : " + error);
			}
		});
	}
}

function inputcheck() {
	if (confirm("회원가입을 하시겠습니까?")) {
		if (idck == 0) {
			alert('아이디 중복체크를 해주세요');
			return false;
		} else if (genck == 0) {
			alert('닉네임 중복체크를 해주세요');
			return false;
		} else {
			alert("회원가입을 축하합니다");
			$("#inputform").button();
		}
	}
	/*if( ! inputform.user_id.value ) {
		alert( iderror );
		inputform.user_id.focus();
		return false;
	} else if( ! inputform.passwd.value ) {
		alert( passwderror );
		inputform.passwd.focus();
		return false;
	} else if( inputform.passwd.value != inputform.repasswd.value ) {
		alert( repasswderror );
		inputform.repasswd.focus();
		return false;
	} else if( ! inputform.user_name.value ) {
		alert( nameerror );
		inputform.user_name.focus();
		return false;
	}else if ( ! inputform.gender.value ){
		alert( gendererror );
		inputform.gender.focus();
		return false;
	}else if ( ! inputform.email1.value ){
		alert( emailerror );
		inputform.email1.focus();
		return false;
	}else if ( ! inputform.email2.value ){
		alert( emailerror );
		inputform.email1.focus();
		return false;
	}*/
	// 	1. null 인 경우			이동 가능
	// 	2. 직접입력일 경우		email1 란에 @가 없으면 경고
	// 	3. 선택입력일 경우		email1 란에 @가 있으면 경고
	//	단 전화번호가 있건 없건 모두 가능해야 한다.

	if (inputform.email1.value.indexOf("@") == -1) {
		alert(emailerror);
		return false;
	} else {
		if (inputform.email1.value.indexOf("@") != -1) {
			alert(emailerror);
			return false;
		}
	}
}
//사진 업로드 click->photo upload
function uploadPhotos(){
	eventOccur(document.getElementById('file'),'click');
	var error=0;
	$('#file').change(function() {  
	    if (this.files) { 
	    	var form=document.getElementById('uploadForm');
	    	var file= document.getElementById("file");
	    	for(var i=0;i<file.files.length;i++){
		    	var fileName=$('#file').get(0).files[i].name;
		    	var size=$('#file').get(0).files[i].size;
		    	if(validation(fileName)){
		    		alert(extensionerror);
		    		error++;
		    		break;
		    	}
		    	if(sizeOver(size)){
		    		alert(sizeerror);
		    		error++;
		    		break;
		    	}
	    	}
	    	if(error==0)form.submit();
	    }
	});	
}
function eventOccur(evEle, evType){
	 if (evEle.fireEvent) {
		 evEle.fireEvent('on' + evType);
	 } else {
		 var mouseEvent = document.createEvent('MouseEvents');
		 mouseEvent.initEvent(evType, true, false);
		 var transCheck = evEle.dispatchEvent(mouseEvent);
		 if (!transCheck) {
			 //만약 이벤트에 실패했다면
			 console.log("click event fail");
		 }
	 }
}
//client-side extension validation
function validation(fileName) {
    fileName = fileName + "";
    var fileNameExtensionIndex = fileName.lastIndexOf('.') + 1;
    var fileNameExtension = fileName.toLowerCase().substring(
            fileNameExtensionIndex, fileName.length);
    if (!((fileNameExtension === 'jpg')
            || (fileNameExtension === 'gif') || (fileNameExtension === 'png'))) {
        return true;
    } else {
        return false;
    }
}
//size validation
function sizeOver(size){
	if(size>filesize)return true;
	else return false;
}