package com.example.eclipse.spring5mvcmongoreact.services;





import java.util.List;

import com.example.eclipse.spring5mvcmongoreact.api.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;




/**
 * Created by jt on 9/27/17.
 */
public interface CustomerService {

    Flux<Customer> getAllCustomers();
    Flux<Customer> getAllCustomersWithInterval();

    Mono<Customer> getCustomerById(Long id);

    Mono<Customer> createNewCustomer(Customer customer);

    Mono<Customer> saveCustomer(Long id, Customer customer);

    Mono<Customer> patchCustomer(Long id, Customer customer);

    Mono<Void> deleteCustomerById(Long id);
}
