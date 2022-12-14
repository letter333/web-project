# Spring SNS Project 
스프링 + JSP SNS프로젝트
<a href="3.39.125.101:8080">사이트 바로가기</a>

## 🖥 프로젝트 소개
SNS형식의 게시판 입니다. 
## ⏲ 개발기간
* 2022.10.28 ~ 2022.11.11 (이후 생각나는 기능 천천히 추가하는 중)
### ⚙ 개발환경
- `Java8`
- `JDK 1.8.0`
- **IDE** : Eclipse
- **Framework** : Spring 5.2.5
- **Database** : Mysql 8.0.31
- **ORM** : Mybatis
### 개발하면서 어려웠던 점과 해결한 방법
1. 게시글 등록시 이미지 등록 </br>-> 1개만 가능하게 만드는것을 생각하던 중 여러개 올리는게 더 잘어울리지 않을까? 라는 생각을 하게 됨 </br>-> 단일 파일만 받아오는 것을 리스트를 사용해 여러 파일을 가져오도록  바꿈 </br>-> 뷰에서 업로드한 사진을 미리보기하고 클릭시 업로드 리스트에서 삭제되는걸 만들고 싶었으나 미리보기에선 삭제되지만 실제 리스트에선 제대로 지워지지 않음 </br>-> js의 리더를 사용한 파일 리스트로 수정 성공
2. 좋아요 아이콘 출력이 이상하게 됨
   </br>-> 컨트롤러에서 넘겨주는 모델이 dto여서 로그인한 사용자가 좋아요를 누른 게시글 목록을 제대로 못뽑아옴
   </br>-> 매퍼에서 반환해주는 값을 dto에서 int로 바꿔 해당 사용자가 좋아요를 누른 게시글 리스트만 전달해서 해결
3. 로컬환경에서 Aws배포로 옮긴 후 시간 출력이 세계표준시간으로 출력됨 </br>-> 톰캣 서버시간 문제인 줄 알고 톰캣 서버시간을 바꿨으나 고쳐지지 않음 </br>-> 시간 저장을 db시간 기준으로 되는걸 뒤늦게 알고 db의 타임존 수정
## 📌 주요 기능
#### 회원가입
* 회원가입시 입력한 아이디가 이미 가입된 아이디인지 확인을 하고 가입되지 않았다면 입력한 비밀번호를 암호화 하여 데이터베이스에 저장
<details>
<summary>코드 보기</summary>

```java
	@PostMapping(value="/idChk")
	public @ResponseBody  int idCheck(String user_id) {
		// 회원가입시 아이디 중복 체크
		int result = this.userService.checkJoin(user_id);
		return result;
	}
```

```
<select id="check_join" parameterType="string" resultType="int">
	<![CDATA[
		select count(*) from user where user_id = #{user_id}
	]]>
</select>
```
```JAVA
 	public String createUser(UserDTO dto) {
		PasswordEncoder p = new BCryptPasswordEncoder();
		dto.setUser_pw(p.encode(dto.getUser_pw()));		
		
		int affectRowCount = this.userDao.userInsert(dto);
		if(affectRowCount == 1) {
			return dto.getUser_id();
		}
		return null;
	}
```
```javascript
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
    							$('#user_id').attr('readonly', true);
    							$('#idChk').attr('disabled', true);
    							$('#idChk').css('background-color', '#e3e3e3');
    							flag_id = true
    							if(flag_id == true && flag_pw == true) {
    			    				$('.submit').removeAttr('style');
    			    				$('.submit').removeAttr('disabled');    					
    		    				}
    	    					alert('사용 가능한 아이디 입니다.');
    	    				}
    	    			},
    	    			error : function() {
    	    				alert('서버 요청에 실패했습니다.');
    	    			}
    	    		})
        		}
        	})
```
</details>

#### 로그인
* 데이터베이스에 저장돼 있는 암호화된 비밀번호와 로그인 시 입력받은 비밀번호를 비교하여 맡다면 로그인에 성공

<details>
<summary>코드 보기</summary>

```java
	@PostMapping(value="/login")
	public String loginPost(@ModelAttribute LoginDTO dto, HttpServletRequest request, RedirectAttributes rttr) {
		// 비밀번호 암호화를 위해 인코더 객체 생성
		PasswordEncoder p = new BCryptPasswordEncoder();
		
		// 로그인 성공 후 세션에 아이디를 저장해두기 위해 만듬
		HttpSession session = request.getSession();
		
		// DB에 저장돼있는 해당 유저의 암호화된 비밀번호
		String userDBPassword = this.userService.login(dto);
		// 입력한 비밀번호와 암호화된 비밀번호를 비교해서 비밀번호가 맞으면
		if(p.matches(dto.getUser_pw(), userDBPassword)) {
			// 세션에 유저의 아이디를 저장하고 메인화면으로
			session.setAttribute("user_id", dto.getUser_id());
			return "redirect:/";
		} else {
			// 실패하면 메시지에 false를 저장하고 다시 로그인 화면으로
			// addFlashAttribute = 새로고침하면 사라지는 정보
			rttr.addFlashAttribute("message", false);
			session.setAttribute("user_id", null);
			
			return "redirect:login";
		}
		
	}
```
</details>

#### 게시글 작성
* 게시글 내용과 사진을 최대 10장까지 업로드 가능
<details>
<summary>코드 보기</summary>

```java
@PostMapping(value="/new_feed")
	public String newFeedPost(FeedDTO dto, HttpServletRequest req, MultipartHttpServletRequest mhsq, RedirectAttributes rttr) throws IllegalStateException, IOException {
		//HttpSession session = req.getSession();
		
		// 제일 마지막 게시글의 게시글 번호를 가져옴
		Integer feed_id = feedService.getFeedMax();
		
		// 게시글이 없을 경우 게시글 번호를 0으로
		if(feed_id == null) {
			feed_id = 0;
		}
		
		int affectRowCount = feedService.newFeed(dto);
		// 게시글 내용 저장에 성공하면
		if(affectRowCount == 1) {
			// 사진 저장 경로
			String realFolder = req.getSession().getServletContext().getRealPath("/") + "resources/uploadImg/";
			File dir = new File(realFolder);
			// 폴더가 없으면 생성
			if(!dir.isDirectory()) {
				dir.mkdirs();
			}
			
			// 뷰에서 넘겨받은 사진 파일들
			List<MultipartFile> mf = mhsq.getFiles("uploadFile");
			if(mf.size() == 1 && mf.get(0).getOriginalFilename().equals("")) {
			} else {
				for(int i = 0; i < mf.size(); i++) {
					// 업로드 이미지의 이름 중복을 방지하기 위해 랜덤한 문자열을 생성해서 추가
					String genId = UUID.randomUUID().toString();
					String originalFileName = mf.get(i).getOriginalFilename();
					
					String saveFileName = genId + "." + FilenameUtils.getExtension(originalFileName);
					
					String savePath = realFolder + saveFileName;
					long fileSize = mf.get(i).getSize();
					
					mf.get(i).transferTo(new File(savePath));
					
					// 서버에 저장된 이미지의 이름을 디비에 저장
					feedService.fileUpload(originalFileName, saveFileName, fileSize, feed_id + 1);
				}
			}
			return "redirect:/";
		} else {
			rttr.addFlashAttribute("message", false);
			return "redirect:new_feed";
		}
		
	}
	
```
</details>

#### 게시글 수정
* 작성했던 게시글의 내용만 수정 (이미지 수정 추가 예정)

<details>
<summary>코드 보기</summary>

```java
@GetMapping(value="modify_feed")
	public ModelAndView modifyFeedGet(@RequestParam String feed_id) {
		// 수정하려는 게시글의 게시글 번호를 통해 DB에서 정보를 가져옴
		FeedDTO dto = feedService.getFeedById(feed_id);
		ModelAndView mav = new ModelAndView();
		
		// 가져온 정보를 모델로 뷰에 넘겨서 화면에 출력
		mav.addObject("data", dto);
		mav.setViewName("/board/modifyFeed");
		
		return mav;
	}
	
	@PostMapping(value="/modify_feed")
	public String modifyFeedPost(@RequestParam String feed_id) {
		// 수정하려는 게시글의 게시글 번호를 파라미터로 전달
		return "redirect:/modify_feed?feed_id=" + feed_id;
	}
	
	@PostMapping(value="modify_feed_execute")
	public String modifyFeedExecute(@RequestParam Map<String, Object> map) {
		// 게시글 수정 진행
		feedService.modifyFeed(map);
		
		return "redirect:/";
	}
	
```
</details>

#### 게시글 삭제
* 작성한 게시글 삭제

<details>
<summary>코드 보기</summary>

```java
	@PostMapping(value="/delete_feed")
	public String deleteFeed(@RequestParam String feed_id, HttpServletRequest req) {
		// 게시글과 서버에 올라와 있는 해당 게시글에 대한 사진을 함께 지우기 위해 폴더 경로를 변수로 설정
		String realFolder = req.getSession().getServletContext().getRealPath("/") + "/resources/uploadImg/";
		
		// 게시글의 사진에 해당하는 정보를 DB에서 가져옴 
		List<UploadFileDTO> feedUploadFileList = feedService.getFeedUploadFile(feed_id);
		
		// 서버에 파일이름이 존재한다면 삭제
		for (UploadFileDTO uploadFileDTO : feedUploadFileList) {
			File file = new File(realFolder + uploadFileDTO.getFile_name());
			if(file.exists()) {
				file.delete();
			}
		}
		
		// 게시글 내용 삭제
		feedService.deleteFeed(feed_id);
		return "redirect:/";
	} 
```
</details>

#### 댓글 작성
* 게시글에 대한 댓글 작성

<details>
<summary>코드 보기</summary>

```java
	@PostMapping(value="/new_comment")
	public String newComment(CommentDTO comment) {
		// 댓글 작성
		feedService.newComment(comment);
		
		return "redirect:/";
	}
```
```
<insert id="new_feed" parameterType="com.project.sns.dto.FeedDTO" useGeneratedKeys="true" keyProperty="feed_id">
	<![CDATA[{ call new_feed(
		#{feed_content},
		#{feed_user_id}
		)}
	]]>
</insert>
```
</details>

#### 댓글 삭제
* 댓글을 작성한 유저만 삭제 가능

<details>
<summary>코드 보기</summary>

```java
	@PostMapping(value="/delete_comment")
	public String deleteComment(@RequestParam Map<String, Object> map) {
		// 댓글 번호를 가져와 댓글 삭제
		feedService.deleteComment(map);
		
		return "redirect:/";
	}
```
```
<c:if test="${user_id eq comment.comment_user_id }">
    <form action="/delete_comment" method="post" id="comment_form" class="col-1 row text-center">
        <input type="hidden" name="comment_feed_id" value="${feed.feed_id }" />
        <input type="hidden" name="comment_id" value="${comment.comment_id }" />
        // 댓글 삭제 버튼
        <button class="delete-btn fa-solid fa-eraser fa-sm" type="submit"></button>
    </form>
</c:if>
```
</details>

#### 좋아요
* 비어있는 하트버튼 클릭시 좋아요, 가득찬 하트버튼 클릭시 좋아요 취소

<details>
<summary>코드 보기</summary>

```java
@ResponseBody
	@GetMapping(value="/like")
	public FeedDTO like(@RequestParam String num, HttpServletRequest req) {
		// 좋아요 클릭
		HttpSession session = req.getSession();
		LikeDTO likeDTO = new LikeDTO();
		// LikeDTO에 좋아요를 누른 유저의 아이디와 게시글 번호를 저장
		likeDTO.setLike_feed_id(Integer.parseInt(num));
		likeDTO.setLike_user_id(session.getAttribute("user_id").toString());
		
		// 게시글의 좋아요 수를 1 증가시키고 좋아요 수가 증가한 게시긇의 정보를 AJAX요청의 결과로 반환
		FeedDTO result = feedService.likeUp(likeDTO);
		
		return result;
	}
	
	@ResponseBody
	@GetMapping(value="/like_cancel")
	public FeedDTO likeCancel(@RequestParam String num, HttpServletRequest req) {
		// 좋아요 취소
		HttpSession session = req.getSession();
		LikeDTO likeDTO = new LikeDTO();
		//LikeDTODP 좋아요 취소를 한 유저의 아이디와 게시글 번호를 저장
		likeDTO.setLike_feed_id(Integer.parseInt(num));
		likeDTO.setLike_user_id(session.getAttribute("user_id").toString());
		
		// 게시글의 좋아요 수를 1 감소시키고 좋아요 수가 감소한 게시글의 정보를 AJAX요청의 결과로 반환
		FeedDTO result = feedService.likeCancel(likeDTO);
		
		return result;
	}
```
```
$(".like").click(function() {
			let num = $(this).attr('idx');
			console.log('like click');
			
			
			if(num == null) {
				return
			} else if($(this).attr('class') == 'like fa-regular fa-heart') {
				$.ajax({
					url : 'like',
					type : 'get',
					data : {
						num : num
					},
					success : function(result) {
						let like = result.feed_like_count;
						$('#like'+num).text(like);
					},
					error : function() {
						alert('서버 에러')
					}
				})
				$(this).attr('class', 'like fa-solid fa-heart')
			} else if($(this).attr('class') == 'like fa-solid fa-heart') {
				$.ajax({
					url : 'like_cancel',
					type : 'get',
					data : {
						num : num,
					},
					success : function(result) {
						let like = result.feed_like_count;
						$('#like'+num).text(like);
					},
					error : function() {
						alert('서버 에러');
					}
				})
				$(this).attr('class', 'like fa-regular fa-heart')
			}
		})
```
</details>

#### 프로필 수정
* 회원가입시 입력했던 정보를 수정하거나 프로필 사진을 수정 (프로필 사진 삭제 추가 예정)

<details>
<summary>코드 보기</summary>

```java
@PostMapping(value="/user_modify")
	public String userModifyPost(@ModelAttribute UserDTO dto, MultipartHttpServletRequest file ,HttpServletRequest req, RedirectAttributes rttr) {
		// 입력한 비밀번호를 DB의 암호화된 비밀번호와 비교하기 위해 객체 생성
		PasswordEncoder p = new BCryptPasswordEncoder();
		String myDBPw = this.userService.checkPw(dto);
		
		// 입력한 비밀번호가 암호화된 비밀번호와 일치하는지 확인
		boolean checkPw = p.matches(dto.getUser_pw(), myDBPw);
		
		if(checkPw == true) {
			// 유저의 수정된 정보를 저장
			this.userService.modifyUser(dto);
			
			// 프로필 사진을 저장하기 위한 경로를 변수에 저장
			String realFolder = req.getSession().getServletContext().getRealPath("/") + "/resources/uploadImg/profileImg/";
			File dir = new File(realFolder);
			// 해당 폴더가 없으면 폴더를 생성
			if(!dir.isDirectory()) {
				dir.mkdirs();
			}
			
			// 업로드한 프로필 사진
			MultipartFile mf = file.getFile("uploadProfile");
			
			// 사진 이름의 중복을 방지하기 위해 랜덤한 문자열 생성후 붙여서 사진이름을 저장
			String genId = UUID.randomUUID().toString();
			String originalFileName = mf.getOriginalFilename();
			// 프로필 사진을 변경했다면
			if(!originalFileName.isEmpty()) {
				String saveFileName = genId + "." + FilenameUtils.getExtension(originalFileName);
				String savePath = realFolder + saveFileName;
				
				try {
					// 서버에 프로필 사진을 저장
					mf.transferTo(new File(savePath));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// 서버에 저장된 프로필 사진 이름을 DB에 저장
				this.userService.updateProfile(originalFileName, saveFileName, dto.getUser_id());
			}
			
			
			return "redirect:/user_detail?user_id=" + dto.getUser_id();
		} else {
			rttr.addFlashAttribute("chkPwMsg", false);
			
			return "redirect:/user_modify?user_id=" + dto.getUser_id();
		}
		
		
	}
```

</details>
