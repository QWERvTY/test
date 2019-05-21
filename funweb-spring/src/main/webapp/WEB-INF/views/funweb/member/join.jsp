<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SignUp</title>
<link href="/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="/resources/css/subpage.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<script src="/resources/script/jquery-3.3.1.min.js"></script>
<script>
$(function() {
	$('input[name=id]').on('keyup', function(event) {
		var id = $(this).val();
		console.log('id 변수 타입 : ' + typeof id);
		console.log('id : ' + id);
		
		$.ajax({
			url: '/member/joinIdCheckJson', // 'joinIdCheckJson.do?userid=id입력값' - data속성 사용하지 않을 시
			data: {userID: id}, // userID = id입력값
			success: function(isDup) {
				console.log('result 변수 타입 : ' + typeof(isDup));
				console.log('result : ' + isDup);
				
				if (isDup) {
					$('span#dupCheck').html('이미 존재하는 아이디입니다.').css('color', '#FF0000');
				} else {
					$('span#dupCheck').html('사용 가능한 아이디입니다.').css('color', '#309050');
				}
				
			}
		});
	});
});
</script>
<script>
function formCheck() {
	
	if (form.id.value.length < 5) { // form.id.value.length < 5 / form.id.value == ''
		alert("아이디는 5글자 이상으로 입력해주세요.");
		form.id.focus();
		return false;
	}
	
	if (form.pw.value != form.pw2.value) {
		alert("두 비밀번호가 서로 다릅니다.");
		form.pw.focus();
		return false;
	}
	
	if (form.email.value != form.email2.value) {
		alert("두 이메일 주소가 서로 다릅니다.");
		form.email.focus();
		return false;
	}
	return true;
}

function idDupCheck() {
	var id = form.id.value;
	if (id.length == 0) {
		alert("아이디를 입력하세요.");
		form.id.focus();
		return;
	}
	var ChildWindow = window.open('/member/joinIdCheck?userID=' + id, '', 'width=400,height=200'); // 새 창 열기
}
</script>
</head>
<body>
<div id="wrap">
	<!-- 헤더들어가는 곳 -->
	<jsp:include page="/WEB-INF/views/inc/top.jsp"></jsp:include>
	<!-- 헤더들어가는 곳 -->

	<!-- 본문들어가는 곳 -->
	<!-- 본문메인이미지 -->
	<div id="sub_img_member"></div>
	<!-- 본문메인이미지 -->
	<!-- 왼쪽메뉴 -->
	<nav id="sub_menu">
		<ul>
			<li><a href="#">Join us</a></li>
			<li><a href="#">Privacy policy</a></li>
		</ul>
	</nav>
	<!-- 왼쪽메뉴 -->
	<!-- 본문내용 -->
	<article>
		<h1>Join Us</h1>
		<form action="/member/join" id="join" name="form" method="POST"
			onsubmit="return formCheck();">
			<fieldset>
				<legend>Basic Info</legend>
				<label>User ID</label>
				<input type="text" name="id" class="id" required>
				<input type="button" value="dup. check"	class="dup" onclick="idDupCheck();">
				<span id="dupCheck"></span><br>
				
				<label>Password</label>
				<input type="password" name="password" required><br>
				<label>Retype Password</label>
				<input type="password" name="password2" required><br>
				<label>Name</label>
				<input type="text" name="name" required><br>
				<label>E-Mail</label>
				<input type="email" name="email" required><br>
				<label>Retype E-Mail</label>
				<input type="email" name="email2" required><br>
			</fieldset>

			<fieldset>
				<legend>Optional</legend>
				<label>Gender</label>
				<p>
					<input type="radio" name="gender" value="남">M
					<input type="radio" name="gender" value="여">W
				</p>
				<label>Birthday</label> <input type="date" name="birthday"><br>
				<label>Address</label> <input type="text" name="address"><br>
				<label>Phone Number</label> <input type="text" name="tel"><br>
				<label>Mobile Phone Number</label> <input type="text" name="mTel"><br>
			</fieldset>
			<div class="clear"></div>
			<div id="buttons">
				<input type="submit" value="Submit" class="submit">
				<input type="reset" value="Reset" class="cancel">
			</div>
		</form>
	</article>
	<!-- 본문내용 -->
	<!-- 본문들어가는 곳 -->

	<div class="clear"></div>
	<!-- 푸터들어가는 곳 -->
	<jsp:include page="/WEB-INF/views/inc/bottom.jsp"></jsp:include>
	<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>