package com.indi.cacheserver.dao;

import java.util.ArrayList;
import java.util.Date;
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
		Query timelineQuery = entityManager.createQuery("select t.id as id, t.user.id as userId, u.name as name,"
				+ " t.createdAt as createdAt, t.content as content"
				+ " from Follower f left join User u on u.id = f.following.id"
				+ " left join Tweet t on f.following.id = t.user.id"
				+ " where f.follower.id=:id order by t.id desc");
		timelineQuery.setParameter("id", id);
		
		timelineQuery.setFirstResult((pageNumber - 1) * limit);
		timelineQuery.setMaxResults(limit);
		@SuppressWarnings("rawtypes")
		List rows = timelineQuery.getResultList();
		
		List<TimelineTweet> tweets = new ArrayList<TimelineTweet>();
		for(int i=0; i<rows.size(); i++) {
			Object[] row = (Object[]) rows.get(i);
			tweets.add(
				new TimelineTweet((Integer)row[0], (Integer)row[1], (String)row[2], (String)row[4], (Date)row[3])
			);
		}
		return tweets;
	}

	@Override
	public Tweet updateTweet(Tweet tweet) {
		Tweet t =  entityManager.merge(tweet);
		return t;
	}

	@Override
	public void deleteTweet(int id) {
		Query deletePostQuery = entityManager.createQuery("delete from Tweet where id=:id");
		deletePostQuery.setParameter("id", id);
		deletePostQuery.executeUpdate();
	}

}
