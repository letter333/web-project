package com.project.sns.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sns.dao.FeedDAO;
import com.project.sns.dto.FeedDTO;

@Service
public class FeedServiceImpl implements FeedService {
	@Autowired
	FeedDAO feedDao;
	
	@Override
	public int newFeed(FeedDTO dto) {
		return feedDao.newFeed(dto);
	}
	
	@Override
	public String getFeedMax() {
		return feedDao.getFeedMax(); 
	}

	@Override
	public void fileUpload(String originalFileName, String saveFileName, long fileSize, String feed_id) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("original_name", originalFileName);
		hm.put("file_name", saveFileName);
		hm.put("file_size", fileSize);
		hm.put("feed_id", feed_id);
		
		feedDao.fileUpload(hm);
	}
}

