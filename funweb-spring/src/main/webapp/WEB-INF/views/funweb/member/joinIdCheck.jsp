<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Id Check</title>
<script>
function useIdAndClose() {
	// 현재 창을 띄운 부모창의 참조는 window.opener
	// window(현재 창)는 일반적으로 생략가능
	opener.document.form.id.value = document.form.userID.value;
	window.close();
}
</script>
</head>
<link href="/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="/resources/css/subpage.css" rel="stylesheet" type="text/css">
<style>
body {
	background-color: white;
	width: 380px;
}

#join {
	width: 380px;
}
</style>
<body>
	<c:choose>
		<c:when test="${idDup }">
사용중인 아이디 입니다.
</c:when>
		<c:otherwise>
사용 가능한 아이디 입니다.
<input type="button" value="Use this ID" onclick="useIdAndClose();">
		</c:otherwise>
	</c:choose>

	<hr>
	<form action="/member/joinIdCheck" method="get" id="join" name="form">
		<label>User ID</label> <input type="text" name="userID" class="id"
			value="${param.userID }"> <input type="submit"
			value="Dup. Check" class="dup">
	</form>

</body>
</html>