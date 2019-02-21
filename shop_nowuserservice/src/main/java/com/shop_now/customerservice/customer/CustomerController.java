package com.shop_now.customerservice.customer;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shop_now.customerservice.exception.UserAlreadyExistException;
import com.shop_now.customerservice.exception.UserNotFoundException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository repository;
	
	@GetMapping("/customers")
	public List<Customer> retrieveAllUser() {
		List<Customer> customers = repository.findAll();
		
		if(customers.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		}
		
		return(customers);
	}

	@GetMapping("/customers/{username}")
	public Customer retrieveUser(@PathVariable String username) {
		Customer user = repository.findOneByUsername(username);
		
		if(user == null) {
			throw new UserNotFoundException("User Not Found");
		}
		
		return(user);
	}

	@PostMapping("/customers")
	public ResponseEntity<Object> addUser(@RequestBody Customer bodyUser) {
		Customer customer = repository.findOneByUsername(bodyUser.getUsername());
		if(customer != null) {
			throw new UserAlreadyExistException("Username already present - " + bodyUser.getUsername());
		}
		
		customer = repository.save(bodyUser);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{username}")
				.buildAndExpand(customer.getUsername())
				.toUri();
		
		return(ResponseEntity.created(location).build());
	}
	
	@PutMapping("/customers")
	public ResponseEntity<Object> updateUser(@RequestBody Customer bodyUser) {		
		Customer customer = repository.findOneByUsername(bodyUser.getUsername());
		if(customer == null) {
			throw new UserNotFoundException("User Not Found");
		}
		
		customer.setAddress(bodyUser.getAddress());
		customer = repository.save(customer);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{username}")
				.buildAndExpand(customer.getUsername())
				.toUri();
		
		return(ResponseEntity.created(location).build());
	}
	
	@DeleteMapping("/customers/{username}")
	public void deleteUser(@PathVariable String username) {
		Customer customer = repository.findOneByUsername(username);
		if(customer == null) {
			throw new UserNotFoundException("User Not Found");
		}
		
		repository.delete(customer);
	}
}
