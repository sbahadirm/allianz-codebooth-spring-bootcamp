package com.bahadirmemis.codebooth.codeboothspringbootcamp;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.springcore.product.dao.CategoryDao;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.springcore.product.dao.ProductDao;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.springcore.product.entity.Category;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.springcore.product.entity.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class CodeboothSpringBootcampApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(CodeboothSpringBootcampApplication.class, args);

//		CategoryDao categoryDao = applicationContext.getBean(CategoryDao.class);
//		ProductDao productDao = applicationContext.getBean(ProductDao.class);
//
//		Category category = new Category();
//		category.setName("Giyim");
//
//		category = categoryDao.save(category);
//
//		Product product = new Product();
//		product.setName("Kazak");
//		product.setCategory(category);
//		product.setPrice(BigDecimal.valueOf(100));
//		product.setIsActive(true);
//		product.setExpireDate(new Date());
//		product = productDao.save(product);
//
//		System.out.println("saved!");



	}

}
