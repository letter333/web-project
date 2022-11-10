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
<link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
rel="stylesheet"
integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
crossorigin="anonymous">
<link href="${path }/resources/style/login.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
<c:import url="../component/header.jsp" />
<form method="post" style="padding-top: 200px;">
<div class="container" style="width: 700px;">
  <div class="row mb-3 mt-3">
    <div class="">
    <label for="inputEmail3" class="col-4 col-form-label mt-5">아이디</label>
      <input type="text" class="form-control" id="inputEmail3" name="user_id">
    </div>
  </div>
  <div class="row mb-3">
    <div class="">
    <label for="inputPassword3" class="col-4 col-form-label d-inline">비밀번호</label>
      <input type="password" class="form-control d-inline" id="inputPassword3" name="user_pw">
    </div>
  </div>
  <button type="submit" class="btn btn-primary">로그인</button>
  <a href="/join" style="font-size: 13px;">회원가입</a>
  <a href="" style="font-size: 13px;">비밀번호 잊음</a>
</div>
</form>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous">
</script>
</body>
</html>