package com.example.eclipse.spring5mvcmongoreact.services;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;

import com.example.eclipse.spring5mvcmongoreact.api.model.Customer;
import com.example.eclipse.spring5mvcmongoreact.api.repositories.CustomerReactiveRepository;
import com.example.eclipse.spring5mvcmongoreact.routeconfig.WebConfig;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jt on 9/27/17.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerReactiveRepository customerRepository;

	public CustomerServiceImpl(CustomerReactiveRepository customerRepository) {

		this.customerRepository = customerRepository;
	}

	@Override
	public Flux<Customer> getAllCustomers() {
		return customerRepository.findAll();

	}
	
	
	public Flux<Customer> getAllCustomersWithInterval() {
		
		
	
		
		
		return customerRepository
								.findAll()
								.collectList()
								.flatMapMany( lista->
								
								Flux.interval(Duration.ofMillis(1000))
								.take(lista.size())
								.map(index->lista.get(index.intValue()))
								
								);
								
								

								
								
								
			
			
		
	
		
	}

	@Override
	public Mono<Customer> getCustomerById(Long id) {

		Mono<Customer> mono = Mono.empty();
		return customerRepository.findById(id);

	}

	@Override
	public Mono<Customer> createNewCustomer(Customer customer) {

		Mono<Customer> savedCustomer = customerRepository.save(customer);

		return savedCustomer.map((customerlocal) -> {

			customerlocal.setCustomerUrl(getCustomerUrl(customerlocal.getId()));
			return customerlocal;
		});

	}

	@Override
	public Mono<Customer> saveCustomer(Long id, Customer customer) {
		customer.setId(id);
		customer.setCustomerUrl(getCustomerUrl(customer.getId()));

		return customerRepository.save(customer);

		
	}
	
	
	@Override
	public Flux<Customer> saveAllCustomers(Publisher<Customer> customerStream) {
		
		return customerRepository.saveAll(customerStream);

		
	}

	@Override
	public Mono<Customer> patchCustomer(Long id, Customer customer) {
		return customerRepository.findById(id)
				.map(customersave -> {

			if (customer.getFirstname() != null) {
				customersave.setFirstname(customer.getFirstname());
			}

			if (customer.getLastname() != null) {
				customersave.setLastname(customer.getLastname());
			}

			customersave.setCustomerUrl(getCustomerUrl(customersave.getId()));

			customerRepository.save(customersave);
			return customersave;

		}

		);
	}

	private String getCustomerUrl(Long id) {
		return WebConfig.BASE_URL + "/" + id;
	}

	@Override
	public Mono<Void> deleteCustomerById(Long id) {
		customerRepository.deleteById(id);
		
		return Mono.empty();
	}
}
