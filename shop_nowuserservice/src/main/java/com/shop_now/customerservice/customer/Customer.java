package com.shop_now.customerservice.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {

	@Id
	@GeneratedValue
	private Long Id;
	
	@Column(name="username", unique=true)
	private String username;
	
	@Column(name="address")
	private String address;
	
	private int port;
	
	// Constructors
	public Customer() {
		super();
	}
	
	public Customer(String username, String address) {
		super();
		this.username = username;
		this.address  = address;
		this.port     = 0;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
