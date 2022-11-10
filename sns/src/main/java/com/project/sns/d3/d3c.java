package com.project.sns.d3;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class d3c {
	@Autowired
	d3s d3s;

	@GetMapping("/d3")
	public ModelAndView list(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		System.out.println("segu1");
		List<Map<String, Object>> segu = this.d3s.segu_select(map);
		mav.addObject("segu", segu);
		System.out.println("segu4");
		
		System.out.println("susunggu1");
		List<Map<String, Object>> susunggu = this.d3s.susunggu_select(map);
		mav.addObject("susunggu", susunggu);
		System.out.println("susunggu4");
		
		mav.setViewName("/d3/d3");
		return mav;
	}
}