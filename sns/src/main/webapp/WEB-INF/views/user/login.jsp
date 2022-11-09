<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width">
<title>OHAll : 로그인</title>
<link rel="icon" href="${path }/resources/img/favicon.png">
<link href="${path }/resources/style/common.css" rel="stylesheet"
	type="text/css" />
<link href="${path }/resources/style/login.css" rel="stylesheet"
	type="text/css" />
<!-- favicon -->
<%-- <link rel="icon" href="${path }/resources/img/favicon.png">
<link rel="instagram-icon" href="${path }/resources/img/favicon.png"> --%>
</head>
<body>
<c:import url="../component/header.jsp" />
	<form action="" method="post">
		<div class="container">
			<img class="logo_instagram"
				src="${path }/resources/img/logo_text.png" alt="instagram_logo">
			<input type="text" class="input_login" name="user_id" id="userID"
				placeholder="아이디">
			<c:if test="${message == false }">
				<script>
			alert("아이디 또는 비밀번호를 확인해 주세요")
		</script>
			</c:if>
			<input type="password" class="input_login" name="user_pw" id="userPW"
				placeholder="비밀번호">
			<button type="submit" id="btn_login" disabled>로그인</button>
			<span class="button_forgot">비밀번호를 잊으셨나요?&nbsp;&nbsp;<a href="#">비밀번호 찾기</a></span>
			<span><a href="/join">회원가입</a></span>
		</div>
	</form>
	<script src="${path }/resources/js/login.js"></script>
</body>
</html>











