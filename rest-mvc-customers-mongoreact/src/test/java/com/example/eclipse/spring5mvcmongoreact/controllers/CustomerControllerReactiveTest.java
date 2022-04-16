package com.example.eclipse.spring5mvcmongoreact.controllers;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import com.example.eclipse.spring5mvcmongoreact.api.model.Customer;
import com.example.eclipse.spring5mvcmongoreact.api.repositories.CustomerReactiveRepository;
import com.example.eclipse.spring5mvcmongoreact.controller.CustomerControllerReactive;
import com.example.eclipse.spring5mvcmongoreact.services.CustomerService;
import com.example.eclipse.spring5mvcmongoreact.services.CustomerServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class CustomerControllerReactiveTest {

	@Mock
	CustomerReactiveRepository customerReactiveRepository;

	CustomerService customerService;

	CustomerControllerReactive customerController;
	
	 WebTestClient webTestClient;
	
	@BeforeEach
    public void setUp() throws Exception {
     
		customerReactiveRepository= Mockito.mock(CustomerReactiveRepository.class);
		

		customerService  = new CustomerServiceImpl(customerReactiveRepository); 
		customerController = new CustomerControllerReactive(customerService);
		
        webTestClient = WebTestClient.bindToController(customerController).build();
    }

    @Test
    public void list() {
        given(customerReactiveRepository.findAll())
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
        given(customerReactiveRepository.saveAll(any(Publisher.class)))
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
    
}
