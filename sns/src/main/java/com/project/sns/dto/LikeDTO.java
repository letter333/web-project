package com.project.sns.dto;

public class LikeDTO {
	private int like_num;
	private int like_feed_id;
	private String like_user_id;
	public int getLike_num() {
		return like_num;
	}
	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}
	public int getLike_feed_id() {
		return like_feed_id;
	}
	public void setLike_feed_id(int like_feed_id) {
		this.like_feed_id = like_feed_id;
	}
	public String getLike_user_id() {
		return like_user_id;
	}
	public void setLike_user_id(String like_user_id) {
		this.like_user_id = like_user_id;
	}
	
	
}
