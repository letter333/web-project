package com.project.sns.service;

import com.project.sns.dto.FeedDTO;

public interface FeedService {

	String getFeedMax();

	int newFeed(FeedDTO dto);

	void fileUpload(String originalFileName, String saveFileName, long fileSize, String feed_id);

}
