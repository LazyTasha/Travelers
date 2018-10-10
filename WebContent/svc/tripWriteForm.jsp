<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/svc/setting.jsp"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <script src="${project}script.js"></script>
   <!-- Bootstrap core CSS -->
   <link rel="stylesheet" type="text/css"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
   <!-- Custom style for this template -->
   <link href="https://fonts.googleapis.com/css?family=Work+Sans" rel="stylesheet">
   <style>
      body {
           padding-top: 5rem;
         }
      .input-box {
           padding: 3rem 1.5rem;
           text-align: center;
      }
      #searchmap{
           position:relative;
           top:0px;
           bottom:-10px;
           width: 300px;
           height: 300px;
           margin:auto;
           padding : 3px;
           border:1px;
          }
     #floating-panel {
           position: relative;
           top: 5px;
           z-index: 5;
           background-color: #fff;
           padding: 5px;
           border: 1px solid #999;
           text-align: center;
           font-family: 'Roboto','sans-serif';
           line-height: 30px;
           padding-left: 5px;   
         }
      </style>
</head>
<body>
<div class="container" style="width:800px;">
   <form class="form-horizontal" method="post" action="tripWritePro.go" >
         <h4> ${page_write}</h4>
         <hr size="1px" color="black">
         <div class="input-box">
            <div class="form-group row">
                 <input type="text" name="title" class="col-sm-12" maxlength="30" placeholder="${trip_title}" autofocus>
            </div>
               <input type="hidden" name="user_name" value="${userDto.user_name}">
            <div class="form-group row">
                 <label for="trip_m_num" class="col-sm-2 col-form-label">${trip_m_num}</label>
                 <input type="text" name="trip_m_num" class="col-sm-10">
            </div>
            <div class="form-group row">
                 <label for="trip_talk_link" class="col-sm-2 col-form-label">${trip_talk_link}</label>
                 <input type="text" name="trip_talk_link" class="col-sm-10">
            </div>
            <div class="form-group row">
                 <label for="trip_location" class="col-sm-2 col-form-label">${trip_location}</label>
               <div id="floating-panel" class="col-sm-10">
                  <input id="address" type="text"/>
                  <input id="submit" type="button" class="btn btn-dark btn-sm"  value="${btn_search}"/>
               </div>
               <div id="searchmap"></div>
            </div>
            <hr>
            <div class="form-group row">
               <textarea name="content" class="col-sm-12" rows="10" placeholder="내용을 입력하세요"></textarea>
            </div>
            <hr>
            <div class="form-group row">
                 <label for="trip_tag" class="col-sm-2 col-form-label">${trip_tag}</label>
               <c:if test="${tagList.size() ne 0}">
                  <c:forEach var="i" items="${tagList}">
                     <input type="checkbox" name="tag" value="${key}">${value}
                  </c:forEach>
               </c:if>
            </div>   
            <div class="form-group row">
               <input class="btn btn-dark btn-sm" type="submit" value="${trip_write}">
               <input class="btn btn-dark btn-sm" type="button" value="${btn_list}"
                     onclick="location='list.go'">
            </div>      
      </div><!-- input box -->
   </form>
</div><!-- container -->
</body>
<!-- Map Search API -->
   <script async defer
       src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBnBlipOjNesyFkAIAlXO9WkkIhfiqUIi4&callback=searchMap">
   </script>
</html>
	