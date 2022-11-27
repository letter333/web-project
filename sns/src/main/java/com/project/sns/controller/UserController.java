package com.project.sns.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.sns.dto.LoginDTO;
import com.project.sns.dto.UserDTO;
import com.project.sns.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	// 회원가입 페이지 보여줌
	@GetMapping(value="/join")
	public String createUser() {
		return "/user/join";
	}
	
	@Transactional
	@PostMapping(value="/join")
	public String createUserPost(UserDTO dto) {
		// 유저의 정보를 DB에 추가
		this.userService.createUser(dto);
		// 유저의 프로필 사진을 기본 이미지로 설정
		this.userService.initProfile(dto);
		return "redirect:login";
	}
	
	
	@PostMapping(value="/idChk")
	public @ResponseBody  int idCheck(String user_id) {
		// 회원가입시 아이디 중복 체크
		int result = this.userService.checkJoin(user_id);
		return result;
	}
	
	// 로그인 페이지 보여줌
	@GetMapping(value="/login")
	public String login() {
		return "/user/login";
	}
	
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
	
	@GetMapping(value="/logout")
	public String logoutGet(HttpServletRequest req) {
		// 로그아웃할 경우 세션을 지워줌
		HttpSession session = req.getSession();
		session.invalidate();
		
		return "redirect:/";
	}

	// 로그인한 유저의 정보 
	@GetMapping(value="/user_detail")
	public ModelAndView userDetailGet(@RequestParam String user_id) {
		ModelAndView mav = new ModelAndView();
		// 유저의 정보를 DB에서 가져옴
		UserDTO dto = this.userService.getUser(user_id);
		// 유저의 프로필 사진
		String profileImg = this.userService.getProfileImg(dto);
		
		// 정보를 모델로 뷰에 넘겨서 출력해줌
		mav.addObject("data", dto);
		mav.addObject("profileImg", profileImg);
		mav.setViewName("/user/detailUser");
		
		return mav;
	}
	
	// 유저 정보 수정 
	// 파라미터로 다른 유저의 정보 수정에 접근하지 못하게 파라미터 없이 세션에서 아이디를 가져옴
	@GetMapping(value="/user_modify")
	public ModelAndView userModifyGet(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();

		String user_id = req.getSession().getAttribute("user_id").toString();
		// 로그인한 유저의 정보와 프로필 사진을 DB에서 가져옴
		UserDTO dto = this.userService.getUser(user_id);
		String profileImg = this.userService.getProfileImg(dto);
		
		// 가져온 정보를 모델로 뷰에 전달
		mav.addObject("data", dto);
		mav.addObject("profileImg", profileImg);
		mav.setViewName("/user/modifyUser");		
		
		return mav;
	}
	
	// 유저 정보 수정 실행
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
	
	// 회원탈퇴 
	@PostMapping(value="/withdrawal")
	public String userWithdrawal(String user_id, HttpServletRequest req) {
		// 유저의 정보를 DB에서 삭제하는데 성공했다면
		if(this.userService.deleteUser(user_id) == 1) {
			// 세션을 지우고 메인화면으로 이동
			req.getSession().invalidate();
			return "redirect:/";
		} else {
			// 실패했다면 유저 정보로 이동
			return "redirect:/detail?user_id=" + user_id;
		}
		
	}
	
}