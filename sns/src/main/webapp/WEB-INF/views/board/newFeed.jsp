<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>피드 작성</title>
</head>
<body>
<form method="post" enctype="multipart/form-data">
<input type="text" name="feed_user_id" value="${user_id }" />
<input type="text" name="feed_title" />
<input type="text" name="feed_content" />
<input type="file" name="uploadFile" />
<input type="file" name="uploadFile" />
<input type="file" name="uploadFile" />
<c:if test="${message == false }">
	<script>
		alert("글 작성 실패")
	</script>
</c:if>
<input type="submit" value="작성하기" />
</form>
</body>
</html>