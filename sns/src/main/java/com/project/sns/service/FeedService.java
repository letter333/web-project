package com.project.sns.service;

import java.util.List;

import com.project.sns.dto.FeedDTO;
import com.project.sns.dto.UploadFileDTO;

public interface FeedService {

	Integer getFeedMax();

	int newFeed(FeedDTO dto);

	FeedDTO getFeed(String feed_id);

	List<UploadFileDTO> getUploadFile(String feed_id);

	void fileUpload(String originalFileName, String saveFileName, String savePath, long fileSize, int feed_id);

}
