package com.project.sns.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.sns.dto.FeedDTO;
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
}
