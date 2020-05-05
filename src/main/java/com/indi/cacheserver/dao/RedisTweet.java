package com.indi.cacheserver.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.indi.cacheserver.model.TimelineTweet;

@Repository
public class RedisTweet {
	
	private HashOperations<String, Integer, TimelineTweet> hashOperations;
	private ListOperations<Integer, Integer> listOperations;
	private String tweetData = "TWEETDATA";
	
	@Autowired
	public RedisTweet(RedisTemplate<String, TimelineTweet> redisTemplate, RedisTemplate<Integer, Integer> redisListTemplate) {
		hashOperations = redisTemplate.opsForHash();
		listOperations = redisListTemplate.opsForList();
	}

	public void addTweet(TimelineTweet t) {
		hashOperations.put(tweetData, t.getId(), t);
	}
	
	public void updateTweet(TimelineTweet t) {
		hashOperations.put(tweetData, t.getId(), t);
	}
	
	public void deleteTweet(int id) {
		hashOperations.delete(tweetData, id);
	}
	
	public TimelineTweet getTweet(int i) {
		return hashOperations.get(tweetData, i);
	}
	
	public List<TimelineTweet> getTweets(List<Integer> ids) {
		return hashOperations.multiGet(tweetData, ids);
	}
	
	public void addTweetIdToList(int userId, int tweetId) {
		listOperations.leftPush(userId, tweetId);
	}
	
	public void addTweetIdsToList(int userId, List<Integer> tweetId) {
		listOperations.leftPushAll(userId, tweetId);
	}
	
	public List<Integer> getTweerIds(int userId, int pageNumber, int limit) {
		return listOperations.range(userId, (pageNumber - 1) * limit, (pageNumber*limit) - 1);
	}
	
}
