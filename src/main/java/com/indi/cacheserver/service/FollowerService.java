package com.indi.cacheserver.service;

import java.util.List;

import com.indi.cacheserver.entity.Follower;

public interface FollowerService {

	Follower addFollow(Follower follower);
	
	List<Follower> getFollowings(int followerId);
	
	List<Follower> getFollowers(int followingId);
	
}
