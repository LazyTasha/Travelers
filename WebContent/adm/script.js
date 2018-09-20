var taginserterror="겹치는 tag가 존재합니다.";
var valueerror="값을 입력하세요";
var checkerror="추가할 값을 선택하세요";
$(document).ready(
	function(){
		//전체 선택 체크시 모든 체크 박스 check
		$('#checkAll').change(function(){
			if($('#checkAll').is(":checked")){
				$("input:checkbox").each(function() {
					$(this).attr("checked", true);
				});

			}else{
				$("input:checkbox").each(function() {
					$(this).attr("checked", false);
				});
			}
		});	
	}		
);

function erroralert(msg){
	alert(msg);
	history.back();
}

//tag 추가
function addTag(state){  
	
	var checkbox=$("input[name=check1]:checked");
	var values=new Array();

	checkbox.each(function(i){
		// checkbox.parent() : checkbox의 부모는 <td>이다.
		// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
		var tr = checkbox.parent().parent().eq(i);	

		var tag_value=tr.find('input[type="text"]').val();
		// 체크된 row의 모든 값을 배열에 담는다.
		if(!tag_value){
			alert(valueerror);
			
		}else{
			values.push(tag_value);
			
			location.href="adminTagMng.go?state="+state+"&values="+values;
			
		}
		
	});
	//if(values!=null||values!=""){
		
	//}else{
		//setCancel();
	//}
	
}
function modTag(state){
	location.href="adminTagMng.go?state="+state;
}
function goModTag(){
	open("adminTagMod.go?tag_id="+"","confirm window","scrollbar=yes,status=no,menubar=no,width=600,height=300");
}
function goAddTag(){
		open("adminTagAdd.go","confirm window","scrollbar=yes,status=no,menubar=no,width=600,height=300");
}
function setCancel(){
	self.close();
}
function addRow(){
	var tag=document.getElementById('t');
	var row=tag.insertRow(tag.rows.length);
	
	var cell=[];
	var n=2;
	for(var i=0;i<n;i++){
		cell[i]=row.insertCell(i);
		if(i==0){
			cell[i].innerHTML="<input type='checkbox' name='check1'>";
		}else if(i==n-1){
			cell[i].innerHTML="<input class='input' type='text' autofocus=''>";
		}
	}	
}





