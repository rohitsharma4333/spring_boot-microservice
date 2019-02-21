package com.shop_now.authenticationservice.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AppUser {

	@Id
	@GeneratedValue
	private Long Id;
	
	@Column(name="username", unique=true)
	private String username;
	
	@Column(name="password")
	private String password;
	
	// Constructors
	public AppUser() {
		super();
	}
	
	public AppUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	// Getters and Setters
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
