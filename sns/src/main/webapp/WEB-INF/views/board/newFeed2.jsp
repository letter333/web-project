<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<script>
	function goWrite(frm) {
		var post_content = frm.post_content.value;
		var post_writer_position = frm.post_writer_position.value;
		if (post_content.trim() == '') {
			alert("내용을 입력해주세요");
			return false;
		}
		frm.submit();
	}
</script>
<style>
.container {
	width: 500px;
}

li {
	list-style: none;
}

img {
	width: 200px;
	height: 200px;
}

.real-upload {
	display: none;
}

.upload {
	width: 200px;
	height: 200px;
	background-color: antiquewhite;
}

.image-preview {
	width: 1300px;
	height: 200px;
	background-color: aquamarine;
	display: flex;
	gap: 20px;
}
</style>
</head>
<body>
	<div class="container">
		<h2 style="text-align: center;">게시글 작성</h2>
		<br>
		<br>
		<br>
		<div class="form-floating">
			<form method="post" enctype="multipart/form-data">
				<input type="file" class="real-upload" accept="image/*" required
					multiple style="display: none;" name="uploadFile">
				<div class="upload"></div>
				<ul class="image-preview"></ul>

				<input type="text" name="feed_user_id" style="width: 50%;"
					placeholder="작성자" readonly value="${user_id}" /><br> <input
					type="hidden" name="feed_title" value="test">
				<textarea type="text" name="feed_content"
					style="width: 100%; height: 300px"></textarea>
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
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>

	<script>		
		function getImageFiles(e) {
			const uploadFiles = [];
			const imagePreview = document.querySelector('.image-preview');
			const docFrag = new DocumentFragment();
			const files = e.currentTarget.files;

			[...files].forEach(file => {
				if(!file.type.match("image/.*")) {
					alert('이미지 파일만 업로드가 가능합니다.');
					return;
				}

				if([...files].length < 10) {
					uploadFiles.push(file);
					const reader = new FileReader();
					reader.onload = (e) => {
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
			li.appendChild(img);

			return li;
		}

		const realUpload = document.querySelector('.real-upload');
		const upload = document.querySelector('.upload');

		upload.addEventListener('click', () => {
			realUpload.click();
		})
		realUpload.addEventListener('change', getImageFiles);
		
	</script>
</body>
</html>