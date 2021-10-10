package com.infy.demo;


	import static org.junit.Assert.assertEquals;
	import static org.junit.Assert.assertTrue;

	import org.junit.Before;
	import org.junit.Test;
	import org.springframework.http.MediaType;
	import org.springframework.test.web.servlet.MvcResult;
	import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.infy.dto.CustomerDTO;
import com.infy.dto.CustomerResponseBuilder;
import com.infy.dto.ResponseBuilder;

	

	public class CustomerServiceControllerTest extends DemoApplicationTests {
	   @Override
	   @Before
	   public void setUp() {
	      super.setUp();
	   }
	   @Test
	   public void getCustomerList() throws Exception {
	      String uri = "/customers";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      CustomerResponseBuilder response = super.mapFromJson(content, CustomerResponseBuilder.class);
	      assertEquals(200,response.getResponseCode());
	   }
	   @Test
	   public void createCustomer() throws Exception {
	      String uri = "/customers";
	      CustomerDTO dto = new CustomerDTO();
	      dto.setPhoneNo("9876543210");
	      dto.setName("Ginger");
	      dto.setAddress("Chandigarh");
	      dto.setEmail("gig@xyz.com");
	      
	      String inputJson = super.mapToJson(dto);
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