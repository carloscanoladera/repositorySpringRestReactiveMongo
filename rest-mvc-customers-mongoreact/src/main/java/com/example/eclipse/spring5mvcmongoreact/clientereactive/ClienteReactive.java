package com.example.eclipse.spring5mvcmongoreact.clientereactive;




import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.eclipse.spring5mvcmongoreact.api.model.Customer;

import java.util.Collections;

public class ClienteReactive {

    public static void main(String[] args) {


        try {
       WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                 
               // .defaultCookie("cookieKey", "cookieValue")
                 //.defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();


       
        webClient
                .get()
                .uri("/api/v2/customers")               
                .retrieve()
                .bodyToFlux(Customer.class)
                .map(customer->"Nombre del cliente:" + customer.getFirstname())
                .subscribe(System.out::println);


            Thread.sleep(20000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
