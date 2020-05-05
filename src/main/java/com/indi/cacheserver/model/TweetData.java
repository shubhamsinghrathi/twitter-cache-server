package com.indi.cacheserver.model;

import java.util.Date;

public class TweetData {

	private int id;
	private int userId;
	private String content;
	private Date createdAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Override
	public String toString() {
		return "TimelineTweet [id=" + id + ", userId=" + userId + ", content=" + content
				+ ", createdAt=" + createdAt + "]";
	}
	
	public TweetData() {
		super();
	}
	
	public TweetData(int id, int userId, String content, Date createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.content = content;
		this.createdAt = createdAt;
	}
	
	public TweetData(int userId, String content) {
		super();
		this.userId = userId;
		this.content = content;
	}
	
}
