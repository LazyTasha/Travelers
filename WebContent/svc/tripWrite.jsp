<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <script src="${project}script.js"></script>
   <!-- Bootstrap core CSS -->
   <link rel="stylesheet" type="text/css"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
   <!-- Custom style for this template (Font API & Our UI)-->
   <link href="https://fonts.googleapis.com/css?family=Work+Sans" rel="stylesheet">
   <link rel="stylesheet" href="${project}travelers_style.css">
   <!-- Calendar API -->
   <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
   <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
   <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
<div class="container" style="width:800px;">
      <form class="form-horizontal" method="post" action="tripWritePro.go" >
         <h4> ${page_write}</h4>
         <hr size="1px" color="black">
         <div class="input-box">
            <div class="form-group row">
                 <input type="text" name="title" class="col-12 form-control form-control-lg" maxlength="30" placeholder="${trip_title}" autofocus required>
            </div>
               <input type="hidden" name="user_name" value="${userDto.user_name}">
            <div class="form-group row">	
                 <label for="trip_m_num" class="col-2 col-form-label">${trip_m_num}</label>
                 	<input type="number" name="trip_m_num" class="col-2" min="0">
                 <label for="cal_date" class="col-2 col-form-label">${trip_schedule}</label>
                  	<input type="text" name="cal_start_date" id="date1" class="col-2"/>
                  	~
                  	<input type="text" name="cal_end_date" id="date2" class="col-2"/>
            </div>
            <div class="form-group row">
                 <label for="trip_talk_link" class="col-2 col-form-label">${trip_talk_link}</label>
                 <input type="text" name="trip_talk_link" class="col-10">
            </div>
            <div class="form-group row">
                 <label for="trip_location" class="col-2 col-form-label">${trip_location}</label>
               <div id="floating-panel" class="col-10">
                  <input id="address" type="text"/>
                  <input id="submit" type="button" class="btn btn-dark btn-sm"  value="${btn_search}"/>
               </div>
               <div id="searchmap" class="col-12"></div>
            </div>
            <hr>
            <div class="form-group row">
               <textarea name="content" class="col-12" rows="10" placeholder="${trip_content}"></textarea>
            </div>
            <hr>
            <div class="form-group row">
                 <label for="trip_tag" class="col-2 col-form-label">${trip_tag}</label>
               <c:if test="${tag.size() ne 0}">
                  <c:forEach var="i" items="${tags}">
                  	 <label class="btn btn-secondary">
                   	  <input type="checkbox" name="tag" value="${key}">${value}
                     </label>
                  </c:forEach>
               </c:if>
            </div>           
               <input class="btn btn-dark btn-sm" type="submit" value="${trip_write}">
               <input class="btn btn-dark btn-sm" type="button" value="${btn_list}"
                     onclick="location='tripList.go'">   
      </div><!-- input box -->
   </form>
</div><!-- container -->
</body>
<!-- Map Search API -->
   <script async defer
       src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBnBlipOjNesyFkAIAlXO9WkkIhfiqUIi4&callback=searchMap">
   </script>
</html>