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
<script src="/resources/script/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function() {
	$('button#btn').on('click', function() {
		$('div#newUploadFiles').append('<input type="file" name="newFiles" multiple="multiple"><br>');
	});
	
	$('span#del').on('click', function() {
		val $li = $(this).closest('li');
		
		$li.children('input[type="hidden"]').attr('name', 'delFiles');
		$li.children('div.attach-item').remove();
	});
});
</script>
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
	<form action="/board/fileWrite" method="POST" name="form" enctype="multipart/form-data">
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
				<th>File</th>
				<td class="left">
				<c:if test="${not empty attachList }">
				<ul>
				<c:forEach items="#{attachList }" var="attach">
					<li>
					<div class="attach-item">
					<c:if test="${attach.fileType eq 'I'}">
						<img src="/resources/images/center/p_png_s.gif">
					</c:if>
					<c:if test="${attach.fileType eq 'O'}">
						<img src="/resources/images/center/p_etc_s.gif">
					</c:if>
					${attach.fileName}
					<span id="del"><img src="/resources/images/close.png" width="10px"></span>
					</div>
					<input type="hidden" name="oldFiles" value="${attach.uploadPath}/${attach.uuid}_${attach.fileName}">
					</li>
				</c:forEach>
				</ul>
				</c:if>
				<button type="button" id="btn">새로 업로드</button>
				<div id="newUploadFiles"></div>
				</td>
			</tr>
			<tr>
				<th>Content</th>
				<td><textarea rows="13" cols="30" name="content" style="resize: none;">${board.content }</textarea></td>
			</tr>
		</table>
		<div id="table_search">
			<input type="submit" value="FileWrite" class="btn">
			<input type="reset" value="Reset" class="btn">
			<input type="button" value="List" class="btn"
				onclick="location.href='/board/filelist?pageNum=${param.pageNum}';">
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