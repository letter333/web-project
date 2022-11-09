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
    <title>OHAll : 회원가입</title>
    <link rel="icon" href="${path }/resources/img/favicon.png">
    <!--CSS-->
    <link rel="stylesheet" href="${path }/resources/style/join.css">
    <!--Iconscout CSS-->
    <link rel="stylesheet" href="http://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<c:import url="../component/header.jsp" />
    <div class="container">
        <header>회원가입</header>
        <form action="join" method="POST">
            <div class="form first">
                <div class="details personal">
                    <span class="title">Personal Details</span>
                    <div class="fields">
                        <div class="input-field">
                            <label>아이디</label>
                            <input type="text" id="user_id" name="user_id" placeholder="아이디" required>
                        </div>
                        <div class="input-field">
                            <label>비밀번호</label>
                            <input id="user_pw" type="password" name="user_pw" placeholder="비밀번호" autocomplete="on" required>
                        </div>
                        <div class="input-field">
                            <label>이름</label>
                            <input type="text" name="user_name" placeholder="이름" required>
                        </div>
                        <div class="input-field">
                        	<label>아이디 중복 확인</label>
                        	<input type="button" id="idChk" style="border: 0; outline: none; background-color: #4070f4; color: #fff;" value="아이디 중복 확인" />
                        </div>
                        
                        <div class="input-field">
                        	<label>비밀번호 확인&nbsp;&nbsp;&nbsp;<span id="checkNotice"></span></label>
                        	<input type="password" id="pw_check" placeholder="비밀번호 확인" autocomplete="on" required />
                        </div>

                        <div class="fieldset">
                        <!-- <div class="input-field"> -->
                            <label><span>성별</span></label>
                            <br>
                            <span>
                            <label>&nbsp;남성&nbsp;<input type="radio" name="user_gender" value="남성"></label>
                            <label>&nbsp;여성&nbsp;<input type="radio" name="user_gender" value="여성"></label>
                            <label>&nbsp;비공개&nbsp;<input type="radio" name="user_gender" value="비공개"></label>
                            </span>
                        </div>
    
                        <div class="input-field">
                            <label>생일</label>
                            <input type="date" name="user_birth" placeholder="생일" required>
                        </div>
     
                        <div class="input-field">
                            <label>Email</label>
                            <input type="text" name="user_email" placeholder="이메일" required>
                        </div>
    
                        <div class="input-field">
                            <label>핸드폰 번호</label>
                            <input type="number" name="user_phone" placeholder="휴대폰 번호" required>
                        </div>
                        </div>
                    </div>
                    <br>
                    <br>
                    <br>
                <button class="submit" style="background-color: #d0d5db" disabled>
                    <span class="btnText">회원가입</span>
                    <i class="uil uil-navigator"></i>
                    </button>
                    </div>
        </form>
    </div>    
    <script src="${path }/resources/js/join.js"></script>
    <script>
    	$(function() {
    		$('#user_pw').keyup(function() {
    			$('#checkNotice').html('');
    		});
    		
    		$('#pw_check').keyup(function() {
    			if($('#user_pw').val() != $('#pw_check').val()) {
    				$('#checkNotice').html('비밀번호가 일치하지 않음.');
    				$('#checkNotice').attr('style', 'color:red;');
    				$('.submit').attr('style', 'background-color : #d0d5db');
    				$('.submit').attr('disabled', true);
    			} else {
    				$('#checkNotice').html('비밀번호 일치');
    				$('#checkNotice').attr('style', 'color:blue;');
    				$('.submit').removeAttr('style');
    				$('.submit').removeAttr('disabled');
    			}
    		})
    		
    		$('#idChk').click(function() {
        		let user_id = $('#user_id').val().trim();
        		
        		if(user_id == '') {
        			alert('아이디를 입력해 주세요')
        		}else {
    	    		$.ajax({
    	    			url : "/idChk",
    	    			type : "post",
    	    			dataType : 'json',
    	    			data : {user_id : user_id},
    	    			success : function(result) {
    	    				if(result == 1) {
    	    					$('.submit').attr('disabled', true);
    	    					alert('이미 사용중인 아이디입니다.');
    	    				} else {
    							$('.submit').attr('disabled', false);
    							$('#user_id').attr('readonly', true);
    							$('#idChk').attr('disabled', true);
    							$('#idChk').css('background-color', '#e3e3e3');
    							$('.submit').removeAttr('style');
    	    					alert('사용 가능한 아이디 입니다.');
    	    				}
    	    			},
    	    			error : function() {
    	    				alert('서버 요청에 실패했습니다.');
    	    			}
    	    		})
        		}
        	})
        	
    	})
    	
    
    
    	
    	
    </script>
</body>
</html>








