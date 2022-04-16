package com.example.eclipse.spring5mvcmongoreact.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.example.eclipse.spring5mvcmongoreact.api.model.Customer;
import com.example.eclipse.spring5mvcmongoreact.services.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.reactivestreams.Publisher;
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
	 
	 
	 @ResponseStatus(HttpStatus.CREATED)
	    @PostMapping("/api/v2/customers")
	    Flux<Customer> create(@RequestBody Publisher<Customer> categoryStream){
	        return customerService.saveAllCustomers(categoryStream);
	    }

	    @PutMapping("/api/v2/customers/{id}")
	    Mono<Customer> update(@PathVariable String id, @RequestBody Customer customer) {
	       
	        return customerService.saveCustomer(Long.valueOf(id),customer);
	    }

	    @PatchMapping("/api/v2/customers/{id}")
	    Mono<Customer> patch(@PathVariable String id, @RequestBody Customer customer) {

	       return customerService.patchCustomer(Long.valueOf(id), customer);
	     
	    }
	 

}
