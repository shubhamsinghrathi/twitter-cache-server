package com.indi.cacheserver.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.indi.cacheserver.model.RedisUser;

class RedisRepo {
	static String User = "USER";
}

@Repository
public class RedisUserRepositoryImpl implements RedisUserRepositiry {
	
	private HashOperations<String, String, RedisUser> hashOperations;
	
	@Autowired
	public RedisUserRepositoryImpl(RedisTemplate<String, RedisUser> redisTemplate) {
		hashOperations = redisTemplate.opsForHash();
	}
	
	@Override 
	public void add(RedisUser user) {
		hashOperations.put(RedisRepo.User, user.getId(), user);
	}

	@Override
	public Map<String, RedisUser> findAll() {
		return hashOperations.entries(RedisRepo.User);
	}

	@Override
	public void update(RedisUser user) {
		add(user);
	}

	@Override
	public void delete(String id) {
		hashOperations.delete(RedisRepo.User, id);
	}

	@Override
	public RedisUser findById(String id) {
		return hashOperations.get(RedisRepo.User, id);
	}

}
