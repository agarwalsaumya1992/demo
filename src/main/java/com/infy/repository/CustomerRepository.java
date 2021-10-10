package com.infy.repository;

import java.util.List;

import com.infy.dto.CustomerDTO;

public interface CustomerRepository {
	
	public List<CustomerDTO> fetchCustomer();
	
    public int createCustomer(CustomerDTO dto);
    
    public int deleteCustomer(long id);
    public int updateCustomer(long id, CustomerDTO customerDTO);
}
