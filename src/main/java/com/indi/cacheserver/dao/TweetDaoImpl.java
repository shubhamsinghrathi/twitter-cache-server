package com.indi.cacheserver.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.indi.cacheserver.entity.Tweet;
import com.indi.cacheserver.model.TimelineTweet;

@Repository
public class TweetDaoImpl implements TweetDao {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Tweet addTweet(Tweet tweet) {
		entityManager.persist(tweet);
		return tweet;
	}

	@Override
	public Tweet getTweet(int id) {
		return entityManager.find(Tweet.class, id);
	}

	@Override
	public List<TimelineTweet> getTimeline(int id, int pageNumber, int limit) {
		Query timelineQuery = entityManager.createQuery("");
		
		timelineQuery.setFirstResult((pageNumber - 1) * limit);
		timelineQuery.setMaxResults(limit);
		@SuppressWarnings("unchecked")
		List<TimelineTweet> rows = timelineQuery.getResultList();
		return rows;
	}

}