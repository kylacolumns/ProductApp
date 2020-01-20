package com.rakuten.training.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.domain.Product;

public class ProductServiceImplTest {

	@Test
	public void addNewProduct_ReturnValidIdWhenProductValue_GTEQ_MinValue() {
		//Arrange
		ProductServiceImpl service= new ProductServiceImpl();
		Product toBeAdded= new Product("test",10000,1);	//notice 10k*1>=10k
		ProductDAO mockDAO= Mockito.mock(ProductDAO.class);
		Product saved= new Product("test",10000,1);
		saved.setId(1);
		Mockito.when(mockDAO.save(toBeAdded)).thenReturn(saved);
		service.setDao(mockDAO);
		//Act
		int id= service.addNewProduct(toBeAdded);
		//Assert
		assertTrue(id>0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addNewProduct_ThrowsExceptionWhenProductValue_LT_MinValue()
	{
		//Arrange
		ProductServiceImpl service= new ProductServiceImpl();
		Product toBeAdded= new Product("test",9999,1);	//notice 9999*1<10k
		//Act
		service.addNewProduct(toBeAdded);
		//Assert
	}
	
	@Test
	public void removeProduct_NoExceptionIfProductValue_LT_MinValue()
	{
		ProductServiceImpl service= new ProductServiceImpl();
		Product toBeAdded= new Product("test",10000,1);	
		toBeAdded.setId(1);
		ProductDAO mockDAO= Mockito.mock(ProductDAO.class);
		Mockito.when(mockDAO.findById(toBeAdded.getId())).thenReturn(toBeAdded);
		service.setDao(mockDAO);
		service.removeProduct(toBeAdded.getId());
		
	}
	
	@Test(expected = IllegalStateException.class)
	public void removeProduct_ThrowsExceptionIfProductValue_GTEQ_MinValue()
	{
		ProductServiceImpl service= new ProductServiceImpl();
		Product toBeAdded= new Product("test",120000,1);	
		toBeAdded.setId(1);
		ProductDAO mockDAO= Mockito.mock(ProductDAO.class);
		//mockDAO.save(toBeAdded);
		Mockito.when(mockDAO.findById(toBeAdded.getId())).thenReturn(toBeAdded);
		service.setDao(mockDAO);
		service.removeProduct(toBeAdded.getId());
		
	}
}
