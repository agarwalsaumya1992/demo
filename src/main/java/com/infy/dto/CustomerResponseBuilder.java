package com.infy.dto;

import java.util.List;

public class CustomerResponseBuilder extends ResponseBuilder {
	
	private List<CustomerDTO> customerList;

	public List<CustomerDTO> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerDTO> customerList) {
		this.customerList = customerList;
	}
	

}
