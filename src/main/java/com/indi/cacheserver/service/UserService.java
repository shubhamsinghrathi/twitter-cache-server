package com.indi.cacheserver.service;

import java.util.List;

import com.indi.cacheserver.entity.User;

public interface UserService {

	User addUser(User user);
	
	User getUser(int id);
	
	List<User> getAllUsers();
	
}
