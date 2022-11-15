package com.project.sns.dto;

import java.sql.Timestamp;

public class FeedDTO {
	private Integer feed_id;
	private String feed_content;
	private String feed_user_id;
	private String feed_created_at;
	private int feed_comment_count;
	private int feed_like_count;
	private int feed_last_feed_id;
	
	
	public int getFeed_like_count() {
		return feed_like_count;
	}
	public void setFeed_like_count(int feed_like_count) {
		this.feed_like_count = feed_like_count;
	}
	public int getFeed_last_feed_id() {
		return feed_last_feed_id;
	}
	public void setFeed_last_feed_id(int feed_last_feed_id) {
		this.feed_last_feed_id = feed_last_feed_id;
	}
	public int getFeed_comment_count() {
		return feed_comment_count;
	}
	public void setFeed_comment_count(int feed_comment_count) {
		this.feed_comment_count = feed_comment_count;
	}
	public Integer getFeed_id() {
		return feed_id;
	}
	public void setFeed_id(Integer feed_id) {
		this.feed_id = feed_id;
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
	
	
}
