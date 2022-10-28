package com.project.sns.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.sns.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/createUser", method=RequestMethod.GET)
	public ModelAndView createUser() {
		return new ModelAndView("/user/createUser");
	}
	
	@RequestMapping(value="/createUser", method=RequestMethod.POST)
	public ModelAndView createUserPost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		String userId = this.userService.createUser(map);
		if(userId == null) {
			mav.setViewName("redirect:/createUser");
		} else {
			mav.setViewName("redirect:/userInfo?userId=" + userId);
		}
		return mav;
	}
}
