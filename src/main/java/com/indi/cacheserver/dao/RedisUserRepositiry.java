package com.indi.cacheserver.dao;

import java.util.Map;

import com.indi.cacheserver.model.RedisUser;

public interface RedisUserRepositiry {

	void add(RedisUser user);
	
	Map<String, RedisUser> findAll();
	
	void update(RedisUser user);
	
	void delete(String id);
	
	RedisUser findById(String id);
	
}
