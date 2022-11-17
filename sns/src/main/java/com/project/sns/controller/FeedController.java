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
import com.project.sns.dto.LikeDTO;
import com.project.sns.dto.ProfileDTO;
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
		List<ProfileDTO> profileList = feedService.getProfile();
		List<CommentDTO> commentList = feedService.getComment();
		List<Integer> likeList = null;
		if(session.getAttribute("user_id") != null) {
			likeList = feedService.getLikeAll(session.getAttribute("user_id").toString());
		}
		mav.addObject("feedList", feedList);
		mav.addObject("uploadFileList", uploadFileList);
		mav.addObject("profileList", profileList);
		mav.addObject("commentList", commentList);
		if(likeList != null) {
			mav.addObject("likeList", likeList);			
		}
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
		//HttpSession session = req.getSession();
		
		Integer feed_id = feedService.getFeedMax();
		
		if(feed_id == null) {
			feed_id = 0;
		}
		
		int affectRowCount = feedService.newFeed(dto);
		String realFolder = req.getSession().getServletContext().getRealPath("/") + "resources\\uploadImg\\";
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
				
				feedService.fileUpload(originalFileName, saveFileName, fileSize, feed_id + 1);
			}
		}
		
		if(affectRowCount == 1) {
			return "redirect:/";
		} else {
			rttr.addFlashAttribute("message", false);
			return "redirect:new_feed";
		}
		
	}
	
	
	@Transactional
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
	
	@PostMapping(value="/modify_feed")
	public String modifyFeedPost(@RequestParam String feed_id) {
		return "redirect:/modify_feed?feed_id=" + feed_id;
	}
	
	@GetMapping(value="modify_feed")
	public ModelAndView modifyFeedGet(@RequestParam String feed_id) {
		FeedDTO dto = feedService.getFeedById(feed_id);
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("data", dto);
		mav.setViewName("/board/modifyFeed");
		
		return mav;
	}
	
	@PostMapping(value="modify_feed_execute")
	public String modifyFeedExecute(@RequestParam Map<String, Object> map) {
		feedService.modifyFeed(map);
		
		return "redirect:/";
	}
	
	@ResponseBody
	@GetMapping(value="/like")
	public FeedDTO like(@RequestParam String num, HttpServletRequest req) {
		HttpSession session = req.getSession();
		LikeDTO likeDTO = new LikeDTO();
		likeDTO.setLike_feed_id(Integer.parseInt(num));
		likeDTO.setLike_user_id(session.getAttribute("user_id").toString());
		
		FeedDTO result = feedService.likeUp(likeDTO);
		
		return result;
	}
	
	@ResponseBody
	@GetMapping(value="/like_cancel")
	public FeedDTO likeCancel(@RequestParam String num, HttpServletRequest req) {
		HttpSession session = req.getSession();
		LikeDTO likeDTO = new LikeDTO();
		
		likeDTO.setLike_feed_id(Integer.parseInt(num));
		likeDTO.setLike_user_id(session.getAttribute("user_id").toString());
		
		FeedDTO result = feedService.likeCancel(likeDTO);
		
		return result;
	}
}
