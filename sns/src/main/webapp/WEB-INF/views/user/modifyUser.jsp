<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보 수정</title>
<script>
	function goupdate(frm) {
		var user_pw = frm.user_pw.value;
		var user_pw2 = frm.user_pw2.value;

		if (user_pw == '') {
			alert("비밀번호를 입력해주세요");
			return false;
		}
		if (user_pw != user_pw2) {
			alert("비밀번호가 서로 다릅니다");
			return false;
		}
		return frm.submit();
	}
</script>
</head>
<body>
	<h2 style="text-align: center;">회원정보 수정</h2><br><br>
	<div style="width: 80%; margin: auto;">
		<form action="user_update" method="post">
			<input type="file" id="poto" name="poto" accept="image/*" required><br><br>
			<label for="id">아이디</label> <input type="text" id="user_id" name="user_id" value="tset" value="${data.user_id }" readonly/><br><br>
			<label for="pw1">비밀번호</label> <input type="password" id="pw1" name="user_pw" placeholder="비밀번호" /><br><br>
			<label for="pw2">비밀번호</label> <input type="password" id="pw2" name="user_pw2" placeholder="비밀번호 확인" /><br><br>
			<label for="name">&nbsp;&nbsp;이&nbsp;&nbsp;름&nbsp;&nbsp;</label> <input type="text" id="name" name="user_name" placeholder="이름" value="${data.user_name }" /><br><br>
			<label for="email">&nbsp;&nbsp;이메일&nbsp;</label> <input type="text" id="email" name="user_email" placeholder="이메일" value="${data.user_email }" /><br><br>
			<label for="ph">전화번호</label> <input type="text" id="ph" name="user_phone" placeholder="전화번호" value="${data.user_phone }" /><br><br>
			<label for="regdate">가입일</label><input type="text" id="regdate" value="${data.user_reg_date }" readonly /><br><br>
			<input type="button" value="회원정보 수정완료" onclick="goupdate(this.form)" />
		</form>
	</div>
</body>
</html>