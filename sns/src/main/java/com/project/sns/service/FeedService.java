package com.project.sns.service;

import com.project.sns.dto.FeedDTO;

public interface FeedService {

	String getFeedCount();

	int newFeed(FeedDTO dto);

}
