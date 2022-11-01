package com.project.sns.service;

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
}
