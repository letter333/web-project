<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
img {
width: 100px;
height: 100px;
}
</style>
<title>Insert title here</title>
</head>
<body>
<c:forEach var="uploadFile" items="${uploadFileList }">
<tr>
<td><img src="/resources/upload/${uploadFile.file_name }" alt="" /></td>
</tr>
		
</c:forEach>
</body>
</html>