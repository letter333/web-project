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
    <title>OHAll : 정보 수정</title>
    <!--CSS-->
    <link rel="stylesheet" href="${path }/resources/style/join.css">
    <!--Iconscout CSS-->
    <link rel="stylesheet" href="http://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
    <div class="container">
        <header>내 정보 수정</header>
        <form action="user_modify" method="POST">
            <div class="form first">
                <div class="details personal">
                    <span class="title">Personal Details</span>
                    <div class="fields">
                        <div class="input-field">
                            <label>아이디</label>
                            <input type="hidden" name="user_id" value="${data.user_id }" />
                            <input type="text" id="user_id" placeholder="아이디" value="${data.user_id }" disabled>
                        </div>
                        <div class="input-field">
                            <label>비밀번호</label>
                            <input id="user_pw" type="password" name="user_pw" placeholder="비밀번호" autocomplete="on" required>
                        </div>
                        <div class="input-field">
                            <label>이름</label>
                            <input type="text" name="user_name" placeholder="이름" value="${data.user_name }" required>
                        </div>

                        <div class="fieldset">
                        <!-- <div class="input-field"> -->
                            <label><span>성별</span></label>
                            <br>
                            <span>
								<c:if test="${data.user_gender == '남성' }">
		                            <label>&nbsp;남성&nbsp;<input type="radio" name="user_gender" value="남성" checked></label>
		                            <label>&nbsp;여성&nbsp;<input type="radio" name="user_gender" value="여성"></label>
		                            <label>&nbsp;비공개&nbsp;<input type="radio" name="user_gender" value="비공개"></label>
		                            </c:if>
		                           
		                            <c:if test="${data.user_gender == '여성' }">
		                            <label>&nbsp;남성&nbsp;<input type="radio" name="user_gender" value="남성"></label>
		                            <label>&nbsp;여성&nbsp;<input type="radio" name="user_gender" value="여성" checked></label>
		                            <label>&nbsp;비공개&nbsp;<input type="radio" name="user_gender" value="비공개"></label>
		                            </c:if>
		                           
		                            <c:if test="${data.user_gender == '비공개' }">
		                            <label>&nbsp;남성&nbsp;<input type="radio" name="user_gender" value="남성"></label>
		                            <label>&nbsp;여성&nbsp;<input type="radio" name="user_gender" value="여성"></label>
		                            <label>&nbsp;비공개&nbsp;<input type="radio" name="user_gender" value="비공개" checked></label>
	                            </c:if>
                            </span>
                        </div>
    
                        <div class="input-field">
                            <label>생일</label>
                            <input type="hidden" name="user_birth" value="${data.user_birth }" />
                            <input type="date" placeholder="생일" value="${data.user_birth }" disabled>
                        </div>
     
                        <div class="input-field">
                            <label>Email</label>
                            <input type="text" name="user_email" placeholder="이메일" value="${data.user_email }" required>
                        </div>
    
                        <div class="input-field">
                            <label>핸드폰 번호</label>
                            <input type="number" name="user_phone" placeholder="휴대폰 번호" value="${data.user_phone }" required>
                        </div>
                        
                        <input type="hidden" name="user_reg_date" value="${data.user_reg_date }" />
                        </div>
                    </div>
                    <br>
                    <br>
                    <br>
                    <c:if test="${chkPwMsg eq false }">
                    	<script>
                    		alert('비밀번호가 틀렸습니다.')                    	
                    	</script>                    
                    </c:if>
                <button class="submit" style="background-color: blue;">
                    <span class="btnText">내 정보 수정 완료</span>
                    <i class="uil uil-navigator"></i>
                    </button>
                    </div>
        </form>
    </div>    
    <script src="${path }/resources/js/join.js"></script>
</body>
</html>








