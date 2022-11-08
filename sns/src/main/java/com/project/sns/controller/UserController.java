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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		return "redirect:login";
	}
	
	
	@PostMapping(value="/idChk")
	public @ResponseBody  int idCheck(String user_id) {
		int result = userService.checkJoin(user_id);
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
		
		String login = userService.login(dto);
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
		
		UserDTO dto = userService.getUser(user_id);
		
		mav.addObject("data", dto);
		mav.setViewName("/user/detailUser");
		
		return mav;
	}
	
	@GetMapping(value="/user_modify")
	public ModelAndView userModifyGet(@RequestParam String user_id) {
		ModelAndView mav = new ModelAndView();
		
		UserDTO dto = userService.getUser(user_id);
		
		mav.addObject("data", dto);
		mav.setViewName("/user/modifyUser");		
		
		return mav;
	}
}
