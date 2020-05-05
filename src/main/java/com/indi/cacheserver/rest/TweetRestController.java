package com.indi.cacheserver.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indi.cacheserver.dao.RedisTweet;
import com.indi.cacheserver.entity.Tweet;
import com.indi.cacheserver.entity.User;
import com.indi.cacheserver.model.TimelineTweet;
import com.indi.cacheserver.model.TweetData;
import com.indi.cacheserver.service.TweetService;
import com.indi.cacheserver.service.UserService;

@RestController
@RequestMapping("/api/timeline")
public class TweetRestController {

	@Autowired
	private RedisTweet redisTweet;
	
	@Autowired
	private TweetService tweetService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<TimelineTweet>> getTimeline(
			@PathVariable int userId,
			@RequestParam(required = false, value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(required = false, value = "limit", defaultValue = "25") int limit) {
		List<Integer> tweetIds = redisTweet.getTweerIds(userId, pageNumber, limit);
		List<TimelineTweet> tweets = new ArrayList<TimelineTweet>();
		redisTweet.getTweets(tweetIds).forEach(tweet -> {
			if (tweet!=null) tweets.add(tweet);
		});
		return new ResponseEntity<List<TimelineTweet>>(tweets, HttpStatus.OK);
	}
	
	@GetMapping("/db/{userId}")
	public ResponseEntity<List<TimelineTweet>> getTimelineFromDB(
			@PathVariable int userId,
			@RequestParam(required = false, value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(required = false, value = "limit", defaultValue = "25") int limit) {
		List<TimelineTweet> tweets = tweetService.getTimeline(userId, pageNumber, limit);
		return new ResponseEntity<List<TimelineTweet>>(tweets, HttpStatus.OK);
	}
	
	@GetMapping("/mix/{userId}")
	public ResponseEntity<List<TimelineTweet>> getTimelineMixDataSource(
			@PathVariable int userId,
			@RequestParam(required = false, value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(required = false, value = "limit", defaultValue = "25") int limit) {
		List<Integer> tweetIds = redisTweet.getTweerIds(userId, pageNumber, limit);
		if (tweetIds != null && tweetIds.size() > 0) {
			List<TimelineTweet> tweets = new ArrayList<TimelineTweet>();
			redisTweet.getTweets(tweetIds).forEach(tweet -> {
				if (tweet!=null) tweets.add(tweet);
			});
			return new ResponseEntity<List<TimelineTweet>>(tweets, HttpStatus.OK);
		} else {
			List<TimelineTweet> allTimelineTweets = tweetService.getTimeline(userId, 1, 400);
			List<Integer> allTweetIds = new ArrayList<Integer>();
			
			for (int i = allTimelineTweets.size() - 1; i>=0; i--) {
				TimelineTweet tweet = allTimelineTweets.get(i);
				redisTweet.updateTweet(tweet);
				allTweetIds.add(tweet.getId());
			}
			
			redisTweet.addTweetIdToList(99991, userId); // A bad hack
			redisTweet.addTweetIdsToList(userId, allTweetIds);
			List<Integer> tweetIdsNew = redisTweet.getTweerIds(userId, pageNumber, limit);
			List<TimelineTweet> tweets = new ArrayList<TimelineTweet>();
			redisTweet.getTweets(tweetIdsNew).forEach(tweet -> {
				if (tweet!=null) tweets.add(tweet);
			});
			return new ResponseEntity<List<TimelineTweet>>(tweets, HttpStatus.OK);
		}
	}
	
	
	
	@PostMapping
	public ResponseEntity<String> updateTweet(@RequestBody TweetData tweetData) {
		User user = userService.getUser(tweetData.getUserId());
		Tweet tweet = new Tweet(tweetData.getContent(), user);
		tweet.setId(tweetData.getId());
		tweet.setCreatedAt(tweetData.getCreatedAt());
		Tweet t = tweetService.updateTweet(tweet);
		TimelineTweet tlt = new TimelineTweet(t.getId(), user.getId(), user.getName(), t.getContent(), t.getCreatedAt());
		redisTweet.updateTweet(tlt);
		return new ResponseEntity<String>("Tweet updated", HttpStatus.OK);
	}
	
	@DeleteMapping("/{tweetId}")
	public ResponseEntity<String> deleteTweet(@PathVariable int tweetId) {
		tweetService.deleteTweet(tweetId);
		redisTweet.deleteTweet(tweetId);
		return new ResponseEntity<String>("Tweet removed", HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addTweet(@RequestBody TweetData tweetData) {
		User user = userService.getUser(tweetData.getUserId());
		Tweet tweet = new Tweet(tweetData.getContent(), user);
		Tweet t = tweetService.addTweet(tweet);
		
		TimelineTweet tlt = new TimelineTweet(t.getId(), user.getId(), user.getName(), t.getContent(), t.getCreatedAt());
		redisTweet.addTweet(tlt);
		
		List<Integer> cachedUserIds = redisTweet.getTweerIds(99991, 1, 10000);
		System.out.println("cachedUserIds: " + cachedUserIds.size());
		cachedUserIds.forEach(userId -> {
			redisTweet.addTweetIdToList(userId, t.getId());
		});
		
		return new ResponseEntity<String>("Tweet added", HttpStatus.OK);
	}
	
}
