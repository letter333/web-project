package com.project.sns.controller;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
		String feed_id = feedService.getFeedMax(); 
		int affectRowCount = feedService.newFeed(dto);
		
		if(affectRowCount == 1) {
			return "redirect:feed?feed_id=" + (Integer.parseInt(feed_id) + 1);
		} else {
			return "redirect:/";
		}
		
	}
	
	@GetMapping(value="/test")
	public String test() {
		return "/user/test";
	}
}
