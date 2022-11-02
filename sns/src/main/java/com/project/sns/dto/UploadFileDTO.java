package com.project.sns.dto;

public class UploadFileDTO {
	private int file_num;
	private String original_name;
	private String file_name;
	private String file_save_path;
	private long file_size;
	private int feed_id;
	public int getFile_num() {
		return file_num;
	}
	public void setFile_num(int file_num) {
		this.file_num = file_num;
	}
	public String getOriginal_name() {
		return original_name;
	}
	public void setOriginal_name(String original_name) {
		this.original_name = original_name;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public long getFile_size() {
		return file_size;
	}
	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}
	public int getFeed_id() {
		return feed_id;
	}
	public void setFeed_id(int feed_id) {
		this.feed_id = feed_id;
	}
	
	public String getFile_save_path() {
		return file_save_path;
	}
	public void setFile_save_path(String file_save_path) {
		this.file_save_path = file_save_path;
	}
	public UploadFileDTO() {
		super();
	}
	public UploadFileDTO(int file_num, String original_name, String file_name, String file_save_path, long file_size,
			int feed_id) {
		super();
		this.file_num = file_num;
		this.original_name = original_name;
		this.file_name = file_name;
		this.file_save_path = file_save_path;
		this.file_size = file_size;
		this.feed_id = feed_id;
	}
	
	
}
