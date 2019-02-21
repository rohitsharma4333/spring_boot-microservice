package com.shop_now.authenticationservice;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import com.shop_now.authenticationservice.exception.UserNotFoundException;
import com.shop_now.authenticationservice.user.AppUser;
import com.shop_now.authenticationservice.user.AppUserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AppUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = userRepository.findOneByUsername(username);
		
		if(appUser == null) {
			throw new UserNotFoundException("User Not Found");
		}
		
		return(new User(appUser.getUsername(), appUser.getPassword(), Collections.emptyList()));
	}
}
