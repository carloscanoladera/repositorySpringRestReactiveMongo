package com.example.eclipse.spring5mvcmongoreact.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.eclipse.spring5mvcmongoreact.api.model.Customer;
import com.example.eclipse.spring5mvcmongoreact.api.repositories.CustomerReactiveRepository;
import com.example.eclipse.spring5mvcmongoreact.controller.CustomerControllerReactive;
import com.example.eclipse.spring5mvcmongoreact.services.CustomerService;
import com.example.eclipse.spring5mvcmongoreact.services.CustomerServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class CustomerControllerReactiveTest2 {

	@Mock
	CustomerServiceImpl customerService;
	@InjectMocks
	CustomerControllerReactive customerController;
	
	 WebTestClient webTestClient;
	
	@BeforeEach
    public void setUp() throws Exception {
     
		  MockitoAnnotations.openMocks(this);

		  webTestClient = WebTestClient.bindToController(customerController).build();
    }

    @Test
    public void list() {
        given(customerService.getAllCustomersWithInterval())
                .willReturn(Flux.just(new Customer(1L,"Custom1","Lastname1"),
                		new Customer(2L,"Custom2","Lastname2")));

        webTestClient.get()
        .uri("/api/v2/customers/")
        .exchange()
        .expectBodyList(Customer.class)
        .hasSize(2);
    }
    
    
    @Test
    public void saveAll() {
    	
    	Publisher<Customer> pubToSave= Flux.just(new Customer(1L,"Custom1","Lastname1"),
        		new Customer(2L,"Custom2","Lastname2"));    	
        given(customerService.saveAllCustomers(any(Publisher.class)))
                .willReturn(Flux.just(new Customer(1L,"Custom1","Lastname1"),
                		new Customer(2L,"Custom2","Lastname2")));

        webTestClient.post()
                .uri("/api/v2/customers/")
                .body(pubToSave, Customer.class)
                .exchange()               
                .expectBodyList(Customer.class)
                .contains(new Customer(1L,"Custom1","Lastname1"),
        		new Customer(2L,"Custom2","Lastname2"));
    }
    
    @Test
    public void deleteById() {
    	
        given(customerService.deleteCustomerById(any()))
        .willReturn(Mono.empty() );

    	
    	
    	 webTestClient.delete()
         .uri("/api/v2/customers/1")        
         .exchange()
         .expectStatus()
         .isOk();
    	
    }
}
