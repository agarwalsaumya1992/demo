package com.infy.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.infy.dto.ResponseBuilder;
import com.infy.exceptions.NoSuchRecordException;

@RestController
@RequestMapping("/products")
public interface ProductController {
	

	@GetMapping(produces = "application/json")
	public ResponseEntity<ResponseBuilder> fetchProduct();
	

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseBuilder> deleteProduct(long id) throws NoSuchRecordException;
	

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseBuilder> createProduct(String product, MultipartFile file) throws Exception;
	

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseBuilder> updateProduct(String product, MultipartFile file)
			throws NoSuchRecordException, Exception;
	

	@GetMapping(value = "file", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getFile(String file) throws Exception;
	
	@DeleteMapping("file/{filename}")
	public ResponseEntity<ResponseBuilder> deleteFile(String file) throws Exception;
	
	@GetMapping(value = "categories", produces = "application/json")
	public ResponseEntity<ResponseBuilder> fetchCategories();

	

}
