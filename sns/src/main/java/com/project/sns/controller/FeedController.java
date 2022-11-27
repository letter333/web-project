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
	
	// 메인 화면
	@GetMapping(value="/")
	public ModelAndView mainGet(HttpServletRequest req) {
		HttpSession session = req.getSession();
		ModelAndView mav = new ModelAndView();
		
		// 게시글, 게시글 사진, 댓글, 좋아요 수와 로그인한 유저의 좋아요 여부를 모델로 넘겨줌.
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
		
		// 제일 마지막 게시글의 게시글 번호를 가져옴
		Integer feed_id = feedService.getFeedMax();
		
		// 게시글이 없을 경우 게시글 번호를 0으로
		if(feed_id == null) {
			feed_id = 0;
		}
		
		int affectRowCount = feedService.newFeed(dto);
		// 게시글 내용 저장에 성공하면
		if(affectRowCount == 1) {
			// 사진 저장 경로
			String realFolder = req.getSession().getServletContext().getRealPath("/") + "resources/uploadImg/";
			File dir = new File(realFolder);
			// 폴더가 없으면 생성
			if(!dir.isDirectory()) {
				dir.mkdirs();
			}
			
			// 뷰에서 넘겨받은 사진 파일들
			List<MultipartFile> mf = mhsq.getFiles("uploadFile");
			if(mf.size() == 1 && mf.get(0).getOriginalFilename().equals("")) {
			} else {
				for(int i = 0; i < mf.size(); i++) {
					// 업로드 이미지의 이름 중복을 방지하기 위해 랜덤한 문자열을 생성해서 추가
					String genId = UUID.randomUUID().toString();
					String originalFileName = mf.get(i).getOriginalFilename();
					
					String saveFileName = genId + "." + FilenameUtils.getExtension(originalFileName);
					
					String savePath = realFolder + saveFileName;
					long fileSize = mf.get(i).getSize();
					
					mf.get(i).transferTo(new File(savePath));
					
					// 서버에 저장된 이미지의 이름을 디비에 저장
					feedService.fileUpload(originalFileName, saveFileName, fileSize, feed_id + 1);
				}
			}
			return "redirect:/";
		} else {
			rttr.addFlashAttribute("message", false);
			return "redirect:new_feed";
		}
		
	}
	
	
	@Transactional
	@PostMapping(value="/new_comment")
	public String newComment(CommentDTO comment) {
		// 댓글 작성
		feedService.newComment(comment);
		
		return "redirect:/";
	}
	
	@PostMapping(value="/delete_comment")
	public String deleteComment(@RequestParam Map<String, Object> map) {
		// 댓글 번호를 가져와 댓글 삭제
		feedService.deleteComment(map);
		
		return "redirect:/";
	}
	
	@PostMapping(value="/delete_feed")
	public String deleteFeed(@RequestParam String feed_id, HttpServletRequest req) {
		// 게시글과 서버에 올라와 있는 해당 게시글에 대한 사진을 함께 지우기 위해 폴더 경로를 변수로 설정
		String realFolder = req.getSession().getServletContext().getRealPath("/") + "/resources/uploadImg/";
		
		// 게시글의 사진에 해당하는 정보를 DB에서 가져옴 
		List<UploadFileDTO> feedUploadFileList = feedService.getFeedUploadFile(feed_id);
		
		// 서버에 파일이름이 존재한다면 삭제
		for (UploadFileDTO uploadFileDTO : feedUploadFileList) {
			File file = new File(realFolder + uploadFileDTO.getFile_name());
			if(file.exists()) {
				file.delete();
			}
		}
		
		// 게시글 내용 삭제
		feedService.deleteFeed(feed_id);
		return "redirect:/";
	}
	
	@GetMapping(value="modify_feed")
	public ModelAndView modifyFeedGet(@RequestParam String feed_id) {
		// 수정하려는 게시글의 게시글 번호를 통해 DB에서 정보를 가져옴
		FeedDTO dto = feedService.getFeedById(feed_id);
		ModelAndView mav = new ModelAndView();
		
		// 가져온 정보를 모델로 뷰에 넘겨서 화면에 출력
		mav.addObject("data", dto);
		mav.setViewName("/board/modifyFeed");
		
		return mav;
	}
	
	@PostMapping(value="/modify_feed")
	public String modifyFeedPost(@RequestParam String feed_id) {
		// 수정하려는 게시글의 게시글 번호를 파라미터로 전달
		return "redirect:/modify_feed?feed_id=" + feed_id;
	}
	
	@PostMapping(value="modify_feed_execute")
	public String modifyFeedExecute(@RequestParam Map<String, Object> map) {
		// 게시글 수정 진행
		feedService.modifyFeed(map);
		
		return "redirect:/";
	}
	
	@ResponseBody
	@GetMapping(value="/like")
	public FeedDTO like(@RequestParam String num, HttpServletRequest req) {
		// 좋아요 클릭
		HttpSession session = req.getSession();
		LikeDTO likeDTO = new LikeDTO();
		// LikeDTO에 좋아요를 누른 유저의 아이디와 게시글 번호를 저장
		likeDTO.setLike_feed_id(Integer.parseInt(num));
		likeDTO.setLike_user_id(session.getAttribute("user_id").toString());
		
		// 게시글의 좋아요 수를 1 증가시키고 좋아요 수가 증가한 게시긇의 정보를 AJAX요청의 결과로 반환
		FeedDTO result = feedService.likeUp(likeDTO);
		
		return result;
	}
	
	@ResponseBody
	@GetMapping(value="/like_cancel")
	public FeedDTO likeCancel(@RequestParam String num, HttpServletRequest req) {
		// 좋아요 취소
		HttpSession session = req.getSession();
		LikeDTO likeDTO = new LikeDTO();
		//LikeDTODP 좋아요 취소를 한 유저의 아이디와 게시글 번호를 저장
		likeDTO.setLike_feed_id(Integer.parseInt(num));
		likeDTO.setLike_user_id(session.getAttribute("user_id").toString());
		
		// 게시글의 좋아요 수를 1 감소시키고 좋아요 수가 감소한 게시글의 정보를 AJAX요청의 결과로 반환
		FeedDTO result = feedService.likeCancel(likeDTO);
		
		return result;
	}
}
