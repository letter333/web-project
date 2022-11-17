package com.project.sns.service;

import java.util.List;
import java.util.Map;

import com.project.sns.dto.CommentDTO;
import com.project.sns.dto.FeedDTO;
import com.project.sns.dto.LikeDTO;
import com.project.sns.dto.ProfileDTO;
import com.project.sns.dto.UploadFileDTO;

public interface FeedService {

	Integer getFeedMax();

	int newFeed(FeedDTO dto);

	List<FeedDTO> getFeed();

	List<UploadFileDTO> getUploadFile();

	void fileUpload(String originalFileName, String saveFileName, long fileSize, int feed_id);

	int newComment(CommentDTO dto);

	List<CommentDTO> getComment();

	int deleteComment(Map<String, Object> map);

	int deleteFeed(String feed_id);

	List<UploadFileDTO> getFeedUploadFile(String feed_id);

	int modifyFeed(Map<String, Object> map);

	FeedDTO getFeedById(String feed_id);

	List<ProfileDTO> getProfile();

	FeedDTO likeUp(LikeDTO dto);

	FeedDTO likeCancel(LikeDTO dto);

	List<Integer> getLikeAll(String like_user_id);

}
