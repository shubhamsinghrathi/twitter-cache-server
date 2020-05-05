package com.indi.cacheserver.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.indi.cacheserver.dao.RedisTweet;
import com.indi.cacheserver.entity.Follower;
import com.indi.cacheserver.entity.Tweet;
import com.indi.cacheserver.entity.User;
import com.indi.cacheserver.model.TimelineTweet;
import com.indi.cacheserver.service.FollowerService;
import com.indi.cacheserver.service.TweetService;
import com.indi.cacheserver.service.UserService;

@Controller
public class InitialController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private FollowerService followerService;
	
	@Autowired
	private TweetService tweetService;
	
	@Autowired
	private RedisTweet redisTweet;
	
	private int totalUsers = 1000; // 1000, 10, 1000
	private int followerStart = 101; // 51, 6, 101
	private int followingEnd = 100; // 50, 5, 100
	private int totalTweets = 1000; // 100, 10, 1000
	private int cacheEnd = 101; // 100, 10, 101
	
	public void initialProcess() {
		User user = userService.getUser(1);
		if(user != null) {
			System.out.println("____DATA PREPARETION ALREADY DONE____");
			return;
		}
		
		System.out.println("____DATA PREPARETION IS ON GOING____");
		
		Map<Integer, User> userMap = new HashMap<Integer, User>();
		
		for (int i=1; i<=totalUsers; i++) {
			User u = new User("User" + i);
			userMap.put(i, u);
			userService.addUser(u);
		}
		
		System.out.println("__USER ADDED__");
		
		for (int i=followerStart; i<=totalUsers; i++) {
			for (int j=1; j<=followingEnd; j++) {
				followerService.addFollow(new Follower(userMap.get(i), userMap.get(j)));
			}
		}
		
		System.out.println("__FOLLOWING DONE__");
		
		for (int k=followerStart; k<=cacheEnd; k++) {
			redisTweet.addTweetIdToList(99991, k); // A bad hack
		}
		
		System.out.println("__CACHED USER LIST ADDED__");
		
		for (int i=1; i<=followingEnd; i++) {
			System.out.println("Adding tweets for user " + i + "/" + followingEnd);
			User u = userMap.get(i);
			
			for (int j=1; j<=totalTweets; j++) {
				Tweet t = tweetService.addTweet(new Tweet("Tweet number" + j + " by user" + i, u));
				
				// code for storing this tweet in redis
				TimelineTweet tlt = new TimelineTweet(t.getId(), u.getId(), u.getName(), t.getContent(), t.getCreatedAt());
				redisTweet.addTweet(tlt);
				
				//code for saving each tweet number in redis list for users 51 to 100
				for (int k=followerStart; k<=cacheEnd; k++) {
					redisTweet.addTweetIdToList(k, t.getId());
				}
			}
		}
		
		System.out.println("____DATA PREPARETION DONE____");
	}
	
}
