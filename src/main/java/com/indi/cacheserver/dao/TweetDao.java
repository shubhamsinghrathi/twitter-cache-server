package com.indi.cacheserver.dao;

import java.util.List;

import com.indi.cacheserver.entity.Tweet;
import com.indi.cacheserver.model.TimelineTweet;

public interface TweetDao {

	Tweet addTweet(Tweet tweet);
	
	Tweet getTweet(int id);
	
	List<TimelineTweet> getTimeline(int id, int pageNumber, int limit);

	Tweet updateTweet(Tweet tweet);

	void deleteTweet(int tweetId);
	
}
