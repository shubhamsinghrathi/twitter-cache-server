package com.indi.cacheserver.model;

import java.io.Serializable;
import java.util.Date;

public class TimelineTweet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3586236887131022599L;
	private int id;
	private int userId;
	private String name;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		return "TimelineTweet [id=" + id + ", userId=" + userId + ", name=" + name + ", content=" + content
				+ ", createdAt=" + createdAt + "]";
	}
	
	public TimelineTweet() {
		super();
	}
	
	public TimelineTweet(int id, int userId, String name, String content, Date createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.content = content;
		this.createdAt = createdAt;
	}
	
}
