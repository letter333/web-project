<%@ page language="java" 
contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책 추가하기</title>
</head>
<body>
<h1>로그인</h1>
<form method="post">
<input type="hidden" name="reg_date" />
<p>id : <input type="text" name="user_id"> </p>
<p>pw : <input type="password" name="user_pw"></p>
<p><input type="submit" value="저장"> </p>
</form>
</body>
</html> -->
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/XSL/Transfo">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>Instagram</title>
    <link href="/style/common.css" rel="stylesheet" type="text/css" />
    <link href="/style/login.css" rel="stylesheet" type="text/css" />

    <!-- favicon -->
    <link rel="icon" href="/img/favicon.png">
    <link rel="instagram-icon" href="/img/favicon.png">

</head>
<body>
<div class="container">
    <form method="post">
        <img class="logo_instagram" src="/img/logo_text.png" alt="instagram_logo">
        <input type="text" class="username" name="user_id" id="user_id" placeholder="이메일">
        <input type="password" class="password" name="user_pw" id="user_pw" placeholder="비밀번호">
        <button type="submit" id="btn_login">로그인</button>
    </form>
    <span class="login_input_check" th:if="${param.error}" > 입력한 계정을 찾을 수 없습니다. </span>
    <!-- Oauth 소셜로그인 -->
    <div class="login_facebook">
        <img class="logo_facebook" src="/img/facebook_icon.png">
        <a href="/oauth2/authorization/facebook" class="btn_facebook">Facebook으로 로그인</a>
    </div>
    <!-- Oauth 소셜로그인end -->
    <a href="/signup" class="button_signup">계정이 없으신가요? 가입하기</a>
</div>
<script src="/js/login.js"></script>
</body>
</html>










