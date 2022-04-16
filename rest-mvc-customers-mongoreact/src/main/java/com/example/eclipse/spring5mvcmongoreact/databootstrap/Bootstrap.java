package com.example.eclipse.spring5mvcmongoreact.databootstrap;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.eclipse.spring5mvcmongoreact.api.model.Customer;
import com.example.eclipse.spring5mvcmongoreact.api.repositories.CustomerReactiveRepository;
import com.example.eclipse.spring5mvcmongoreact.services.CustomerService;

import reactor.core.publisher.Mono;



/**
 * Created by ccl on 26/03/2022.
 */
@Component
public class Bootstrap implements CommandLineRunner{


    private final CustomerService customerService;
    private final CustomerReactiveRepository customerRepository;


    public Bootstrap( CustomerService customerService, CustomerReactiveRepository customerRepository) {

        this.customerService = customerService;
        this.customerRepository=customerRepository;
   
    }

    @Override
    public void run(String... args) throws Exception {


        loadCustomers();

    }

 

    private void loadCustomers() {
        //given
        Customer customer1 = new Customer();
        customer1.setId(1l);
        customer1.setFirstname("Michale");
        customer1.setLastname("Weston");
        customerService.createNewCustomer(customer1).block();

        Customer customer2 = new Customer();
        customer2.setId(2l);
        customer2.setFirstname("Sam");
        customer2.setLastname("Axe");

        customerService.createNewCustomer(customer2).block();
        
        Customer customer3 = new Customer();
        //customer3.setId(2l);
        customer3.setFirstname("Prueba");
        customer3.setLastname("Repository");
        Mono<Customer> pruebasalvar = customerRepository.save(customer3);
        
        System.out.println(pruebasalvar.block().toString());

        System.out.println("Customers Loaded: " + customerRepository.count().block());
    }
}
