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
import com.infy.dto.ProductDTO;

import com.infy.dto.ResponseBuilder;
import com.infy.exceptions.NoSuchRecordException;
import com.infy.service.ProductService;
import com.infy.service.FileService;


@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

	
	// injecting the customerService reference
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FileService fileService;

	
//	private static Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<ResponseBuilder> fetchProduct() {

		ResponseBuilder response = new ResponseBuilder();
		response.setResponseCode(HttpStatus.OK.value());
		response.setMessage("fetched successfully");
		response.setList(productService.fetchProduct());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

//update
	@PutMapping(consumes = "application/json")
	public ResponseEntity<ResponseBuilder> updateProduct(@RequestBody ProductDTO customerDTO) throws NoSuchRecordException {

		ResponseBuilder response = new ResponseBuilder();
		response.setResponseCode(HttpStatus.OK.value());
		response.setMessage(productService.updateProduct(customerDTO));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Deleting 
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseBuilder> deleteProduct(@PathVariable("id") long id) throws NoSuchRecordException {

		ResponseBuilder response = new ResponseBuilder();
		response.setResponseCode(HttpStatus.OK.value());
		response.setMessage(productService.deleteProduct(id));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseBuilder> createProduct(@RequestParam("product") String customer, @RequestParam(value = "file", required = false) MultipartFile file)
			throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		ProductDTO dto = mapper.readValue(customer, ProductDTO.class);
		
		String filename =UUID.randomUUID().toString();
		dto.setPhoto(filename);
		
		if(file!=null)
		fileService.saveFile(filename, file);
	         
	     ResponseBuilder response = new ResponseBuilder();
			response.setResponseCode(HttpStatus.OK.value());
			response.setMessage(productService.createProduct(dto));
			return new ResponseEntity<>(response, HttpStatus.OK);

	}

	// query params 
	@GetMapping(value = "file", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getFile(@RequestParam("file") String file) throws Exception {
		
		byte[] bytes=fileService.getFile(file);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);

		

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
