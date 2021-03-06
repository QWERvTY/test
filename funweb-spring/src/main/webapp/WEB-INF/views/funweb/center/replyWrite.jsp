<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reply Write</title>
<link href="/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="/resources/css/subpage.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="/resources/images/favicon.png">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
</head>
<body>
	<div id="wrap">
		<!-- 헤더들어가는 곳 -->
		<jsp:include page="/WEB-INF/views/inc/top.jsp"></jsp:include>
		<!-- 헤더들어가는 곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 메인이미지 -->
		<div id="sub_img_center"></div>
		<!-- 메인이미지 -->

		<!-- 왼쪽메뉴 -->
		<jsp:include page="/WEB-INF/views/inc/noticeLeft.jsp"></jsp:include>
		<!-- 왼쪽메뉴 -->

		<!-- 게시판 -->
		<article>
			<h1>Reply Write</h1>
			<form action="/board/reply" method="POST" name="form">
				<input type="hidden" name="pageNum" value="${param.pageNum }">
				<input type="hidden" name="reRef" value="${param.reRef }">
				<input type="hidden" name="reLev" value="${param.reLev }">
				<input type="hidden" name="reSeq" value="${param.reSeq }">
				<table id="notice">
					<tr>
						<th>Title</th>
						<td><input type="text" name="subject"></td>
					</tr>
					<tr>
						<th>Password</th>
						<td><input type="password" name="pass"></td>
					</tr>
					<tr>
						<th>Content</th>
						<td><textarea rows="13" cols="30" name="content"></textarea></td>
					</tr>
				</table>
				<div id="table_search">
					<input type="submit" value="Write" class="btn">
					<input type="reset" value="Reset" class="btn">
					<input type="button" value="List" class="btn"
						onclick="location.href='/board/list?pageNum=${param.pageNum}';">
				</div>
			</form>

			<div class="clear"></div>
			<!-- <div id="page_control">
<a href="#">Prev</a>
<a href="#">1</a><a href="#">2</a><a href="#">3</a>
<a href="#">4</a><a href="#">5</a><a href="#">6</a>
<a href="#">7</a><a href="#">8</a><a href="#">9</a>
<a href="#">10</a>
<a href="#">Next</a>
</div> -->
		</article>
		<!-- 게시판 -->
		<!-- 본문들어가는 곳 -->
		<div class="clear"></div>
		<!-- 푸터들어가는 곳 -->
		<jsp:include page="/WEB-INF/views/inc/bottom.jsp"></jsp:include>
		<!-- 푸터들어가는 곳 -->
	</div>
</body>
</html>