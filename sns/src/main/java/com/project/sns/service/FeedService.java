package com.project.sns.service;

import java.util.List;
import java.util.Map;

import com.project.sns.dto.CommentDTO;
import com.project.sns.dto.FeedDTO;
import com.project.sns.dto.UploadFileDTO;

public interface FeedService {

	Integer getFeedMax();

	int newFeed(FeedDTO dto);

	List<FeedDTO> getFeed();

	List<UploadFileDTO> getUploadFile();

	void fileUpload(String originalFileName, String saveFileName, String savePath, long fileSize, int feed_id);

	int newComment(CommentDTO dto);

	List<CommentDTO> getComment();

	int deleteComment(Map<String, Object> map);

}
