package com.automation.home.models;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.automation.home.controllers.UserController;
import com.automation.home.others.CryptoConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "users")
public class User extends ResourceSupport {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@JsonProperty("id")
	private Integer userId;

	@NotNull
	@Column(length = 100)
	private String firstName;

	@NotNull
	@Column(length = 100)
	private String lastName;

	@NotNull
	@Email
	@Column(length = 255, unique = true)
	private String email;

	@NotNull
	@Column(length = 255)
	@Convert(converter = CryptoConverter.class)
	private String password;

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setId(Integer id) {
		this.userId = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static User addHATEOAS(User user) {
		user.add(ControllerLinkBuilder.linkTo(UserController.class).slash(user.getUserId()).withSelfRel());
		return user;
	}

}
