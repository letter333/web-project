package com.project.sns.dto;

public class CommentDTO {
	private int comment_id;
	private int comment_feed_id;
	private String comment_user_id;
	private String comment_content;
	private String comment_created_at;
	
	
	public String getComment_created_at() {
		return comment_created_at;
	}
	public void setComment_created_at(String comment_created_at) {
		this.comment_created_at = comment_created_at;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public int getComment_feed_id() {
		return comment_feed_id;
	}
	public void setComment_feed_id(int comment_feed_id) {
		this.comment_feed_id = comment_feed_id;
	}
	public String getComment_user_id() {
		return comment_user_id;
	}
	public void setComment_user_id(String comment_user_id) {
		this.comment_user_id = comment_user_id;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	
	
}
