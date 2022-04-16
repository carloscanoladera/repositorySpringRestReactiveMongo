package com.example.eclipse.spring5mvcmongoreact.api.repositories;

//Cambio


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.eclipse.spring5mvcmongoreact.api.model.Customer;





/**
*
 */
public interface CustomerReactiveRepository extends ReactiveMongoRepository<Customer, Long>{

	
}
