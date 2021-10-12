package com.infy.controller;

import java.util.UUID;

//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

//import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.dto.CustomerDTO;

import com.infy.dto.ResponseBuilder;
import com.infy.exceptions.NoSuchCustomerException;
import com.infy.service.CustomerService;
import com.infy.service.FileService;


@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {

	// CustomerController needs to contact CustomerService, hence dependency
	// injecting the customerService reference
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private FileService fileService;

	
//	private static Logger log = LoggerFactory.getLogger(CustomerController.class);
	// Fetching customer details
	@GetMapping(produces = "application/json")
	public ResponseEntity<ResponseBuilder> fetchCustomer() {

		ResponseBuilder response = new ResponseBuilder();
		response.setResponseCode(HttpStatus.OK.value());
		response.setMessage("fetched successfully");
		response.setList(customerService.fetchCustomer());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Adding a customer
//	@PostMapping(consumes = "application/json")
//	public ResponseEntity<ResponseBuilder> createCustomer(@Valid @RequestBody CustomerDTO customerDTO)
//			throws Exception {
//
//		ResponseBuilder response = new ResponseBuilder();
//		response.setResponseCode(HttpStatus.OK.value());
//		response.setMessage(customerService.createCustomer(customerDTO));
//		return new ResponseEntity<>(response, HttpStatus.OK);
//
//	}

	// Updating an existing customer
	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<ResponseBuilder> updateCustomer(@PathVariable("id") long id,
			@RequestBody CustomerDTO customerDTO) throws NoSuchCustomerException {

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

	// query params 
	@GetMapping(value = "file", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getFile(@RequestParam("file") String file) throws Exception {
		
		byte[] bytes=fileService.getFile(file);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);

		

	}
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseBuilder> createCustomer(@RequestParam("customer") String customer, @RequestParam(value = "file", required = false) MultipartFile file)
			throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		CustomerDTO dto = mapper.readValue(customer, CustomerDTO.class);
		
		String filename =UUID.randomUUID().toString();
		dto.setPhoto(filename);
		
		if(file!=null)
		fileService.saveFile(filename, file);
	         
	     ResponseBuilder response = new ResponseBuilder();
			response.setResponseCode(HttpStatus.OK.value());
			response.setMessage(customerService.createCustomer(dto));
			return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@PostMapping(value = "file" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseBuilder> saveFile(@RequestParam("filename") String filename, @RequestParam(value = "file", required = false) MultipartFile file)
			throws Exception {

		
		fileService.saveFile(filename, file);
	         
	     ResponseBuilder response = new ResponseBuilder();
			response.setResponseCode(HttpStatus.OK.value());
			response.setMessage("File uploaded sucessfully");
			return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
