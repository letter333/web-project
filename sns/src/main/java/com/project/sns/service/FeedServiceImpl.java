package com.project.sns.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.sns.dao.FeedDAO;
import com.project.sns.dto.FeedDTO;

public class FeedServiceImpl implements FeedService {
	@Autowired
	FeedDAO feedDao;
	
	@Override
	public int newFeed(FeedDTO dto) {
		return feedDao.newFeed(dto);
	}
	
	@Override
	public String getFeedCount() {
		return feedDao.getFeedCount(); 
	}
}
