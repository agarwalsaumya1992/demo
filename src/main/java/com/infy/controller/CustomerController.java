package com.infy.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
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
import javax.validation.constraints.Pattern;

import com.infy.dto.CustomerDTO;

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
	public List<CustomerDTO> fetchCustomer() {

		return customerService.fetchCustomer();
	}

	// Adding a customer
	@PostMapping(consumes = "application/json")
	public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
		return ResponseEntity.ok(customerService.createCustomer(customerDTO));
	}

	// Updating an existing customer
	@PutMapping(value = "/{phoneNumber}", consumes = "application/json")
	public String updateCustomer(@PathVariable("phoneNumber") long phoneNumber, @RequestBody CustomerDTO customerDTO)
			throws NoSuchCustomerException {
		return customerService.updateCustomer(phoneNumber, customerDTO);
	}

	// Deleting a customer
	@DeleteMapping("/{phoneNumber}")
	public String deleteCustomer(@PathVariable("phoneNumber") @Pattern(regexp = "[0-9]{10}",message="{customer.phoneNo.invalid}")  String phoneNumber)
			throws NoSuchCustomerException {
		return customerService.deleteCustomer(Long.parseLong(phoneNumber));
	}

	// request
	@GetMapping(value = "callDetails", produces = "application/json")
	public String fetchCallDetails(@RequestParam("calledBy") long calledBy, @RequestParam("calledOn") String calledOn) {
		return "called by " + calledBy + " called on " + calledOn;
	}

}
