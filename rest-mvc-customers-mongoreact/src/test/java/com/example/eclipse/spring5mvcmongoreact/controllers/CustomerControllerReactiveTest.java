package com.example.eclipse.spring5mvcmongoreact.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.eclipse.spring5mvcmongoreact.api.model.Customer;
import com.example.eclipse.spring5mvcmongoreact.api.repositories.CustomerReactiveRepository;
import com.example.eclipse.spring5mvcmongoreact.controller.CustomerControllerReactive;
import com.example.eclipse.spring5mvcmongoreact.services.CustomerService;

import reactor.core.publisher.Flux;

class CustomerControllerReactiveTest {

	@Mock
	CustomerReactiveRepository customerReactiveRepository;
	@InjectMocks
	CustomerService customerService;
	CustomerControllerReactive customerController;
	
	 WebTestClient webTestClient;
	
	@BeforeEach
    public void setUp() throws Exception {
     
        webTestClient = WebTestClient.bindToController(customerController).build();
    }

    @Test
    public void list() {
        given(customerReactiveRepository.findAll())
                .willReturn(Flux.just(new Customer(1L,"Custom1","Lastname1"),
                		new Customer(2L,"Custom2","Lastname2")));

        webTestClient.get()
                .uri("/api/v2/customer/")
                .exchange()
                .expectBodyList(Customer.class)
                .hasSize(2);
    }
}
