package com.indi.cacheserver.dao;

import java.util.List;

import com.indi.cacheserver.entity.User;

public interface UserDao {

	User addUser(User user);
	
	User getUser(int id);
	
	List<User> getAllUsers();
	
}
