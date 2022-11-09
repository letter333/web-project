package com.project.sns.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sns.dao.FeedDAO;
import com.project.sns.dto.CommentDTO;
import com.project.sns.dto.FeedDTO;
import com.project.sns.dto.ProfileDTO;
import com.project.sns.dto.UploadFileDTO;

@Service
public class FeedServiceImpl implements FeedService {
	@Autowired
	FeedDAO feedDao;
	
	@Override
	public int newFeed(FeedDTO dto) {
		return feedDao.newFeed(dto);
	}
	
	@Override
	public Integer getFeedMax() {
		return feedDao.getFeedMax(); 
	}

	@Override
	public void fileUpload(String originalFileName, String saveFileName, String savePath, long fileSize, int feed_id) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("original_name", originalFileName);
		hm.put("file_name", saveFileName);
		hm.put("file_size", fileSize);
		hm.put("feed_id", feed_id);
		
		feedDao.fileUpload(hm);
	}
	
	@Override
	public List<FeedDTO> getFeed() {
		return feedDao.getFeed();
	}

	@Override
	public List<UploadFileDTO> getUploadFile() {
		return feedDao.getUploadFile();
	}
	
	@Override
	public int newComment(CommentDTO dto) {
		return feedDao.newComment(dto);
	}
	
	@Override
	public List<CommentDTO> getComment() {
		return feedDao.getComment();
	}
	
	@Override
	public int deleteComment(Map<String, Object> map) {
		return feedDao.deleteComment(map);
	}
	
	@Override
	public int deleteFeed(String feed_id) {
		return feedDao.deleteFeed(feed_id);
	}
	
	@Override
	public List<UploadFileDTO> getFeedUploadFile(String feed_id) {
		return feedDao.getFeedUploadFile(feed_id);
	}
	
	@Override
	public int modifyFeed(Map<String, Object> map) {
		return feedDao.modifyFeed(map);
	}
	
	@Override
	public FeedDTO getFeedById(String feed_id) {
		return feedDao.getFeedById(feed_id);
	}
	
	@Override
	public List<ProfileDTO> getProfile() {
		return feedDao.getProfile();
	}
}

