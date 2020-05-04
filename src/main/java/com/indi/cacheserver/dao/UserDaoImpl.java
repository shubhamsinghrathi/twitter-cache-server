package com.indi.cacheserver.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.indi.cacheserver.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public User addUser(User user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	public User getUser(int id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public List<User> getAllUsers() {
		Query userQuery = entityManager.createQuery("from User", User.class);
		@SuppressWarnings("unchecked")
		List<User> users = userQuery.getResultList();
		return users;
	}

}
