<%@ page language="java" contentType="text/html; charset=UTF-8"
   session="true"
    buffer="10kb"
    autoFlush="true"
    isThreadSafe="true"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_admin.css">
<script src="${project}script.js"></script>

<header>
	<h2>ADMIN PAGE</h2>
	<div id="logout">
		<h4><a href="">${str_logout}</a></h4>
	</div>
</header>
<aside>
	<h1><a href="adminMain.go?pageName=Trip">${str_list_m}</a></h1>
		<h3><a href="adminMain.go?pageName=Trip">${str_content_v}</a></h3>
		<h3><a href="adminMain.go?pageName=Comment">${str_comment_v}</a></h3>
		<br>
	<h1><a href="adminMain.go?pageName=User">${str_member_m}</a></h1>
		<h3><a href="adminMain.go?pageName=User">${str_member_v}</a></h3>
		<br>
	<h1><a href="adminMain.go?pageName=Tag">${str_tag_m}</a></h1>
		<h3><a href="adminMain.go?pageName=Tag">${str_tag_v}</a></h3>
		<br>
	<h1><a href="adminMain.go?pageName=Album">${str_photo_m}</a></h1>
		<h3><a href="adminMain.go?pageName=Album">${str_photo_v}</a></h3>
		<br>
</aside>
<article>
	<jsp:include page="${pageName}.go" flush="false"/>
</article>
<footer></footer>
