/* 회원 관리 */
var emailerror = "이메일 형식에 맞지 않습니다";
var confirmerror = "아이디 중복확인 해 주세요";
var gendererror = "성별을 선택해 주세요";

var inputerror = "회원가입에 실패했습니다.\n잠시 후 다시 시도하세요.";
var loginiderror = "입력하신 아이디가 없습니다.\n아이디를 다시 확인하세요.";
var loginpasswderror = "입력하신 비밀번호가 다릅니다.\n비밀번호를 다시 확인하세요.";
var deleteerror = "회원탈퇴에 실패했습니다.\n잠시 후 다시 시도하세요.";

var extensionerror="jpg, gif, png 확장자만 업로드 가능합니다.";
var sizeerror="이미지 용량은 5M이하만 가능합니다.";

var nocheckerror="다운로드 받을 사진을 선택하세요";

var filesize=5*1024*1024;

$(document).ready(function(){
	var tb_no=$('input[name=tb_no]').val();
    commentList(tb_no); //페이지 로딩시 댓글 목록 출력 
});


function erroralert( msg ) {
	alert( msg );
	history.back();
}
//Initialize and add the map
function initMap() {
	
	var lat=parseFloat(document.getElementById("lat").value);
	var lng=parseFloat(document.getElementById("lng").value);
	
	  // The location of Uluru
	  var uluru = {lat: lat, lng: lng};
	  // The map, centered at Uluru

	  var map = new google.maps.Map(
	      document.getElementById('map'), {zoom: 8, center: uluru});
	  // The marker, positioned at Uluru
	  var marker = new google.maps.Marker({position: uluru, map: map});

}
//지도 주소검색
function searchMap() {
    var map = new google.maps.Map(document.getElementById('searchmap'), {
      zoom: 8,
      center: {lat: -34.397, lng: 150.644}
    });
    var geocoder = new google.maps.Geocoder();

    document.getElementById('submit').addEventListener('click', function() {
     geocodeAddress(geocoder, map);
    });
  }
//주소로 좌표 표시
  function geocodeAddress(geocoder, resultsMap) {
    var address = document.getElementById('address').value;
    geocoder.geocode({'address': address}, function(results, status) {
      if (status === 'OK') {
        resultsMap.setCenter(results[0].geometry.location);
        var marker = new google.maps.Marker({
          map: resultsMap,
          position: results[0].geometry.location
        });
        //좌표 받기
        var lat=marker.position.lat();//위도 
        var lng=marker.position.lng();//경도
        
        //lat,lng를 form에 보내주기
        var msg="<input id='lat' type='hidden' value="+lat+"/>"
        	+"<input id='lng' type='hidden' value="+lng+"/>";
        $('#searchMap').html(msg);
        //alert($('#searchmap').html())
    
        //alert(document.getElementById('lat').value);
      } else {
        alert('Geocode was not successful for the following reason: ' + status);
      }
    });
  }
//trip view-button event-map
function showMap(){
	$('#albumTab').hide();
	$('#mapTab').show();
}
//trip view-button event-boardAlbum
function showAlbum(){
	$('#albumTab').show();
	$('#mapTab').hide();
}
//trip-album-nextPage
function next(start,size){
	start=start+size;
	albumPaging(start);
}
//trip-album-prePage
function previous(start,size){
	if(start>size)start=start-size;
	albumPaging(start);
}
function albumPaging(start){
	var tb_no=$('input[name=tb_no]').val();
	var tab=1;

	var page="svc/boardAlbum.go?tb_no="+tb_no+"&start="+start+"&tab="+tab;
	$('#album').load(page);
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
			success : function(data) {
				if (data.cnt > 0) {
					$('#passwordCheckMessagegg').html(
							"아이디가 존재합니다. 다른 아이디를 입력해주세요.");
				} else {
					$('#passwordCheckMessagegg').html("사용가능한 아이디입니다.");
					idck = 1;
				}
			},
			error : function(error) {
				alert("error : " + error);
			}
		});
	}
}

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
				
				} else {
					$('#passwordCheckMessageggg').html("사용가능한 닉네임입니다.")

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
//사진 선택click->create checkbox
function selectPhotos(){
	$('input[name=check1]').show();
	$('#select').hide();
	$('#download').show();
}
//사진 다운로드 click->photo download
function downloadPhotos(){
	var check1=$('input[name=check1]');
	if($("input[name=check1]:checked").length==0){
		alert(nocheckerror);
	}else{
		//download구현->downloadPhoto.go로 이동 해서 작업
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

////comment
function commentInsert(){ //댓글 등록 버튼 클릭시 
	 var insertData = $('[name=commentInsertForm]').serialize(); //commentInsertForm의 내용을 가져옴
	 CmtInsert(insertData); //Insert 함수호출(아래)
}

var number = '${tbDto.tb_no}'; //게시글 번호
//댓글 목록 
function commentList(tb_no){
    $.ajax({
        url : 'commentSelect.go',
        type : 'get',
        data : {tb_no : tb_no},
        success : function(data){
            var a =''; 
            $.each(data, function(key, comemnt){ 
            	a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
                a += '<div class="commentInfo'+comemnt.c_id+'">'+'댓글번호 : '+comemnt.c_id+' / 작성자 : '+comemnt.user_id;
                a += '<a onclick="commentUpdate('+comemnt.c_id+',\''+comemnt.c_content+'\');"> 수정 </a>';
                a += '<a onclick="commentDelete('+comemnt.c_id+');"> 삭제 </a> </div>';
                a += '<div class="commentContent'+comemnt.c_id+'"> <p> 내용 : '+comemnt.c_content +'</p>';
                a += '</div></div>'
            });
            
            $(".commentList").html(a);
        },
        error : function(error) {
            alert("error : " + error + number);
        }
    });
}

//댓글 등록
function CmtInsert(insertData){
	var tb_no=$("input[name=tb_no").val();
    $.ajax({
        url : 'commentInsert.go',
        type : 'post',
        data : insertData,
        success : function(data){
        	if(data == 1) {
        		/*오류메세지 작성*/
           }else{
        	   commentList(tb_no);
        	   $('[name=c_content]').val('');
           }
        },
    	error : function(error) {
        alert("error : " + error);
    }
    });
}

//댓글 수정 - 댓글 내용 출력을 input 폼으로 변경 
function commentUpdate(c_id, c_content){
    var a ='';
    
    a += '<div class="input-group">';
    a += '<input type="text" class="form-control" name="c_content_'+c_id+'" value="'+c_content+'"/>';
    a += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="commentUpdateProc('+c_id+');">수정</button> </span>';
    a += '</div>';
    
    $('.commentContent'+c_id).html(a);
    
}
 
//댓글 수정
function commentUpdateProc(c_id){
    var updateContent = $('input[name=c_content_'+c_id+']').val();
    var tb_no=$("input[name=tb_no").val();
    $.ajax({
        url : 'commentUpdate.go',
        type : 'post',
        data : {'c_content' : updateContent, 'c_id' : c_id},
        success : function(data){
            commentList(tb_no); //댓글 수정후 목록 출력 
        }
    });
}
 
//댓글 삭제 
function commentDelete(c_id){
	var tb_no=$("input[name=tb_no").val();
    $.ajax({
        url : 'commentDelete.go',
        type : 'post',
        data : {
        	c_id : c_id
        },
        success : function(data){
            commentList(tb_no); //댓글 삭제후 목록 출력 
        },
        error : function(error) {
            alert("error : " + error);
        }
    });
}

function loadMoreList(last_tb_no) {
	$('#append-list').load
	$.ajax({
		type : 'post',
		data : last_tb_no,
		url : "loadMoreList.go",
		success : function(data) {
			if(data){
				request.send(last_tb_no);
			} else {
				alert('더 이상 불러올 글이 없습니다.');
			}
		},
		error : function(error) {
			alert('글 불러오기에 실패했습니다.');
		}
	});
}
