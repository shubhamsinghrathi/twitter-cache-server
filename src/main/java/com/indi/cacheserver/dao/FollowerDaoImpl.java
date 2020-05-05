package com.indi.cacheserver.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.indi.cacheserver.entity.Follower;

@Repository
public class FollowerDaoImpl implements FollowerDao {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Follower addFollow(Follower follower) {
		entityManager.persist(follower);
		return follower;
	}

	@Override
	public List<Follower> getFollowings(int followerId) {
		Query followQuery = entityManager.createQuery("from Follower where follower_id=:followerId", Follower.class);
		followQuery.setParameter("followerId", followerId);
		@SuppressWarnings("unchecked")
		List<Follower> followings = followQuery.getResultList();
		return followings;
	}

	@Override
	public List<Follower> getFollowers(int followingId) {
		Query followQuery = entityManager.createQuery("from Follower where following_id=:followingId", Follower.class);
		followQuery.setParameter("followingId", followingId);
		@SuppressWarnings("unchecked")
		List<Follower> followers = followQuery.getResultList();
		return followers;
	}

}
