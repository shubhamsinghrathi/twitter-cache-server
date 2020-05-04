package com.indi.cacheserver.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indi.cacheserver.dao.RedisUserRepositiry;
import com.indi.cacheserver.model.RedisUser;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private RedisUserRepositiry redisUserRepository;
	
	@PostMapping
	public ResponseEntity<String> addUser(@RequestBody RedisUser user) {
		redisUserRepository.add(user);
		return new ResponseEntity<String>("User added to redis", HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RedisUser> findUser(@PathVariable String id) {
		return new ResponseEntity<RedisUser>(redisUserRepository.findById(id), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Map<String, RedisUser>> getAllUsers() {
		return new ResponseEntity<Map<String, RedisUser>>(redisUserRepository.findAll(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id) {
		redisUserRepository.delete(id);
		return new ResponseEntity<String>("User delete from redis", HttpStatus.OK);
	}
	
}
