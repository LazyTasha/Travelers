<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/svc/setting.jsp"%>
<script src="${project}script.js"></script>
<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBnBlipOjNesyFkAIAlXO9WkkIhfiqUIi4&callback=searchMap">
</script>
<style>
	#searchmap{
  		position:relative;
  		top:0px;
  		bottom:-10px;
  		width: 300px;
  		height: 300px;
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
<form method="post" action="tripWritePro.go" >
	<table>
		<tr>
			<th>${trip_title}</th>
			<td><input type="text" name="title">
		</tr>
		<tr>
			<th>${trip_writer}</th>
			<td>
				${userDto.user_name}
				<input type="hidden" name="user_name" value="${userDto.user_name}">
			</td>
		</tr>
		<tr>
			<th>${trip_content}</th>
			<td><textarea name="content" rows="30" cols="50"></textarea></td>
		</tr>
		<tr>
			<th>${trip_location}</th>
			<td>
				<div id="floating-panel">
					<input id="address" type="text"/>
					<input id="submit" type="button" value="${btn_search}"/>
				</div>
				<div id="searchmap">
				</div>
			</td>
		</tr>
		<tr>
			<th>${trip_m_num}</th>
			<td><input type="text" name="trip_m_num"></td>
		</tr>
		<tr>
			<th>${trip_talk_link}</th>
			<td><input type="text" name="trip_talk_link"></td>
		</tr>
		<tr>
			<th>${trip_tag}</th>
			<td>
				<c:if test="${tagList.size() ne 0}">
					<c:forEach var="i" items="${tagList}">
						<input type="checkbox" name="tag" value="${key}">${value}
					</c:forEach>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input class="inputbutton" type="submit" value="${trip_write}">
				<input class="inputbutton" type="button" value="${btn_list}"
						onclick="location='list.go'">
			</td>
		</tr>
	</table>
</form>
