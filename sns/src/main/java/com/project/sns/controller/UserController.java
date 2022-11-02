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
		String userId = this.userService.createUser(dto);
		if(userId == null) {
			return "/user/join";
		} else {
			return "redirect:login";
		}
	}
	
	// 회원가입 페이지 구현 후 아이디 중복체크 완성하기 
	@PostMapping(value="idCheck")
	public int idCheck(UserDTO dto) {
		int result = userService.checkJoin(dto);
		return result;
	}
	
	@GetMapping(value="/login")
	public String login() {
		return "/user/login";
	}
	
	@PostMapping(value="/login")
	public String loginPost(@ModelAttribute LoginDTO dto, HttpServletRequest request, RedirectAttributes rttr) {
		PasswordEncoder p = new BCryptPasswordEncoder();
		
		HttpSession session = request.getSession();
		
		String login = userService.login(dto);
		if(p.matches(dto.getUser_pw(), login)) {
			session.setAttribute("user_id", dto.getUser_id());
			System.out.println("로그인 성공");
			return "redirect:test";
		} else {
			System.out.println("로그인 실패");
			rttr.addFlashAttribute("message", false);
			session.setAttribute("user_id", null);
			
			return "redirect:login";
		}
		
	}

}
