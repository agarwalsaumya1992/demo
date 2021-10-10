package com.infy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.infy.dto.CustomerDTO;
import com.infy.dto.CustomerResponseBuilder;
import com.infy.dto.ResponseBuilder;
import com.infy.exceptions.NoSuchCustomerException;
import com.infy.service.CustomerService;


@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {

	// CustomerController needs to contact CustomerService, hence dependency
	// injecting the customerService reference
	@Autowired
	private CustomerService customerService;

	// Fetching customer details
	@GetMapping(produces = "application/json")
	public ResponseEntity<ResponseBuilder> fetchCustomer() {

		CustomerResponseBuilder response = new CustomerResponseBuilder();
		response.setResponseCode(HttpStatus.OK.value());
		response.setMessage("fetched successfully");
		response.setCustomerList(customerService.fetchCustomer());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Adding a customer
	@PostMapping(consumes = "application/json")
	public ResponseEntity<ResponseBuilder> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws Exception {
		
		ResponseBuilder response = new ResponseBuilder();
		response.setResponseCode(HttpStatus.OK.value());
		response.setMessage(customerService.createCustomer(customerDTO));
		return new ResponseEntity<>(response, HttpStatus.OK);
	 
		
		
	}

	// Updating an existing customer
	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<ResponseBuilder> updateCustomer(@PathVariable("id") long id, @RequestBody CustomerDTO customerDTO)
			throws NoSuchCustomerException {
		
		
		ResponseBuilder response = new ResponseBuilder();
		response.setResponseCode(HttpStatus.OK.value());
		response.setMessage(customerService.updateCustomer(id, customerDTO));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Deleting a customer
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseBuilder> deleteCustomer(@PathVariable("id") long id) throws NoSuchCustomerException {
		
		
		ResponseBuilder response = new ResponseBuilder();
		response.setResponseCode(HttpStatus.OK.value());
		response.setMessage(customerService.deleteCustomer(id));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// request
	@GetMapping(value = "callDetails", produces = "application/json")
	public String fetchCallDetails(@RequestParam("calledBy") long calledBy, @RequestParam("calledOn") String calledOn) {
		return "called by " + calledBy + " called on " + calledOn;
	}

}
