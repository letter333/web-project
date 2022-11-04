<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책 추가하기</title>
</head>
<body>
	<h1>유저 생성하기</h1>
	<form method="post">
		<input type="hidden" name="reg_date" />
		<p>
			id : <input type="text" name="user_id">
		</p>
		<p>
			pw : <input type="password" name="user_pw">
		</p>
		<p>
			이름 : <input type="text" name="user_name" />
		</p>
		<p>
			성별 : <input type="text" name="user_gender" />
		</p>
		<p>
			생일 : <input type="text" name="user_birth" />
		</p>
		<p>
			이메일 : <input type="text" name="user_email" />
		</p>
		<p>
			전화번호 : <input type="text" name="user_phone" />
		</p>
		<p>
			관심사 : <input type="text" name="user_interest" />
		</p>
		<p>
			<input type="submit" value="저장">
		</p>
	</form>
</body>
</html>











