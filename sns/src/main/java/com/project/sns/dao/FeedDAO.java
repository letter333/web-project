package com.project.sns.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.sns.dto.CommentDTO;
import com.project.sns.dto.FeedDTO;
import com.project.sns.dto.LikeDTO;
import com.project.sns.dto.ProfileDTO;
import com.project.sns.dto.UploadFileDTO;

@Repository
public class FeedDAO {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int newFeed(FeedDTO dto) {
		return this.sqlSessionTemplate.insert("feed.new_feed", dto);
	}
	
	public Integer getFeedMax() {
		return this.sqlSessionTemplate.selectOne("feed.get_feed_max");
	}
	
	public int fileUpload(HashMap<String, Object> map) {
		return this.sqlSessionTemplate.insert("feed.file_upload", map);
	}
	
	public List<FeedDTO> getFeed() {
		return this.sqlSessionTemplate.selectList("feed.get_feed");
	}

	public List<UploadFileDTO> getUploadFile() {
		return this.sqlSessionTemplate.selectList("feed.get_upload_file");
	}
	
	public int newComment(CommentDTO dto) {
		return this.sqlSessionTemplate.insert("feed.new_comment", dto);
	}
	
	public List<CommentDTO> getComment() {
		return this.sqlSessionTemplate.selectList("feed.get_comment");
	}
		
	public int deleteComment(Map<String, Object> map) {
		return this.sqlSessionTemplate.delete("feed.delete_comment", map);
	}
	
	public int deleteFeed(String feed_id) {
		return this.sqlSessionTemplate.delete("feed.delete_feed", feed_id);
	}
	
	public List<UploadFileDTO> getFeedUploadFile(String feed_id) {
		return this.sqlSessionTemplate.selectList("feed.get_feed_upload_file", feed_id);
	}
	
	public int modifyFeed(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("feed.modify_feed",  map);
	}
	
	public FeedDTO getFeedById(String feed_id) {
		return this.sqlSessionTemplate.selectOne("feed.get_feed_by_id", feed_id);
	}
	
	public List<ProfileDTO> getProfile() {
		return this.sqlSessionTemplate.selectList("feed.get_user_profile");
	}
	
	public FeedDTO likeUp(LikeDTO dto) {
		FeedDTO feedDTO = new FeedDTO();
		feedDTO.setFeed_id(dto.getLike_feed_id());
		int result = this.sqlSessionTemplate.insert("feed.insert_like", dto);
		if(result == 1) {
			this.sqlSessionTemplate.update("feed.feed_like_up", dto);
			feedDTO = this.sqlSessionTemplate.selectOne("feed.get_feed_by_id", feedDTO.getFeed_id().toString());
		}
		
		return feedDTO;
	}
	
	public FeedDTO likeCancel(LikeDTO dto) {
		FeedDTO feedDTO = new FeedDTO();
		feedDTO.setFeed_id(dto.getLike_feed_id());
		int result = this.sqlSessionTemplate.delete("feed.delete_like", dto);
		if(result == 1) {
			this.sqlSessionTemplate.update("feed.feed_like_down", dto);
			feedDTO = this.sqlSessionTemplate.selectOne("feed.get_feed_by_id", feedDTO.getFeed_id().toString());
		}
		
		return feedDTO;
	}
	
	public List<LikeDTO> getLikeAll(String like_user_id) {
		return this.sqlSessionTemplate.selectList("feed.get_like_all", like_user_id);
	}
}
