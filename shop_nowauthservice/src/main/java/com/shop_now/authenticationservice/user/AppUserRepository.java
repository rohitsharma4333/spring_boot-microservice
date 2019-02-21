package com.shop_now.authenticationservice.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends
		JpaRepository<AppUser, Long>{
	List<AppUser> findAll();
	AppUser findOneByUsername(String username);
	AppUser save(AppUser customer);
	void delete(AppUser customer);
//	void merge(User user);
}