<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="/resources/css/subpage.css" rel="stylesheet" type="text/css">

<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->

</head>
<body>
	<div id="wrap">
		<!-- 헤더가 들어가는 곳 -->
		<jsp:include page="/WEB-INF/views/inc/top.jsp"></jsp:include>
		<!-- 헤더가 들어가는 곳 -->

		<!-- 본문 들어가는 곳 -->
		<!-- 서브페이지 메인이미지 -->
		<div id="sub_img"></div>
		<!-- 서브페이지 메인이미지 -->
		<!-- 왼쪽메뉴 -->
		<nav id="sub_menu">
			<ul>
				<li><a href="#">Welcome</a></li>
				<li><a href="#">History</a></li>
				<li><a href="#">Newsroom</a></li>
				<li><a href="#">Public Policy</a></li>
			</ul>
		</nav>
		<!-- 왼쪽메뉴 -->
		<!-- 내용 -->
		<article>
			<h1>Welcome</h1>
			<figure class="ceo">
				<img src="/resources/images/company/CEO.jpg" width="196" height="226" alt="CEO">
				<figcaption>Fun Web CEO Michael</figcaption>
			</figure>
			<p>Lorem ipsum ipsum dolor... tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus</p>
		</article>
		<!-- 내용 -->
		<!-- 본문 들어가는 곳 -->
		<div class="clear"></div>
		<!-- 푸터 들어가는 곳 -->
		<jsp:include page="/WEB-INF/views/inc/bottom.jsp"></jsp:include>
		<!-- 푸터 들어가는 곳 -->
	</div>
</body>
</html>


