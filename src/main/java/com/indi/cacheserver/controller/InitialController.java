package com.indi.cacheserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.indi.cacheserver.entity.User;
import com.indi.cacheserver.service.UserService;

@Controller
public class InitialController {

	@Autowired
	private UserService userService;
	
	public void initialProcess() {
		User user = userService.getUser(1);
		if(user != null) return;
		
		for (int i=0; i<1000; i++) {
			userService.addUser(new User("User"));
		}
	}
	
}
