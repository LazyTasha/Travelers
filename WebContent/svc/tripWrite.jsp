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
   <!-- Custom style for this template -->
   <link href="https://fonts.googleapis.com/css?family=Work+Sans" rel="stylesheet">
   <style>
     input[type=text] {
         border: none;
         border-bottom: 1px solid #344955;
     }
     input[type=text]:focus {
         border: none;
         border-bottom: 2px solid #344955 !important;
     }
      body {
           padding-top: 5rem;
      }
      .input-box {
           padding: 3rem 1.5rem;
           text-align: center;
      }
      .form-group{
            margin-bottom:0.6rem !important
      }
      .form-group:focus{
            border-color: #5cb85c;  box-shadow: none; -webkit-box-shadow: none;
      }
      #searchmap{
           position:relative;
           bottom:-10px;
           height: 200px;
           margin:auto;
           padding : 3px;
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
   <form id="tripWriteForm" class="form-horizontal" method="post" action="tripWritePro.go">
         <h4> ${page_write}</h4>
         <hr size="1px" color="black">
         <div class="input-box">
            <div class="form-group row">
                 <input type="text" name="title" class="col-12 form-control form-control-lg" maxlength="30" placeholder="${trip_title}" autofocus required>
            </div>
               <input type="hidden" name="user_name" value="${userDto.user_name}">
            <div class="form-group row">
                 <label for="trip_m_num" class="col-2 col-form-label">${trip_m_num}</label>
                 <input type="text" name="trip_m_num" class="col-4">
                 <label for="cal_date" class="col-2 col-form-label">일정</label>
                 <input type="text" name="trip_m_num" class="col-4">
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
            </div>
            <div class="form-group row">
               <div id="searchmap" class="col-12"></div>
            </div>
            <hr>
            <div class="form-group row">
               <textarea name="content" class="col-12" rows="10" placeholder="내용을 입력하세요"></textarea>
            </div>
            <hr>
            <div class="form-group row">
                 <label for="trip_tag" class="col-2 col-form-label">${trip_tag}</label>
               
                  <div class="btn-group btn-group-toggle" data-toggle="buttons">
              <label class="btn btn-secondary active">
                <input type="checkbox" autocomplete="off"> Tag1
              </label>
              <label class="btn btn-secondary">
                <input type="checkbox" autocomplete="off"> Tag2
              </label>
              <label class="btn btn-secondary">
                <input type="checkbox" autocomplete="off"> Tag3
              </label>
              <label class="btn btn-secondary">
                <input type="checkbox" autocomplete="off"> Tag4
              </label>
              <label class="btn btn-secondary">
                <input type="checkbox" autocomplete="off"> Tag5
              </label>
               <label class="btn btn-secondary">
                <input type="checkbox" autocomplete="off"> Tag6
              </label>
               <label class="btn btn-secondary">
                <input type="checkbox" autocomplete="off"> Tag7
              </label>
               <label class="btn btn-secondary">
                <input type="checkbox" autocomplete="off"> Tag8
              </label>
               <label class="btn btn-secondary">
                <input type="checkbox" autocomplete="off"> Tag9
              </label>
               <label class="btn btn-secondary">
                <input type="checkbox" autocomplete="off"> Tag10
              </label>
            </div>      
            </div>   
               <input class="btn btn-dark btn-sm" type="submit" value="${trip_write}">
               <input class="btn btn-dark btn-sm" type="button" value="${btn_list}"
                     onclick="location='list.go'">       
      </div><!-- input box -->
   </form>
</div><!-- container -->
</body>
<!-- Map Search API -->
<<<<<<< HEAD
<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBnBlipOjNesyFkAIAlXO9WkkIhfiqUIi4&callback=searchMap">
</script>
=======
   <script async defer
       src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBnBlipOjNesyFkAIAlXO9WkkIhfiqUIi4&callback=searchMap">
   </script>
</html>
>>>>>>> master
