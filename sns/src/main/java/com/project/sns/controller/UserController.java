package com.project.sns.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public String createUserPost(@ModelAttribute UserDTO dto) {
//		ModelAndView mav = new ModelAndView();
//		String userId = this.userService.createUser(dto);
//		if(userId == null) {
//			mav.setViewName("redirect:/createUser");
//		} else {
//			mav.setViewName("/user/test");
//		}
//		return mav;
		String userId = this.userService.createUser(dto);
		if(userId == null) {
			return "/user/join";
		} else {
			return "redirect:login";
		}
	}
	
	@GetMapping(value="/login")
	public String login() {
		return "/user/login";
	}
	
	@PostMapping(value="/login")
	public String loginPost(LoginDTO dto, HttpServletRequest request) {
		PasswordEncoder p = new BCryptPasswordEncoder();
		
		HttpSession session = request.getSession();
		
		String login = userService.login(dto);
		if(p.matches(dto.getUser_pw(), login)) {
			session.setAttribute("user_id", dto.getUser_id());
			System.out.println("로그인 성공");
			return "redirect:test";
		} else {
			session.setAttribute("user_id", null);
		}
		
		return "redirect:login";
	}
	
	@GetMapping(value="/test")
	public String test() {
		return "/user/test";
	}
}
