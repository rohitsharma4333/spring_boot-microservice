package com.shop_now.customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableEurekaClient
public class ShopNowCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopNowCustomerServiceApplication.class, args);
	}

}