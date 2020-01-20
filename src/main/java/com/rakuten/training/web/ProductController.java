package com.rakuten.training.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService service;

	//@RequestMapping(method = RequestMethod.GET, value = "/products")
	@GetMapping("/products")		//Better than above
	public List<Product> getAllProducts()
	{
		return service.findAll();
	}
	
	@GetMapping("/products/{prodId}")
	public ResponseEntity<Product> getProductById(@PathVariable("prodId")int id)
	{
		Product p= service.findById(id);
		if(p!=null)
			return new ResponseEntity<Product>(p, HttpStatus.OK);
		else
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product toBeAdded)
	{
		try
		{
			int id= service.addNewProduct(toBeAdded);
			HttpHeaders headers= new HttpHeaders();
			headers.setLocation(URI.create("/products/"+id));
			return new ResponseEntity<Product>(headers,HttpStatus.CREATED);
			
		}
		catch(IllegalArgumentException e)
		{
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/products/{prodId}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("prodId") int idToBeDeleted)
	{
		try
		{
			service.removeProduct(idToBeDeleted);
			return new ResponseEntity<Product>(HttpStatus.OK);
			
		}
		catch(IllegalStateException e)
		{
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}
	}
}
