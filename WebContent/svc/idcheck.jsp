<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<sql:query var="rs" dataSource="jdbc/encore">
	select count(*) from gg_user where id=?
	<sql:param value="${param.id}"/>
</sql:query>

<c:forEach var="row" items="${rs.rowsByIndex}">
	<c:if test="${row[0] ne 0}">				
		<result>
			<message>${param.id}는 사용할 수 없습니다.</message>
		</result>
	</c:if>
	<c:if test="${row[0] eq 0}">
		<result>
			<message>${param.id}는 사용할 수 있습니다.</message>
		</result>
	</c:if>	
</c:forEach>
