package com.project.sns.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.sns.dto.CommentDTO;
import com.project.sns.dto.FeedDTO;
import com.project.sns.dto.UploadFileDTO;
import com.project.sns.service.FeedService;

@Controller
public class FeedController {
	@Autowired
	FeedService feedService;
	
	@GetMapping(value="/")
	public ModelAndView mainGet(HttpServletRequest req) {
		HttpSession session = req.getSession();
		ModelAndView mav = new ModelAndView();
		
		List<FeedDTO> feedList = feedService.getFeed();
		List<UploadFileDTO> uploadFileList = feedService.getUploadFile();
	
		List<CommentDTO> commentList = feedService.getComment();
				
		mav.addObject("feedList", feedList);
		mav.addObject("uploadFileList", uploadFileList);
		mav.addObject("commentList", commentList);
		mav.setViewName("/board/main");
	
		return mav;
	}
	
	@GetMapping(value="/new_feed")
	public String newFeed() {
		return "/board/newFeed";
	}
	
	@Transactional
	@PostMapping(value="/new_feed")
	public String newFeedPost(FeedDTO dto, HttpServletRequest req, MultipartHttpServletRequest mhsq, RedirectAttributes rttr) throws IllegalStateException, IOException {
		HttpSession session = req.getSession();
		
		Integer feed_id = feedService.getFeedMax();
		
		if(feed_id == null) {
			feed_id = 0;
		}
		
		int affectRowCount = feedService.newFeed(dto);
		
		String realFolder = req.getSession().getServletContext().getRealPath("/") + "/resources/uploadImg/"; //서버 경로 + 저장 경로
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
				System.out.println(savePath);
				
				feedService.fileUpload(originalFileName, saveFileName, savePath, fileSize, feed_id + 1);
			}
		}
		
		if(affectRowCount == 1) {
			return "redirect:/";
		} else {
			rttr.addFlashAttribute("message", false);
			return "redirect:new_feed";
		}
		
	}
	
	@PostMapping(value="/new_comment")
	public String newComment(CommentDTO comment) {
		feedService.newComment(comment);
		
		return "redirect:/";
	}
	
	@PostMapping(value="/delete_comment")
	public String deleteComment(@RequestParam Map<String, Object> map) {
		
		feedService.deleteComment(map);
		
		return "redirect:/";
	}
	
	@PostMapping(value="/delete_feed")
	public String deleteFeed(@RequestParam String feed_id, HttpServletRequest req) {
		String realFolder = req.getSession().getServletContext().getRealPath("/") + "/resources/uploadImg/";
		
		List<UploadFileDTO> feedUploadFileList = feedService.getFeedUploadFile(feed_id);
		
		for (UploadFileDTO uploadFileDTO : feedUploadFileList) {
			File file = new File(realFolder + uploadFileDTO.getFile_name());
			if(file.exists()) {
				file.delete();
			}
		}
		
		feedService.deleteFeed(feed_id);
		return "redirect:/";
	}
}
