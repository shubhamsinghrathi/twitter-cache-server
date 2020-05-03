package com.indi.cacheserver.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.indi.cacheserver.model.User;

class RedisRepo {
	static String User = "USER";
}

@Repository
public class RedisUserRepositoryImpl implements RedisUserRepositiry {
	
	private HashOperations<String, String, User> hashOperations;
	
	@Autowired
	public RedisUserRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
		hashOperations = redisTemplate.opsForHash();
	}
	
	@Override 
	public void add(User user) {
		hashOperations.put(RedisRepo.User, user.getId(), user);
	}

	@Override
	public Map<String, User> findAll() {
		return hashOperations.entries(RedisRepo.User);
	}

	@Override
	public void update(User user) {
		add(user);
	}

	@Override
	public void delete(String id) {
		hashOperations.delete(RedisRepo.User, id);
	}

	@Override
	public User findById(String id) {
		return hashOperations.get(RedisRepo.User, id);
	}

}
