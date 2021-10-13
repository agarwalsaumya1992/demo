package com.infy.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;



import com.infy.dto.ResponseBuilder;
import com.infy.exceptions.NoSuchRecordException;
import com.infy.service.ProductService;
import com.infy.service.FileService;
import com.infy.util.Categories;


@Controller("productController")
public class ProductControllerImpl implements ProductController {

	
	// injecting the  reference
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FileService fileService;

	

	
	@Override
	public ResponseEntity<ResponseBuilder> fetchProduct() {

		ResponseBuilder response = new ResponseBuilder();
		response.setResponseCode(HttpStatus.OK.value());
		response.setMessage("fetched successfully");
		response.setList(productService.fetchProduct());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Deleting 
	
	@Override
	public ResponseEntity<ResponseBuilder> deleteProduct(@PathVariable("id") long id) throws NoSuchRecordException {

		ResponseBuilder response = new ResponseBuilder();
		response.setResponseCode(HttpStatus.OK.value());
		response.setMessage(productService.deleteProduct(id));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@Override
	public ResponseEntity<ResponseBuilder> createProduct(@RequestParam("product") String product, @RequestParam(value = "file", required = false) MultipartFile file)
			throws Exception {

	     	ResponseBuilder response = new ResponseBuilder();
			response.setResponseCode(HttpStatus.OK.value());
			response.setMessage(productService.createProduct(product,file));
			return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	//update
	
	@Override
		public ResponseEntity<ResponseBuilder> updateProduct(@RequestParam("product") String product, @RequestParam(value = "file", required = false) MultipartFile file) throws NoSuchRecordException, Exception {

			ResponseBuilder response = new ResponseBuilder();
			response.setResponseCode(HttpStatus.OK.value());
			response.setMessage(productService.updateProduct(product,file));
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

	// query params 
	
	@Override
	public ResponseEntity<byte[]> getFile(@RequestParam("file") String file) throws Exception {
		byte[] bytes=fileService.getFile(file);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);

	}
	
	@Override
	public ResponseEntity<ResponseBuilder> deleteFile(@PathVariable("filename") String filename) throws Exception {
		ResponseBuilder response = new ResponseBuilder();
		response.setResponseCode(HttpStatus.OK.value());
		response.setMessage(fileService.deleteFile(filename));
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	@Override
	public ResponseEntity<ResponseBuilder> fetchCategories() {

		ResponseBuilder response = new ResponseBuilder();
		response.setResponseCode(HttpStatus.OK.value());
		response.setMessage("fetched successfully");
		response.setList(Categories.getCategories());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
