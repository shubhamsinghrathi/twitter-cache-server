package com.indi.cacheserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indi.cacheserver.dao.TweetDao;
import com.indi.cacheserver.entity.Tweet;
import com.indi.cacheserver.model.TimelineTweet;

@Service
public class TweetServiceImpl implements TweetService {
	
	@Autowired
	private TweetDao tweetDao;

	@Override
	@Transactional
	public Tweet addTweet(Tweet tweet) {
		return tweetDao.addTweet(tweet);
	}

	@Override
	@Transactional
	public Tweet getTweet(int id) {
		return tweetDao.getTweet(id);
	}

	@Override
	@Transactional
	public List<TimelineTweet> getTimeline(int id, int pageNumber, int limit) {
		return tweetDao.getTimeline(id, pageNumber, limit);
	}

	@Override
	@Transactional
	public Tweet updateTweet(Tweet tweet) {
		return tweetDao.updateTweet(tweet);
	}

	@Override
	@Transactional
	public void deleteTweet(int tweetId) {
		tweetDao.deleteTweet(tweetId);
	}

}
