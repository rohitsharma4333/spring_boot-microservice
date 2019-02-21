package com.shop_now.authenticationservice.user;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shop_now.authenticationservice.exception.UserAlreadyExistException;
import com.shop_now.authenticationservice.exception.UserNotFoundException;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
public class AppUserController {

	@Autowired
	private AppUserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/users")
	public List<AppUser> retrieveAllUser() {
		List<AppUser> appUsers = repository.findAll();
		
		if(appUsers.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		}
		
		return(appUsers);
	}

	@GetMapping("/users/{username}")
	public AppUser retrieveUser(@PathVariable String username) {
		AppUser appUser = repository.findOneByUsername(username);
		
		if(appUser == null) {
			throw new UserNotFoundException("User Not Found");
		}
		
		return(appUser);
	}

	@PostMapping("/sign-up")
	public ResponseEntity<Object> addUser(@RequestBody AppUser bodyUser) {
		AppUser appUser = repository.findOneByUsername(bodyUser.getUsername());
		if(appUser != null) {
			throw new UserAlreadyExistException("Username already present - " + bodyUser.getUsername());
		}
		
		appUser = new AppUser(bodyUser.getUsername(), bCryptPasswordEncoder.encode(bodyUser.getPassword()));
//		appUser.setPassword(bCryptPasswordEncoder.encode(bodyUser.getPassword()));
		appUser = repository.save(appUser);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{username}")
				.buildAndExpand(appUser.getUsername())
				.toUri();
		
		return(ResponseEntity.created(location).build());
	}
	
	@PutMapping("/users")
	public ResponseEntity<Object> updateUser(@RequestBody AppUser bodyUser) {		
		AppUser appUser = repository.findOneByUsername(bodyUser.getUsername());
		if(appUser == null) {
			throw new UserNotFoundException("User Not Found");
		}
		
		appUser.setPassword(bCryptPasswordEncoder.encode(bodyUser.getPassword()));
		appUser = repository.save(appUser);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{username}")
				.buildAndExpand(appUser.getUsername())
				.toUri();
		
		return(ResponseEntity.created(location).build());
	}
	
	@DeleteMapping("/users/{username}")
	public void deleteUser(@PathVariable String username) {
		AppUser appUser = repository.findOneByUsername(username);
		if(appUser == null) {
			throw new UserNotFoundException("User Not Found");
		}
		
		repository.delete(appUser);
	}
	
//	@PostMapping("/auth")
//	public String error() {
//		return("here");
//	}
}
