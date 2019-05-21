<%-- <%@page import="vo.Board"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice</title>
<link href="/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="/resources/css/subpage.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="/resources/images/favicon.png">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
</head>
<style>
#board {
	width: 670px;
}

#content {
	padding: 20px 0;
}

pre {
	font-size: 12px;
	font-family: sans-serif;
}

a {
	text-decoration: none;
}
</style>
<body>
	<%--
/*
	디스패치 방식로 jsp뷰로 이동할때
	기존의 동일 request객체가 전달됨!
	request.setAttribute("num", num); // 비효율적!
	글번호num을 영역객체에 따로 저장할 필요가 없음.
	동일 request객체이므로 글번호 파라미터로 바로 찾을수 있음!
*/
/*
Board b = (Board) request.getAttribute("board");
String content = "";
if (b.getContent() != null) {
	content = b.getContent().replace("\r\n", "<br>");
}
*/
--%>
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
			<h1>Notice</h1>
			<table id="notice">
				<tr height="45px">
					<th>${board.subject }</th>
					<th align="right">No. ${board.num }</th>
				</tr>
			</table>
			<table id="board">
				<tr>
					<td align="left">Writer : ${board.name }</td>
					<td align="right">
					<fmt:formatDate value="${board.regDate }" pattern="yyyy.MM.dd HH:mm:ss" /></td>
				</tr>
				<%-- <tr height="500px" valign="top"><td id="content" colspan="2"><%=content %></td></tr> --%>
				<tr height="500px" valign="top">
					<td id="content" colspan="2"><pre>${board.content }</pre></td>
				</tr>
				<tr>
					<td colspan="2" align="right">views : ${board.readCount }</td>
				</tr>
			</table>
			<!-- 글 내용 줄바꿈 처리 : 1. <pre>태그 처리. 2. /r/n -> <br>로 바꾸기 -->
			<div id="table_search">
				<c:if test="${sessionID != null }">
				
				<c:choose>
					<c:when test="${board.name.equals(sessionID) }">
						<a href="/board/modify?num=${board.num }&pageNum=${param.pageNum }">
						<button	class="btn">Modify</button></a>
						<a href="/board/delete?num=${board.num }&pageNum=${param.pageNum }">
						<button	class="btn">Delete</button></a>
					</c:when>
					<c:when test="${sessionID.equals('admin') }">
						<a href="/board/delete?num=${board.num }&pageNum=${param.pageNum }">
						<button	class="btn">Delete</button></a>
					</c:when>
				</c:choose>
					<a href="/board/reply?reRef=${board.reRef }&reLev=${board.reLev }&reSeq=${board.reSeq }&pageNum=${param.pageNum }">
						<button class="btn">Reply</button>
					</a>
				</c:if>
				<a href="/board/list?pageNum=${param.pageNum }"><button class="btn">List</button></a>
			</div>

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