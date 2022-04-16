package com.example.eclipse.spring5mvcmongoreact.api.model;





import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jt on 9/27/17.
 */
@Document
public class Customer {

    @Id
   
    private Long id=UUID.randomUUID().getLeastSignificantBits();

    private String firstname;
    private String lastname;
    @JsonProperty("customer_url")
    private String customerUrl;
    
    public Customer() {
    	
    	
    }
    
	public Customer(Long id, String firstname, String lastname) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCustomerUrl() {
		return customerUrl;
	}

	public void setCustomerUrl(String customerUrl) {
		this.customerUrl = customerUrl;
	}


	
	
    
    
    
}
