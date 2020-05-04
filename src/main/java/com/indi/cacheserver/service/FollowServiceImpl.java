package com.indi.cacheserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indi.cacheserver.dao.FollowerDao;
import com.indi.cacheserver.entity.Follower;

@Service
public class FollowServiceImpl implements FollowerService {
	
	@Autowired
	private FollowerDao followerDao;

	@Override
	@Transactional
	public Follower addFollow(Follower follower) {
		return followerDao.addFollow(follower);
	}

	@Override
	@Transactional
	public List<Follower> getFollowings(int followerId) {
		return followerDao.getFollowings(followerId);
	}

	@Override
	@Transactional
	public List<Follower> getFollowers(int followingId) {
		return followerDao.getFollowers(followingId);
	}

}
