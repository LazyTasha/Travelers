<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/svc/setting.jsp"%>
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
				<input type="submit" value="${trip_write}">
			</td>
		</tr>
	</table>
</form>