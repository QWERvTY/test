<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.logout {
	background-color: white;
	font-size: 12px;
    color: #444;
    border: 1px solid #e5e8ef;
    padding: 0 5px;
    height: 22px;
    line-height: 23px;
    text-decoration: none!important;
}
.logout:hover {
	border: 1px solid #BDC0C7;
	background-color: #F3F3F3;
}
</style>
<header>
	<div id="login">
		<c:if test="${sessionID != null }">
			<b><span style="color: rgb(255, 160, 160);">${sessionID }</span></b>님
			<a href="/member/logout"><button class="logout">로그아웃</button></a>
		</c:if>
		<c:if test="${sessionID == null }">
			<a href="/member/login">login</a> | <a href="/member/join">join</a>
		</c:if>
	</div>
	<div class="clear"></div>
	<!-- 로고들어가는 곳 -->
	<div id="logo">
		<img src="/resources/images/logo.gif" width="265" height="62" alt="Fun Web">
	</div>
	<!-- 로고들어가는 곳 -->
	<nav id="top_menu">
		<ul>
			<li><a href="/">HOME</a></li>
			<li><a href="/welcome">COMPANY</a></li>
			<li><a href="/batch/form">SOLUTIONS</a></li>
			<li><a href="/board/list">CUSTOMER CENTER</a></li>
			<li><a href="#">CONTACT US</a></li>
		</ul>
	</nav>
</header>