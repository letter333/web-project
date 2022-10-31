package com.project.sns.dto;

import java.sql.Timestamp;

public class FeedDTO {
	private int feed_id;
	private String feed_title;
	private String feed_content;
	private String feed_user_id;
	private String feed_created_at;
	public int getFeed_id() {
		return feed_id;
	}
	public void setFeed_id(int feed_id) {
		this.feed_id = feed_id;
	}
	public String getFeed_title() {
		return feed_title;
	}
	public void setFeed_title(String feed_title) {
		this.feed_title = feed_title;
	}
	public String getFeed_content() {
		return feed_content;
	}
	public void setFeed_content(String feed_content) {
		this.feed_content = feed_content;
	}
	public String getFeed_user_id() {
		return feed_user_id;
	}
	public void setFeed_user_id(String feed_user_id) {
		this.feed_user_id = feed_user_id;
	}
	public String getFeed_created_at() {
		return feed_created_at;
	}
	public void setFeed_created_at(String feed_created_at) {
		this.feed_created_at = feed_created_at;
	}
	public FeedDTO(int feed_id, String feed_title, String feed_content, String feed_user_id, String feed_created_at) {
		super();
		this.feed_id = feed_id;
		this.feed_title = feed_title;
		this.feed_content = feed_content;
		this.feed_user_id = feed_user_id;
		this.feed_created_at = feed_created_at;
	}
	public FeedDTO() {
		super();
	}
	
	
}
