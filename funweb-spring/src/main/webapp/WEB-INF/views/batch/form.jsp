<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Batch</title>
<link href="/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="/resources/css/subpage.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="/resources/images/favicon.png">
<script src="/resources/script/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function() {
		$('#frm').on('submit', function(e) {
			var strResDate = $('input[type="datetime-local"]').val();
// 			alert(strResDate);
			
			var resDate = new Date(strResDate); // 예약 날짜 시간 
			var nowDate = new Date(); // 현재 날짜 시간
			
			console.log('resDate.getTime() : ' + resDate.getTime());
			console.log('nowDate.getTime() : ' + nowDate.getTime());
			if (resDate.getTime() <= nowDate.getTime()) {
				alert('미래 날짜를 입력하세요.');
				return false;
			}

			strResDate = strResDate.replace('T', ' ');
			$('#datetime').val(strResDate);
			
			var time = $('input:radio[name="time"]:checked').val();
			var number = $('input[name="interval"]').val();
			alert(time + " : " + number);
			
			var period = 0;
			if (time == 'second') {
				period = 1000 * number;
			} else if (time == 'minute') {
				period = 1000 * 60 * number;
			} else if (time == 'hour') {
				period = 1000 * 60 * 60 * number;
			} else if (time == 'date') {
				period = 1000 * 60 * 60 * 24 * number;
			}
			alert(period);
			
			$('input#period').val(period);
			console.log('period : ' + period);
			
// 			e.preventDefault();
			return true;
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
			<h1>배치 처리 예약하기</h1>
			<form action="/batch/process" method="POST" id="frm">
				예약 날짜 시간 : <input type="datetime-local" name="datetime-local"><br><br>
				<input type="hidden" name="datetime" id="datetime">
				실행 간격(일/시간/분/초) 선택 : <br>
				<input type="radio" name="time" value="date"> 일
				<input type="radio" name="time" value="hour"> 시간
				<input type="radio" name="time" value="minute"> 분
				<input type="radio" name="time" value="second"> 초<br><br>
				실행 간격 값 : <input type="number" min="0" name="interval">
				<input type="hidden" name="period" id="period">
				<button type="submit">예약하기</button>
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