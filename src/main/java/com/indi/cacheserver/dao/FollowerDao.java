package com.indi.cacheserver.dao;

import java.util.List;

import com.indi.cacheserver.entity.Follower;

public interface FollowerDao {
	
	Follower addFollow(Follower follower);
	
	List<Follower> getFollowings(int followerId);
	
	List<Follower> getFollowers(int followingId);
	
}
