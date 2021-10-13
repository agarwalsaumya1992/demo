package com.infy.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.infy.dto.ProductDTO;
import com.infy.exceptions.NoSuchRecordException;
public interface ProductService {
	public List<ProductDTO> fetchProduct();
	public String deleteProduct(long id) throws NoSuchRecordException;
	public String updateProduct(String product, MultipartFile file) throws IOException, NoSuchRecordException;
	public String createProduct(String product, MultipartFile file) throws Exception, IOException;
	
}
