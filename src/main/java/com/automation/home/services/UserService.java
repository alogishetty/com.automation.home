package com.automation.home.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automation.home.models.User;
import com.automation.home.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRespository;

	@Transactional
	public User save(User user) {
		this.userRespository.save(user);
		return User.addHATEOAS(user);
	}

	@Transactional
	public Iterable<User> getAll() {
		Iterable<User> users = this.userRespository.findAll();
		users.forEach(user -> User.addHATEOAS(user));
		return users;
	}

	@Transactional
	public User login(String email, String password) {
		User user = (User)this.userRespository.findUserByEmail(email);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				return User.addHATEOAS(user);
			}
		}
		return null;
	}
	
}
