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
    <title>OHAll : 내 정보</title>
    <link rel="icon" href="${path }/resources/img/favicon.png">
    <!--CSS-->
    <link rel="stylesheet" href="${path }/resources/style/join.css">
    <link rel="stylesheet" href="${path }/resources/style/detail.css">
    <!--Iconscout CSS-->
    <link rel="stylesheet" href="http://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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
</head>
<body>

	<%-- <nav class="navbar bg-light p-0 fixed-top">
		<div class="container-xxl wrapper">
			<a class="navbar-brand m-0 p-0"> <img
				src="${path }/resources/img/logo-removebg.png" alt="Logo"
				width="70" height="70" class="d-inline-block">
				OHAll
			</a>
			<!-- <form class="d-flex" role="search">
				<input class="form-control me-2" type="search" placeholder="Search"
					aria-label="Search">
				일단 보류
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form> -->
			<div>
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
	</nav> --%>
	<c:import url="../component/header.jsp" />
	<div class="left">
		<p style="text-align: center;">프로필 사진</p>
			<c:choose>
				<c:when test="${profileImg eq 'default-image.png' }">
					<img src="${path }/resources/img/default-image.png" class="profileImg" style="width: 200px; height: 200px;" />
				</c:when>
				<c:otherwise>
					<img src="/resources/uploadImg/profileImg/${profileImg }" class="profileImg" style="width: 200px; height: 200px;"/>
				</c:otherwise>						
			</c:choose>
		
		<%-- <img src="/resources/uploadImg/profileImg/${profileImg }" alt="profileImg" class="profileImg" style="width: 200px; height: 200px;" /> --%>
	</div>

    <div class="mycontainer">
	    <c:choose>
	    	<c:when test="${user_id eq data.user_id }">
		        <header>내 정보</header>
	    	</c:when>
	    	<c:otherwise>
	    		<header>유저 정보</header>
	    	</c:otherwise>
	    </c:choose>
        <form action="user_modify" method="get">
            <div class="form first">
                <div class="details personal">
                    <span class="title">Personal Details</span>
                    <div class="fields">
                        <div class="input-field">
                            <label>아이디</label>
                            <input type="text" id="user_id" placeholder="아이디" value="${data.user_id }" disabled>
                        </div>
                        <div class="input-field">
                            <label>이름</label>
                            <input type="text" name="user_name" placeholder="이름" required value="${data.user_name }" disabled>
                        </div>

                        <div class="fieldset">
                        <!-- <div class="input-field"> -->
                            <label><span>성별</span></label>
                            <br>
                            <span>
                            <c:if test="${data.user_gender == '남성' }">
                            <label>&nbsp;남성&nbsp;<input type="radio" name="user_gender" value="남성" checked disabled></label>
                            <label>&nbsp;여성&nbsp;<input type="radio" name="user_gender" value="여성" disabled></label>
                            <label>&nbsp;비공개&nbsp;<input type="radio" name="user_gender" value="비공개" disabled></label>
                            </c:if>
                            
                            <c:if test="${data.user_gender == '여성' }">
                            <label>&nbsp;남성&nbsp;<input type="radio" name="user_gender" value="남성" disabled></label>
                            <label>&nbsp;여성&nbsp;<input type="radio" name="user_gender" value="여성" checked disabled></label>
                            <label>&nbsp;비공개&nbsp;<input type="radio" name="user_gender" value="비공개" disabled></label>
                            </c:if>
                            
                            <c:if test="${data.user_gender == '비공개' }">
                            <label>&nbsp;남성&nbsp;<input type="radio" name="user_gender" value="남성" disabled></label>
                            <label>&nbsp;여성&nbsp;<input type="radio" name="user_gender" value="여성" disabled></label>
                            <label>&nbsp;비공개&nbsp;<input type="radio" name="user_gender" value="비공개" checked disabled></label>
                            </c:if>
                            </span>
                        </div>
    
                        <div class="input-field">
                            <label>생일</label>
                            <input type="date" name="user_birth" placeholder="생일" required value="${data.user_birth }" disabled>
                        </div>
     
                        <div class="input-field">
                            <label>Email</label>
                            <input type="text" name="user_email" placeholder="이메일" required value="${data.user_email }" disabled>
                        </div>
    
                        <div class="input-field">
                            <label>핸드폰 번호</label>
                            <input type="number" name="user_phone" placeholder="휴대폰 번호" required value="${data.user_phone }" disabled>
                        </div>
                        
                        <div class="input-field">
                            <label>가입일</label>
                            <input type="text" name="user_reg_date" placeholder="가입일" value="${data.user_reg_date }" disabled>
                        </div>
                        </div>
                    </div>
                    <br>
                    <br>
                    <br>
                    	<c:if test="${user_id eq data.user_id }">
		                	<button style="background-color: blue;">
			                    <span class="btnText">정보 수정</span>
			                    <i class="uil uil-navigator"></i>
		                    </button>
                    	</c:if>
                    </div>
        </form>
       	<c:if test="${user_id eq data.user_id }">
	        <form action="/withdrawal" method="post" class="withdrawalForm" style="min-height: 0; margin: 0;">
	        	<input type="hidden" name="user_id" value="${user_id }" />
	        	<p class="withdrawalBtn" style="font-size: 14px; font-weight: 400; color: red; cursor: pointer; display: inline;">회원탈퇴</p>
	        </form>
       	</c:if>
    </div>
    
    <script>
    	let withdrawalBtn = document.querySelector('.withdrawalBtn')
    	let withdrawalForm = document.querySelector('.withdrawalForm')
    	
    	withdrawalBtn.addEventListener('click', () => {
    			if(confirm('회원탈퇴?')) {
    				withdrawalForm.submit()
    			} else {
    				return
    			}
	    	}
   		)
    </script>
    <script src="${path }/resources/js/join.js"></script>    
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
</body>
</html>








