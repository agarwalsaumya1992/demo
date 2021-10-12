package com.infy.demo;


	import static org.junit.Assert.assertEquals;

	import org.junit.Before;
	import org.junit.Test;
	import org.springframework.http.MediaType;
	import org.springframework.test.web.servlet.MvcResult;
	import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.infy.dto.ProductDTO;
import com.infy.dto.ResponseBuilder;

	

	public class TestProductController extends TestDemoApplication {
		
		ProductDTO dto1 = new ProductDTO();

	   @Override
	   @Before
	   public void setUp() {
	      super.setUp();
	      dto1.setCategory("test");
	      dto1.setName("testname");
	      dto1.setQuantity(5);
	      dto1.setPrice(350.00);
	      

	   }
	   @Test
	   public void getProductList() throws Exception {
	      String uri = "/products";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      ResponseBuilder response = super.mapFromJson(content, ResponseBuilder.class);
	      assertEquals(response.getMessage(),"fetched successfully");
	   }
//	   @Test
//	   public void createProduct() throws Exception {
//	      String uri = "/products";
//	      String inputJson = super.mapToJson(dto1);
//	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//	         .contentType(MediaType.APPLICATION_JSON_VALUE)
//	         .content(inputJson)).andReturn();
//	      
//	      int status = mvcResult.getResponse().getStatus();
//	      assertEquals(200, status);
//	      String content = mvcResult.getResponse().getContentAsString();
//	      ResponseBuilder response = super.mapFromJson(content, ResponseBuilder.class);
//	      assertEquals(response.getMessage(), "Record got added successfully");
//	   }
	 
	}