package com.indi.cacheserver.dao;

import java.util.Map;

import com.indi.cacheserver.model.User;

public interface RedisUserRepositiry {

	void add(User user);
	
	Map<String, User> findAll();
	
	void update(User user);
	
	void delete(String id);
	
	User findById(String id);
	
}
