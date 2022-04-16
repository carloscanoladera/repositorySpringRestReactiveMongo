package com.example.eclipse.spring5mvcmongoreact.routeconfig;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.eclipse.spring5mvcmongoreact.api.model.Customer;
import com.example.eclipse.spring5mvcmongoreact.services.CustomerService;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

/**
 * Created by ccl on 36/03/2022.
 */
@Configuration
public class WebConfig {
	public static final String BASE_URL = "/api/v1/customers";
    @Bean
    public RouterFunction<?> routes(CustomerService customerService){
        return RouterFunctions.route(GET(BASE_URL),
        		  serverRequest -> ServerResponse
                  .ok()
                  .contentType(MediaType.APPLICATION_JSON)
                                    .body(customerService.getAllCustomersWithInterval(), Customer.class));

    }
}