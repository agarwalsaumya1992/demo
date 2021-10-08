package com.infy.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.infy.dto.CustomerDTO;
import com.infy.exceptions.NoSuchCustomerException;
import com.infy.repository.CustomerRepository;
import com.infy.util.InfyConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("customerService")
@PropertySource("classpath:ValidationMessages.properties")
public class CustomerServiceImpl implements CustomerService{
	
	private static Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private Environment environment;

	//Contacts repository layer to add customer
		public String createCustomer(CustomerDTO customerDTO)
		{
			boolean response=customerRepository.createCustomer(customerDTO);
			 if(!response) {
				 return "Some issue occured";
			 }
			 return environment.getProperty(InfyConstants.CUSTOMER_CREATE_SUCCESS.toString());
		}
		//makes a call to repository method for returning a list of customers
		public List<CustomerDTO> fetchCustomer()
		{
			log.info("Customer list is being fetched");
			List<CustomerDTO> customers = customerRepository.fetchCustomer();
			return customers;
		}
		//Contacts repository layer to delete customer
		public String deleteCustomer(long phoneNumber)throws NoSuchCustomerException
		{
			boolean response = customerRepository.deleteCustomer(phoneNumber);
			if(!response)
				throw new NoSuchCustomerException(environment.getProperty(InfyConstants.CUSTOMER_NOT_FOUND.toString()));
			return environment.getProperty(InfyConstants.CUSTOMER_DELETE_SUCCESS.toString());
			
		}
		//Contacts repository layer to update customer
		public String updateCustomer(long phoneNumber, CustomerDTO customerDTO) throws NoSuchCustomerException
		{
			boolean response = customerRepository.updateCustomer(phoneNumber,customerDTO);
			if(!response)
				throw new NoSuchCustomerException(environment.getProperty(InfyConstants.CUSTOMER_NOT_FOUND.toString()));
			return environment.getProperty(InfyConstants.CUSTOMER_UPDATE_SUCCESS.toString());
		}
	
}
