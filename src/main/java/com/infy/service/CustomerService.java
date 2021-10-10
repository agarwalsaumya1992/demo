package com.infy.service;
import java.util.List;
import com.infy.dto.CustomerDTO;
import com.infy.exceptions.NoSuchCustomerException;
public interface CustomerService {
	public String createCustomer(CustomerDTO customerDTO) throws Exception;
	public List<CustomerDTO> fetchCustomer();
	public String updateCustomer(long id, CustomerDTO customerDTO) throws NoSuchCustomerException;
	public String deleteCustomer(long id) throws NoSuchCustomerException;
}
