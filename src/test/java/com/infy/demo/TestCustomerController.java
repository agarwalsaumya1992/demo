package com.infy.demo;


	import static org.junit.Assert.assertEquals;

	import org.junit.Before;
	import org.junit.Test;
	import org.springframework.http.MediaType;
	import org.springframework.test.web.servlet.MvcResult;
	import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.infy.dto.CustomerDTO;
import com.infy.dto.ResponseBuilder;

	

	public class TestCustomerController extends DemoApplicationTests {
		
		CustomerDTO dto1 = new CustomerDTO();

	   @Override
	   @Before
	   public void setUp() {
	      super.setUp();
	      dto1.setPhoneNo("9876543210");
	      dto1.setName("Saumya");
	      dto1.setAddress("Chandigarh");
	      dto1.setEmail("sam@xyz.com");

	   }
	   @Test
	   public void getCustomerList() throws Exception {
	      String uri = "/customers";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      ResponseBuilder response = super.mapFromJson(content, ResponseBuilder.class);
	      assertEquals(response.getMessage(),"fetched successfully");
	   }
	   @Test
	   public void createCustomer() throws Exception {
	      String uri = "/customers";
	      String inputJson = super.mapToJson(dto1);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      ResponseBuilder response = super.mapFromJson(content, ResponseBuilder.class);
	      assertEquals(response.getMessage(), "Customer details got added successfully");
	   }
	 
	}