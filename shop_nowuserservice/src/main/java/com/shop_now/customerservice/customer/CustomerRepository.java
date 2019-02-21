package com.shop_now.customerservice.customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends
		JpaRepository<Customer, Long>{
	List<Customer> findAll();
	Customer findOneByUsername(String username);
	Customer save(Customer customer);
	void delete(Customer customer);
//	void merge(User user);
}
