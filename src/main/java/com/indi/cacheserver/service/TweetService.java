package com.indi.cacheserver.service;

import java.util.List;

import com.indi.cacheserver.entity.Tweet;
import com.indi.cacheserver.model.TimelineTweet;

public interface TweetService {

	Tweet addTweet(Tweet tweet);
	
	Tweet updateTweet(Tweet tweet);
	
	void deleteTweet(int tweetId);
	
	Tweet getTweet(int id);
	
	List<TimelineTweet> getTimeline(int id, int pageNumber, int limit);
	
}
