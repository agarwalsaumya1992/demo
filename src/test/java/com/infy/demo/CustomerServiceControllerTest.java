package com.infy.demo;


	import static org.junit.Assert.assertEquals;
	import static org.junit.Assert.assertTrue;

	import org.junit.Before;
	import org.junit.Test;
	import org.springframework.http.MediaType;
	import org.springframework.test.web.servlet.MvcResult;
	import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.infy.dto.CustomerDTO;

	

	public class CustomerServiceControllerTest extends DemoApplicationTests {
	   @Override
	   @Before
	   public void setUp() {
	      super.setUp();
	   }
	   @Test
	   public void getProductsList() throws Exception {
	      String uri = "/customers";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      CustomerDTO[] productlist = super.mapFromJson(content, CustomerDTO[].class);
	      assertTrue(productlist.length > 0);
	   }
	   @Test
	   public void createProduct() throws Exception {
	      String uri = "/customers";
	      CustomerDTO dto = new CustomerDTO();
	      dto.setPhoneNo(987654321);
	      dto.setName("Ginger");
	      
	      String inputJson = super.mapToJson(dto);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "Customer details got added successfully");
	   }
	}