package com.galega.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.galega.customer.infrastructure",
		"com.galega.customer.adapters",
		"com.galega.customer.domain"
})

public class CustomerApplication {

	public static void main(String[] args) {

		SpringApplication.run(CustomerApplication.class, args);
	}
}