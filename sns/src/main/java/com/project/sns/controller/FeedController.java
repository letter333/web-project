package com.project.sns.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
	
	@Transactional
	@PostMapping(value="/new_feed")
	public String newFeedPost(@ModelAttribute FeedDTO dto, HttpServletRequest req, MultipartHttpServletRequest mhsq) throws IllegalStateException, IOException {
		HttpSession session = req.getSession();
		
		String feed_id = feedService.getFeedMax(); 
		int affectRowCount = feedService.newFeed(dto);
		
		String realFolder = "C:\\Users\\KB\\Desktop\\OSR\\web-project\\sns\\src\\main\\resources\\upload";
		File dir = new File(realFolder);
		if(!dir.isDirectory()) {
			dir.mkdirs();
		}
		
		List<MultipartFile> mf = mhsq.getFiles("uploadFile");
		if(mf.size() == 1 && mf.get(0).getOriginalFilename().equals("")) {
			
		} else {
			for(int i = 0; i < mf.size(); i++) {
				String genId = UUID.randomUUID().toString();
				String originalFileName = mf.get(i).getOriginalFilename();
				
				String saveFileName = genId + "." + FilenameUtils.getExtension(originalFileName);
				
				String savePath = realFolder + saveFileName;
				
				long fileSize = mf.get(i).getSize();
				
				mf.get(i).transferTo(new File(savePath));
				
				feedService.fileUpload(originalFileName, saveFileName, fileSize, feed_id);
			}
		}
		
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