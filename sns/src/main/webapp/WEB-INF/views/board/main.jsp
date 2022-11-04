<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
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

<style>
.wrapper {
	width: 1080px;
}

i {
	margin: 5px;
}

.carousel-inner>.carousel-item>img {
	top: 0;
	left: 0;
	height: 500px;
}

.profile-header {
	height: 70px;
	display: flex;
	align-items: center;
}

.content {
	min-height: 100px;
}

.profileImg {
	width: 50px;
	height: 50px;
	border: 1px solid;
}

.carousel-btn {
	height: 500px;
	margin-top: 70px;
}

.time {
	font-size: 6px;
}

a {
	color: black;
}

a:hover {
	color: black;
}

.comment-icons {
	bottom: 10px;
	right: 5%;
}

#toggle {
	cursor: pointer;
}

.comment-input {
	width: 93%;
	display: inline-block;
}

.comment-wrapper {
	padding: 0;
    margin: 10px;
    margin-right: 5px;
}

.comment-wrapper>button {
	width: 5%;
}

.send-icon {
	padding: 0;
	margin: 0;
	border: 0;
	outline: none;
	background-color: transparent;
}
</style>
</head>
<body>
	<nav class="navbar bg-light">
		<div class="container-xxl wrapper">
			<a class="navbar-brand"> <img
				src="/docs/5.2/assets/brand/bootstrap-logo.svg" alt="Logo"
				width="30" height="24" class="d-inline-block align-text-top">
				여기 이름
			</a>
			<form class="d-flex" role="search">
				<input class="form-control me-2" type="search" placeholder="Search"
					aria-label="Search">
				<!-- 일단 보류 -->
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form>
			<div>
				<!-- 세션에 아이디 없으면 로그인 띄우기 -->
				<c:choose>
					<c:when test="${empty user_id }">
						<a href="/login"><i class="fa-solid fa-right-to-bracket fa-lg"></i></a>
					</c:when>
					<c:otherwise>
						<a href="/logout"><i class="fa-solid fa-user fa-lg"></i></a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</nav>
	<div class="feed wrapper m-auto row container p-0">
		<div class="col-8 p-0">
			<c:forEach var="feed" items="${feedList }" varStatus="status">

				<div id="carouselExampleIndicators${status.count }"
					class="carousel slide border shadow rounded mt-5"
					data-bs-ride="true">
					<div class="rounded-top border-bottom profile-header">
						<div class="profileImg rounded-circle ms-2">프로필 사진</div>
						<div class="d-flex flex-column ms-3">
							<div>${feed.feed_user_id }</div>
							<div class="time">${feed.feed_created_at }</div>
						</div>
						<div class="ms-auto me-3">메뉴</div>
					</div>
					<div class="carousel-inner">
						<c:forEach var="uploadFile" items="${uploadFileList }">
							<c:if test="${feed.feed_id eq uploadFile.feed_id }">
								<div class="carousel-item active">
									<img src="/resources/uploadImg/${uploadFile.file_name }"
										class="d-block w-100" alt="...">
								</div>
							</c:if>
						</c:forEach>
					</div>
					<button class="carousel-control-prev carousel-btn" type="button"
						data-bs-target="#carouselExampleIndicators${status.count }"
						data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next carousel-btn" type="button"
						data-bs-target="#carouselExampleIndicators${status.count }"
						data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
					<div class="border-top rounded-bottom content position-relative">
						<p class="m-2 d-inline">${feed.feed_content }</p>
						<div class="position-absolute comment-icons">
							<c:forEach var="commentCount" items="${commentCountList }">
								<c:if test="${feed.feed_id eq commentCount.comment_feed_id }">
									<i class="fa-solid fa-comments" id="toggle" onclick='$("#comments${status.count}").toggle(500)'>&nbsp;${commentCount.comment_count }</i> 
								</c:if>
							</c:forEach>
							<i class="fa-regular fa-heart">&nbsp;0</i>
						</div>
					</div>
					<div class="border-top" id="comments${status.count }" style="display: none;">
					<c:forEach var="comment" items="${commentList }">
						<c:if test="${feed.feed_id eq comment.comment_feed_id }">
							<p class="m-2">${comment.comment_user_id} : ${comment.comment_content }</p>
						</c:if>
					</c:forEach>
					<%--ajax로 바꿔야됨 --%>
						<c:choose>
							<c:when test="${empty user_id }">
								<div class="comment-wrapper">
									<input class="form-control comment-input d-inline" type="text"
										placeholder="로그인 후 입력가능합니다." name="comment_content" readonly>
									<button type="submit"
										class="d-inline fa-solid fa-paper-plane fa-lg send-icon" disabled></button>
								</div>
							</c:when>
							<c:otherwise>
								<form action="/new_comment" method="post">
								<input type="hidden" name="comment_feed_id" value="${feed.feed_id }" />
								<input type="hidden" name="comment_user_id" value="${user_id }" />
									<div class="comment-wrapper">
										<input class="form-control comment-input d-inline" type="text"
											placeholder="댓글 입력하기" name="comment_content">
										<button type="submit"
											class="d-inline fa-solid fa-paper-plane fa-lg send-icon"></button>
									</div>
								</form>								
							</c:otherwise>
						</c:choose>
					</div>
				</div>

			</c:forEach>
		</div>
		<div class="col-4 mt-5 ps-5">여기쯤 d3차트 하나?</div>
	</div>
	<script>
      /* $(function() {
        $("#toggle").on("click", function() {
          $("#comments").toggle(500);
        });
      }); */
      </script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
</body>
</html>