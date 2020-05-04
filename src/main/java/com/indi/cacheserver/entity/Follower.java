package com.indi.cacheserver.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "followers", uniqueConstraints = { @UniqueConstraint( columnNames = { "follower_id", "following_id" } ) })
public class Follower {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@ManyToOne(cascade= {
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.REFRESH
	})
	@JoinColumn(name="follower_id")
	private User follower;
	
	@ManyToOne(cascade= {
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.REFRESH
	})
	@JoinColumn(name="following_id")
	private User following;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}

	public User getFollowing() {
		return following;
	}

	public void setFollowing(User following) {
		this.following = following;
	}

	public Follower(User follower, User following) {
		super();
		this.follower = follower;
		this.following = following;
	}

	public Follower() {
		super();
	}
	
}
