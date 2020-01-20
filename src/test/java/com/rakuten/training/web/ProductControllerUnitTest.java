package com.rakuten.training.web;

import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest({ProductController.class})
public class ProductControllerUnitTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ProductService service;
	
	@Test
	public void getProductById_ReturnsOkWithCorrectProductIfFound() throws Exception {
		//Arrange
		Product found= new Product("test",456.0f,600);
		int id= 1;
		found.setId(id);
		Mockito.when(service.findById(id)).thenReturn(found);
		//Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}",id))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(id)));
		
	}

	@Test
	public void getProductById_ReturnsNotFoundIfNotFound() throws Exception {
		//Arrange
		Product found= new Product("test",456.0f,600);
		int id= 1;
		found.setId(id);
		Mockito.when(service.findById(id)).thenReturn(null);
		//Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}",id)).andExpect(MockMvcResultMatchers.status().isNotFound());
		
	}
	
	@Test
	public void addProduct_ReturnsCreatedWithProductIdIfCreated() throws Exception
	{
		Product toBeAdded= new Product("hello", 2300.0f,10);
		int id=1;
		toBeAdded.setId(1);
		Mockito.when(service.addNewProduct(Mockito.any(Product.class))).thenReturn(id);
		ObjectMapper om= new ObjectMapper();
		String mapper= om.writeValueAsString(toBeAdded);
		mockMvc.perform(MockMvcRequestBuilders.post("/products").contentType(MediaType.APPLICATION_JSON).content(mapper))
			.andExpect(MockMvcResultMatchers.header().string(HttpHeaders.LOCATION, "/products/"+id))
			.andExpect(MockMvcResultMatchers.status().isCreated());
		Mockito.verify(service).addNewProduct(Mockito.any(Product.class));
	}

	@Test
	public void addProduct_ReturnsExceptionIfNotCreated() throws Exception
	{
		Product toBeAdded= new Product("hello", 23.0f,10);
		int id=1;
		toBeAdded.setId(1);
		Mockito.when(service.addNewProduct(Mockito.any(Product.class))).thenThrow(IllegalArgumentException.class);
		ObjectMapper om= new ObjectMapper();
		String mapper= om.writeValueAsString(toBeAdded);
		mockMvc.perform(MockMvcRequestBuilders.post("/products").contentType(MediaType.APPLICATION_JSON).content(mapper))
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
		Mockito.verify(service).addNewProduct(Mockito.any(Product.class));
	}
}
