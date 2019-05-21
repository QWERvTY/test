<%@page import="java.util.Map"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.List"%>
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
<style>
.red {
	color: red;
}

.title a {
	text-decoration: none;
	color: black;
}

.title a:hover {
	text-decoration: underline;
}

#reply {
	color: #BBBBBB;
}
</style>
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
			<c:choose>
				<c:when test="${search == null || search.length() == 0 }">
					<h2>File Notice - ${pageInfoMap.allRowCount }개</h2>
				</c:when>
				<c:otherwise>
					<h2>Search Results - ${pageInfoMap.allRowCount }개</h2>
				</c:otherwise>
			</c:choose>

			<table id="notice">
				<tr>
					<th class="tno">No.</th>
					<th class="ttitle">Title</th>
					<th class="twrite">Writer</th>
					<th class="tdate">Date</th>
					<th class="tread">Read</th>
				</tr>
				<c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="5" align="center">작성된 게시글이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
				
				</c:otherwise>
				</c:choose>
				
				<c:forEach items="${list }" var="v">
					<tr onclick="location.href='/board/fileDetail?num=${v.num }&name=${v.name }&pageNum=${pageInfoMap.pageNum }';">
						<td>${v.num }</td>
						<td class="title left"><c:if test="${v.reLev > 0 }">
								<c:forEach begin="1" end="${v.reLev }">
									&nbsp;
								</c:forEach>
								<span id="reply">└</span>
							</c:if>
							<a href="/board/fileDetail?num=${v.num }&name=${v.name }&pageNum=${pageInfoMap.pageNum }">
								${v.subject }</a></td>
						<td>${v.name }</td>
						<td><jsp:useBean id="today" class="java.util.Date"></jsp:useBean>
								<fmt:parseNumber value="${today.time / (1000 * 60 * 60 * 24)}"
									var="nowDays" integerOnly="true" />
								<fmt:parseNumber value="${v.regDate.time / (1000 * 60 * 60 * 24)}"
									var="regDays" integerOnly="true" />
								<c:set value="${nowDays - regDays }" var="dayDiff" />
								<c:choose>
									<c:when test="${dayDiff == 0 }">
										<fmt:formatDate value="${v.regDate }" pattern="HH:mm:ss" />
									</c:when>
									<c:otherwise>
										<fmt:formatDate value="${v.regDate }" pattern="yyyy.MM.dd" />
									</c:otherwise>
								</c:choose></td>
						<td>${v.readCount }</td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${sessionID != null }">
				<div id="table_search">
					<input type="button" value="FileUpload" class="btn"
						onclick="location.href='/board/fileWrite';">
				</div>
			</c:if>
			<div id="table_search">
				<form action="/board/filelist" method="get">
					<input type="text" name="search" class="input_box" value=${search }>
					<input type="submit" value="Search" class="btn">
				</form>
			</div>

			<div class="clear"></div>
			<div id="page_control">
				<c:if test="${pageInfoMap.allRowCount > 0 }">

					<c:if test="${pageInfoMap.startPage > pageInfoMap.pageBlockSize }">
						<a href="/board/filelist?pageNum=1&search=${search }">1</a>...
						<a href="/board/filelist?pageNum=${pageInfoMap.startPage - 1 }&search=${search }">Prev</a>
					</c:if>

					<c:forEach begin="${pageInfoMap.startPage }"
						end="${pageInfoMap.endPage }" step="1" varStatus="s">
						<a href="/board/filelist?pageNum=${s.current }&search=${search }">
							<c:choose>
							<c:when test="${s.current == pageInfoMap.pageNum }">
								<span class="red"><b>${s.current }</b></span>
							</c:when>
							<c:otherwise>
								${s.current }
							</c:otherwise>
							</c:choose>
						</a>
					</c:forEach>

					<c:if test="${pageInfoMap.endPage < pageInfoMap.maxPage }">
						<a href="/board/filelist?pageNum=${pageInfoMap.endPage + 1 }&search=${search }">Next</a>...
						<a href="/board/filelist?pageNum=${pageInfoMap.maxPage }&search=${search }">${pageInfoMap.maxPage }</a>
					</c:if>

				</c:if>

			</div>
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