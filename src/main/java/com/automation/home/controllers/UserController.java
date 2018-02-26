package com.automation.home.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.automation.home.models.Login;
import com.automation.home.models.User;
import com.automation.home.services.UserService;

@RestController
@RequestMapping(path="/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(path="/", method = RequestMethod.GET)
	public ResponseEntity<Iterable<User>> getAllUsers() {
		return new ResponseEntity<Iterable<User>>(this.userService.getAll(), HttpStatus.OK);
	}
	
	@RequestMapping(path="/add", method = RequestMethod.POST)
	public ResponseEntity<User> add(@RequestBody User user) {	
		return new ResponseEntity<User>(this.userService.save(user), HttpStatus.CREATED);
	}
	
	@RequestMapping(path="/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody Login login) {
		User user = this.userService.login(login.getEmail(), login.getPassword());
		if (user != null) {
			return new ResponseEntity<Object>(user, HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Login failed. Invalid username and password", HttpStatus.UNAUTHORIZED);
	}
	
	
	
//	@GetMapping(path="/login")
//	public 
}
