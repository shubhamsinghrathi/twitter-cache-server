package com.indi.cacheserver.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indi.cacheserver.dao.RedisTweet;
import com.indi.cacheserver.model.TimelineTweet;
import com.indi.cacheserver.service.TweetService;

@RestController
@RequestMapping("/api/timeline")
public class TweetRestController {

	@Autowired
	private RedisTweet redisTweet;
	
	@Autowired
	private TweetService tweetService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<TimelineTweet>> getTimeline(
			@PathVariable int userId,
			@RequestParam(required = false, value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(required = false, value = "limit", defaultValue = "20") int limit) {
		List<Integer> tweetIds = redisTweet.getTweerIds(userId, pageNumber, limit);
		List<TimelineTweet> tweets = redisTweet.getTweets(tweetIds);
		return new ResponseEntity<List<TimelineTweet>>(tweets, HttpStatus.OK);
	}
	
	@GetMapping("/db/{userId}")
	public ResponseEntity<List<TimelineTweet>> getTimelineFromDB(
			@PathVariable int userId,
			@RequestParam(required = false, value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(required = false, value = "limit", defaultValue = "20") int limit) {
		List<TimelineTweet> tweets = tweetService.getTimeline(userId, pageNumber, limit);
		return new ResponseEntity<List<TimelineTweet>>(tweets, HttpStatus.OK);
	}
	
}
