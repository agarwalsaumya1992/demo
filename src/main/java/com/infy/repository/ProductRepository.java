package com.infy.repository;

import java.util.List;

import com.infy.dto.ProductDTO;

public interface ProductRepository {
	
	public List<ProductDTO> fetchProduct();
	
    public int createProduct(ProductDTO dto);
    
    public int deleteProduct(long id);
    public int updateProduct(ProductDTO productDTO);
}
