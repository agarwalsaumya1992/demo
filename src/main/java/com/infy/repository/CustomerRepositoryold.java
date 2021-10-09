package com.infy.repository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import com.infy.dto.CustomerDTO;
@Repository
public class CustomerRepository {
	List<CustomerDTO> customers = null;
//Equivalent/similar to constructor. Here, populates the DTOs in a hard-coded way
	@PostConstruct
	public void initializer()
	{
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setAddress("Chennai");
		customerDTO.setName("Jack"); 
		customerDTO.setEmail("Jack@infy.com");
		customerDTO.setPhoneNo(9951212222l);
		customers = new ArrayList<>();
		customers.add(customerDTO);
	}
	
	//adds the received customer object to customers list
	public boolean createCustomer(CustomerDTO customerDTO)
	{
		customers.add(customerDTO);
		return true;
	}
	
	//returns a list of customers
	public List<CustomerDTO> fetchCustomer()
	{
		return customers;
	}
	
	//deletes customer - exception handling incorporated
		public boolean deleteCustomer(long phoneNumber) 
		{   
			boolean responseDelete=false;
			for(CustomerDTO customer : customers)
			{ 
				if(customer.getPhoneNo() == phoneNumber)
				{
					customers.remove(customer);
					responseDelete=true;
					break;
				}
			}
			return responseDelete;
		}
		//finds the customer based on phoneNumber and updates the details of the same
			public boolean updateCustomer(long phoneNumber, CustomerDTO customerDTO)
			{
				boolean responseUpdate=false;
				
				for(CustomerDTO customer : customers)
				{
					if(customer.getPhoneNo() == phoneNumber)
					{
						if(customerDTO.getAddress()!=null)
						{
							customer.setAddress(customerDTO.getAddress());
						}
						if(customerDTO.getEmail()!=null)
						{
							customer.setEmail(customerDTO.getEmail());
						}
						responseUpdate = true;
						break;
					}
				}
				return responseUpdate;
			}
}
