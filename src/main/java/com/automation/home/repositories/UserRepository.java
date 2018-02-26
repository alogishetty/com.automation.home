package com.automation.home.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.automation.home.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	//@Query("SELECT new User(userId, firstName, lastName, email, password) FROM User u WHERE u.email = :email")
    User findUserByEmail(String email);
}
