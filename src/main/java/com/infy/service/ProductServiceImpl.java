package com.infy.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.fasterxml.jackson.databind.ObjectMapper;
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
	private FileService fileService;
	
	@Autowired
	private Environment environment;


		public List<ProductDTO> fetchProduct()
		{
			log.info("Begin product Fetch");
			List<ProductDTO> products = productRepository.fetchProduct();
			log.info("Fetched product list: "+products.toString());
			return products;
		}
		
		public String deleteProduct(long id)throws NoSuchRecordException
		{
			log.info("Begin product delete: "+id);
			int response = productRepository.deleteProduct(id);
			log.info("Delete product response: "+response);
			if(response!=1)
				throw new NoSuchRecordException(environment.getProperty(AppConstants.RECORD_NOT_FOUND.toString()));
			return environment.getProperty(AppConstants.DELETE_SUCCESS.toString());
			
		}
	
		@Override
		public String updateProduct(String product, MultipartFile file) throws IOException, NoSuchRecordException {

			log.info("Begin product update: "+product);
			ObjectMapper mapper = new ObjectMapper();
			ProductDTO dto = mapper.readValue(product, ProductDTO.class);			
			
			if(file!=null)
			{			
			log.info("Begin Update File- "+dto.getPhoto());			
			int response= fileService.updateFile(dto.getPhoto(),file);
			log.info("Update file response: "+response);
			}
			
			int response = productRepository.updateProduct(dto);
			log.info("Update product response: "+response);
			if(response!=1)
				throw new NoSuchRecordException(environment.getProperty(AppConstants.RECORD_NOT_FOUND.toString()));
			return environment.getProperty(AppConstants.UPDATE_SUCCESS.toString());
	
		}
		@Override
		public String createProduct(String product, MultipartFile file) throws Exception, IOException {
			
			log.info("Begin product Create : "+product);
			
			ObjectMapper mapper = new ObjectMapper();
			ProductDTO dto = mapper.readValue(product, ProductDTO.class);
			
			String filename =UUID.randomUUID().toString();
			dto.setPhoto(filename);
			
			if(file!=null)
			{
			log.info("Begin Create file name: "+filename); 
			int response= fileService.saveFile(filename, file);
			log.info("Create file response: "+response); 
			}
			
			int response=productRepository.createProduct(dto);
			log.info("Create product response: "+response); 
	    	return environment.getProperty(AppConstants.CREATE_SUCCESS.toString());
		}
		
	
}
