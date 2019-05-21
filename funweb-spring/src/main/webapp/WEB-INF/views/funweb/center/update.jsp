<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board - Update</title>
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
			<h1>Update</h1>
			<form action="/board/modify" method="POST" name="form">
				<table id="notice">
					<tr>
						<th>Title</th>
						<td><input type="text" name="subject" value="${board.subject }"></td>
					</tr>
					<tr>
						<th>Password</th>
						<td><input type="password" name="pass" value="${board.pass }"></td>
					</tr>
					<tr>
						<th>Content</th>
						<td><textarea rows="13" cols="30" name="content">${board.content }</textarea></td>
					</tr>
				</table>
				<div id="table_search">
					<input type="submit" value="Write" class="btn">
					<input type="reset" value="Reset" class="btn">
					<input type="button" value="List" class="btn"
						onclick="location.href='/board/list?pageNum=${param.pageNum}';">
				</div>
				<input type="hidden" name="num" value="${board.num }">
				<input type="hidden" name="pageNum" value="${param.pageNum }">
			</form>

			<div class="clear"></div>

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