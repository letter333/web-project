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
	
	@GetMapping(value="/join")
	public String createUser() {
		return "/user/join";
	}
	
	@PostMapping(value="/join")
	public String createUserPost(UserDTO dto) {
		this.userService.createUser(dto);
		this.userService.initProfile(dto);
		return "redirect:login";
	}
	
	
	@PostMapping(value="/idChk")
	public @ResponseBody  int idCheck(String user_id) {
		int result = this.userService.checkJoin(user_id);
		return result;
	}
	
	/*
	 * @PostMapping(value="idCheck") public int idCheck(UserDTO dto) { int result =
	 * userService.checkJoin(dto); return result; }
	 */
	
	@GetMapping(value="/login")
	public String login() {
		return "/user/login";
	}
	
	@PostMapping(value="/login")
	public String loginPost(@ModelAttribute LoginDTO dto, HttpServletRequest request, RedirectAttributes rttr) {
		PasswordEncoder p = new BCryptPasswordEncoder();
		
		HttpSession session = request.getSession();
		
		String login = this.userService.login(dto);
		if(p.matches(dto.getUser_pw(), login)) {
			session.setAttribute("user_id", dto.getUser_id());
			return "redirect:/";
		} else {
			rttr.addFlashAttribute("message", false);
			session.setAttribute("user_id", null);
			
			return "redirect:login";
		}
		
	}
	
	@GetMapping(value="/logout")
	public String logoutGet(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		
		return "redirect:/";
	}

	@GetMapping(value="/user_detail")
	public ModelAndView userDetailGet(@RequestParam String user_id) {
		ModelAndView mav = new ModelAndView();
		
		UserDTO dto = this.userService.getUser(user_id);
		String profileImg = this.userService.getProfileImg(dto);
		
		mav.addObject("data", dto);
		mav.addObject("profileImg", profileImg);
		mav.setViewName("/user/detailUser");
		
		return mav;
	}
	
	@GetMapping(value="/user_modify")
	public ModelAndView userModifyGet(@RequestParam String user_id) {
		ModelAndView mav = new ModelAndView();
		
		UserDTO dto = this.userService.getUser(user_id);
		String profileImg = this.userService.getProfileImg(dto);
		
		mav.addObject("data", dto);
		mav.addObject("profileImg", profileImg);
		mav.setViewName("/user/modifyUser");		
		
		return mav;
	}
	
	@PostMapping(value="/user_modify")
	public String userModifyPost(@ModelAttribute UserDTO dto, MultipartHttpServletRequest file ,HttpServletRequest req, RedirectAttributes rttr) {
		PasswordEncoder p = new BCryptPasswordEncoder();
		String myDBPw = this.userService.checkPw(dto);
		
		boolean checkPw = p.matches(dto.getUser_pw(), myDBPw);
		
		if(checkPw == true) {
			this.userService.modifyUser(dto);
			
			String realFolder = req.getSession().getServletContext().getRealPath("/") + "/resources/uploadImg/profileImg/"; //서버 경로 + 저장 경로
			File dir = new File(realFolder);
			if(!dir.isDirectory()) {
				dir.mkdirs();
			}
			
			MultipartFile mf = file.getFile("uploadProfile");
			
			String genId = UUID.randomUUID().toString();
			System.out.println(mf.getOriginalFilename());
			String originalFileName = mf.getOriginalFilename();
			
			String saveFileName = genId + "." + FilenameUtils.getExtension(originalFileName);
			String savePath = realFolder + saveFileName;
			
			try {
				mf.transferTo(new File(savePath));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.userService.updateProfile(originalFileName, saveFileName, dto.getUser_id());
			
			return "redirect:/user_detail?user_id=" + dto.getUser_id();
		} else {
			rttr.addFlashAttribute("chkPwMsg", false);
			
			return "redirect:/user_modify?user_id=" + dto.getUser_id();
		}
		
		
	}
	
}