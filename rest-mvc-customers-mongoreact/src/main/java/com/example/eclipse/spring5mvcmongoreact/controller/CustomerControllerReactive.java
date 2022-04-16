package com.example.eclipse.spring5mvcmongoreact.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eclipse.spring5mvcmongoreact.api.model.Customer;
import com.example.eclipse.spring5mvcmongoreact.services.CustomerService;

import reactor.core.publisher.Flux;

@RestController
@CrossOrigin({"http://127.0.0.1:5500/","localhost","localhost:8080","*"})
public class CustomerControllerReactive {
	
	private final CustomerService customerService;
	
	public CustomerControllerReactive(CustomerService customerService) {
		
		this.customerService= customerService;
	}
	
	 @GetMapping(value = "/api/v2/customers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<Customer> findAllByThreadId() {
        return customerService.getAllCustomersWithInterval();
    }

}
