package com.rakuten.training;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.dal.ProductDAOJpaImpl;
import com.rakuten.training.dal.ReviewDAO;
import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;
import com.rakuten.training.ui.ProductConsoleUI;

@SpringBootApplication
public class ProductAppApplication {

	public static void main(String[] args) {
		//ApplicationContext springContainer=
		
		SpringApplication.run(ProductAppApplication.class, args);
		
//		ProductConsoleUI ui=springContainer.getBean(ProductConsoleUI.class);
		
//		ReviewDAO reviewDAO= springContainer.getBean(ReviewDAO.class);
//		Review sample= new Review("self", "hello", 2);
//		Review saved= reviewDAO.save(sample,1);
//		System.out.println("Created Review With ID: "+saved.getId());
//		ui.createProductWithUI();
		//ui.findById();
		//ui.deleteproduct();
		
//		ProductDAO productDAO= springContainer.getBean(ProductDAO.class);
//		Product p= productDAO.findById(1);
//		System.out.println(p.getName());
//		System.out.println(p.getReviews().size());
		
//		ProductDAO productDAO= springContainer.getBean(ProductDAOJpaImpl.class);
//		List<Product> p= productDAO.findAll();
//		for(Product x: p)
//			System.out.println(x.getName());
//		productDAO.deleteById(2);
		
		
		
	}

}
