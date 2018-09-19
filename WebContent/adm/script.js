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
function modTag(){
	open("adminTagMod.go?tag_id="+"","confirm window","scrollbar=yes,status=no,menubar=no,width=500,height=300");
}
function addTag(){
		open("adminTagAdd.go","confirm window","scrollbar=yes,status=no,menubar=no,width=500,height=300");
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
			cell[i].innerHTML="<input type='checkbox'>";
		}else if(i==n-1){
			cell[i].innerHTML="<input class='input' type='text' autofocus=''>";
		}
	}	
}





