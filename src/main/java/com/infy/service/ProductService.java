package com.infy.service;

import java.util.List;
import com.infy.dto.ProductDTO;
import com.infy.exceptions.NoSuchRecordException;
public interface ProductService {
	public String createProduct(ProductDTO productDTO) throws Exception;
	public List<ProductDTO> fetchProduct();
	public String updateProduct(ProductDTO productDTO) throws NoSuchRecordException;
	public String deleteProduct(long id) throws NoSuchRecordException;
	
}
