<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Page</title>
<link href="/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="/resources/css/front.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="/resources/images/favicon.png">

<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->

</head>
<body>
	<%
String id = (String) session.getAttribute("sessionID");
System.out.println("Main Page, ID : " + id);
%>
	<div id="wrap">
		<!-- 헤더파일들어가는 곳 -->
		<jsp:include page="/WEB-INF/views/inc/top.jsp"></jsp:include>
		<!-- 헤더파일들어가는 곳 -->
		<!-- 메인이미지 들어가는곳 -->
		<div class="clear"></div>
		<div id="main_img">
			<img src="/resources/images/main_img.jpg" width="971" height="282">
		</div>
		<!-- 메인이미지 들어가는곳 -->
		<!-- 메인 콘텐츠 들어가는 곳 -->
		<article id="front">
			<div id="solution">
				<div id="hosting">
					<h3>Broad platform support</h3>
					<p>With AWS, you can use whatever CMS you like, including WordPress, 
					Drupal, Joomla, and more. AWS also supports and provides SDKs 
					for popular platforms like Java, Ruby, PHP, Node.js, and .Net.</p>
				</div>
				<div id="security">
					<h3>Datacenters worldwide</h3>
					<p>Your customers can be anywhere in the world. With AWS you can have a datacenter or CDN hosting your website in any geography you choose with just a few mouse clicks.</p>
				</div>
				<div id="payment">
					<h3>Scalable from day one</h3>
					<p>Website traffic can fluctuate a lot. From quiet times in the middle of the night, to campaign driven, social media sharing traffic spikes, AWS infrastructure that can grow and shrink to meet your needs.</p>
				</div>
			</div>
			<div class="clear"></div>
			<div id="sec_news">
				<h3>
					<span class="orange">Security</span> News
				</h3>
				<dl>
					<dt>Simple Website Hosting</dt>
					<dd>Simple websites typically consist of a single web server which runs either a Content Management System (CMS), such as WordPress, an eCommerce application, such as Magento, or a development stack, like LAMP.</dd>
				</dl>
				<dl>
					<dt>Single Page Web App Hosting</dt>
					<dd>Static web apps that require only a single load in a web browser are referred to as Single page web apps. All subsequent actions by the user are made available through HTML, JavaScript, and CSS that are pre-loaded in the browser.</dd>
				</dl>
			</div>
			<div id="news_notice">
				<h3 class="brown">News &amp; Notice</h3>
				<table>
				<c:forEach items="${list }" var="l">
					<tr>
						<td class="contxt"><a href="#">${l.subject }</a></td>
						<td>
						<jsp:useBean id="today" class="java.util.Date" />
						<fmt:parseNumber value="${today.time / (1000 * 60 * 60 * 24)}"
							var="nowDays" integerOnly="true" />
						<fmt:parseNumber value="${l.regDate.time / (1000 * 60 * 60 * 24)}"
							var="regDays" integerOnly="true" />
						<c:set value="${nowDays - regDays }" var="dayDiff" />
						<c:choose>
							<c:when test="${dayDiff == 0 }">
								<fmt:formatDate value="${l.regDate }" pattern="HH:mm:ss" />
							</c:when>
							<c:otherwise>
								<fmt:formatDate value="${l.regDate }" pattern="yyyy.MM.dd" />
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
				</c:forEach>
				</table>
			</div>
		</article>
		<!-- 메인 콘텐츠 들어가는 곳 -->
		<div class="clear"></div>
		<!-- 푸터 들어가는 곳 -->
		<jsp:include page="/WEB-INF/views/inc/bottom.jsp"></jsp:include>
		<!-- 푸터 들어가는 곳 -->
	</div>
</body>
</html>