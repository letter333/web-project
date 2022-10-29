package com.project.sns.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.sns.dto.UserDTO;
import com.project.sns.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping(value="/createUser")
	public ModelAndView createUser() {
		return new ModelAndView("/user/createUser");
	}
	
	@PostMapping(value="/createUser")
	public ModelAndView createUserPost(@ModelAttribute UserDTO dto) {
		ModelAndView mav = new ModelAndView();
		String userId = this.userService.createUser(dto);
		if(userId == null) {
			mav.setViewName("redirect:/createUser");
		} else {
			mav.setViewName("redirect:/userInfo?user_id=" + dto.getUser_id());
		}
		return mav;
	}
}
