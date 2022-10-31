package com.project.sns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.sns.dto.FeedDTO;
import com.project.sns.service.FeedService;

@Controller
public class FeedController {
	@Autowired
	FeedService feedService;
	
	@GetMapping(value="/new_feed")
	public String newFeed() {
		return "/board/newFeed";
	}
	
	
	@PostMapping(value="/new_feed")
	public String newFeedPost(@ModelAttribute FeedDTO dto) {
		String feed_id = feedService.getFeedCount(); 
		int affectRowCount = feedService.newFeed(dto);
		
		if(affectRowCount == 1) {
			return "redirect:feed?feed_id=" + feed_id;			
		} else {
			return "redirect:/";
		}
		
	}
	
	@GetMapping(value="/test")
	public String test() {
		return "/user/test";
	}
}
