<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
	integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link href="${path }/resources/style/main.css" rel="stylesheet"
	type="text/css" />
	
<style>
.logoImg {
	width: 70px;
	height: 70px;
}
</style>
</head>
<body>
	<nav class="navbar bg-light p-0 fixed-top">
		<div class="container-xxl wrapper">
			<a class="navbar-brand m-0 p-0"> <img
				src="${path }/resources/img/logo-removebg.png" alt="Logo"
				class="d-inline-block logoImg">
				OHAll
			</a>
			<!-- <form class="d-flex" role="search">
				<input class="form-control me-2" type="search" placeholder="Search"
					aria-label="Search">
				일단 보류
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form> -->
			<div>
				<a href="/"><i class="fa-solid fa-house fa-lg"></i></a>
				<!-- 세션에 아이디 없으면 로그인 띄우기 -->
				<c:choose>
					<c:when test="${empty user_id }">
						<a href="/login"><i class="fa-solid fa-right-to-bracket fa-lg"></i></a>
					</c:when>
					<c:otherwise>
						<a href="/new_feed"><i class="fa-solid fa-square-plus fa-lg"></i></a>
						<a href="/user_detail?user_id=${user_id }"><i class="fa-solid fa-user fa-lg"></i></a>
						<a href="/logout"><i class="fa-solid fa-right-from-bracket fa-lg"></i></a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</nav>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
</body>
</html>