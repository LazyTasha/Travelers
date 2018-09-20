<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fnc" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="project_svc" value="/Travelers/svc"/>

<c:set var="write_error" value="글쓰기에 실패했습니다."/>

<c:set var="trip_writer" value="작성자"/>
<c:set var="trip_title" value="제목"/>
<c:set var="trip_content" value="내용"/>
<c:set var="trip_tag" value="해시태그"/>
<c:set var="trip_m_num" value="동행인원"/>
<c:set var="trip_talk_link" value="카카오톡 채팅방"/>
<c:set var="trip_write" value="글쓰기"/>