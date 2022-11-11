<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="${path }/resources/img/favicon.png">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OHAll : 피드 작성</title>
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
.container {
	width: 1200px;
}


li {
	list-style: none;
	display: inline;
}

img {
	width: 200px;
	height: 200px;
}

.real-upload {
	display: none;
}

.upload {
	cursor: pointer;
}

textarea {
	width: 100%;
	height: 300px;
	resize: none;
}

</style>
</head>
<body>
<c:import url="../component/header.jsp" />
	<div class="container">
		<h2 style="text-align: center;">게시글 작성</h2>
		<br>
		<br>
		<br>
			<form method="post" enctype="multipart/form-data">
				<input type="file" class="real-upload" accept="image/*" required
					multiple style="display: none;" name="uploadFile" id="file">
				<div class="upload">
					<i class="fa-solid fa-images fa-lg"></i> 이미지 선택하기
				</div>
				<ul class="image-preview"></ul>

				<input type="hidden" name="feed_user_id" value="${user_id}" /><br>
				<textarea name="feed_content"></textarea>
				<br>
				<br>
				<c:if test="${message == false }">
					<script>
		alert("실패")
	</script>
				</c:if>
				<input type="submit" value="글 작성" style="float: right;"
					onclick="goWrite(this.form)" />
			</form>
	</div>

	<script>		
		const uploadFiles = [];
		function getImageFiles(e) {
			const imagePreview = document.querySelector('.image-preview');
			const docFrag = new DocumentFragment();
			const files = e.currentTarget.files;

			[...files].forEach(function(file) {
				if(!file.type.match("image/.*")) {
					alert('이미지 파일만 업로드가 가능합니다.');
					return;
				}
				
				if(uploadFiles.length > 9) {
					alert('이미지는 최대 10개까지 업로드가 가능합니다.');
					return;
				}

				if([...files].length < 11) {
					uploadFiles.push(file);
					const reader = new FileReader();
					reader.onload = function(e) {
						const preview = createElement(e, file);
						imagePreview.appendChild(preview);
					};
					reader.readAsDataURL(file);
				}
			})

			if([...files].length > 10) {
				alert('이미지는 최대 10개까지 업로드가 가능합니다.');
				return;
			}
		}

		function createElement(e, file) {
			const li = document.createElement('li');
			const img = document.createElement('img');
			img.setAttribute('src', e.target.result);
			img.setAttribute('name', 'uploadFile');
			img.setAttribute('data-file', file.name);
			li.setAttribute('onclick', 'deleteImg(this)');
			li.appendChild(img);

			return li;
		}

		const realUpload = document.querySelector('.real-upload');
		const upload = document.querySelector('.upload');

		upload.addEventListener('click', () => {
			realUpload.click();
		})
		realUpload.addEventListener('change', getImageFiles);
				
		function deleteImg(_this) {
			index = $(_this).index();
			console.log(index);
			uploadFiles.splice(index, 1);
			$(_this).remove();
			
			const dataTransfer = new DataTransfer();
			let files = $('#file')[0].files;
			
			let fileArray = Array.from(files);
			fileArray.splice(index, 1);
			
			fileArray.forEach(file => {
				dataTransfer.items.add(file);
			});
			
			$('#file')[0].files = dataTransfer.files;
			
			console.log(files)
		}
		
		function goWrite(frm) {
			var feed_content = frm.feed_content.value;
			if (feed_content.trim() == '') {
				alert("내용을 입력해주세요");
				return;
			}
			let files = $('#file')[0].files;
			
			let fileArray = Array.from(files);
			if(fileArray.length < 1) {
				alert('이미지를 추가해주세요.')
				return
			}
			frm.submit();
		}
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
</body>
</html>