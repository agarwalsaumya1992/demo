package com.infy.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import org.springframework.stereotype.Service;


import com.infy.dto.ProductDTO;
import com.infy.exceptions.NoSuchRecordException;
import com.infy.repository.ProductRepository;
import com.infy.util.AppConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("productService")
@PropertySource("classpath:ValidationMessages.properties")
public class ProductServiceImpl implements ProductService{
	
	private static Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private Environment environment;

	//Contacts repository layer to add customer
		public String createProduct(ProductDTO productDTO) throws Exception
		{
			log.info("Begin Create : "+productDTO.toString());
			int response=productRepository.createProduct(productDTO);
			log.info("Create : "+response); 
	    	return environment.getProperty(AppConstants.CREATE_SUCCESS.toString());
		}
		//makes a call to repository method for returning a list of customers
		public List<ProductDTO> fetchProduct()
		{
			log.info("Begin Fetch");
			List<ProductDTO> products = productRepository.fetchProduct();
			log.info("Fetched list: "+products.toString());
			return products;
		}
		
		public String deleteProduct(long id)throws NoSuchRecordException
		{
			log.info("Begin delete: "+id);
			int response = productRepository.deleteProduct(id);
			log.info("Delete response: "+response);
			if(response!=1)
				throw new NoSuchRecordException(environment.getProperty(AppConstants.RECORD_NOT_FOUND.toString()));
			return environment.getProperty(AppConstants.DELETE_SUCCESS.toString());
			
		}
		//Contacts repository layer to update customer
		public String updateProduct(ProductDTO productDTO) throws NoSuchRecordException
		{
			log.info("Begin update: "+productDTO.toString());
			int response = productRepository.updateProduct(productDTO);
			log.info("Update response: "+response);
			if(response!=1)
				throw new NoSuchRecordException(environment.getProperty(AppConstants.RECORD_NOT_FOUND.toString()));
			return environment.getProperty(AppConstants.UPDATE_SUCCESS.toString());
		}
		
	
}
