package com.springboot.advanced_jpa.data.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQuery;
import com.springboot.jpa.SpringbootJpaApplication;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.entity.QProduct;
import com.springboot.jpa.data.repository.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringBootTest(classes = SpringbootJpaApplication.class)
public class ProductRepositoryTest {
	
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	private ProductRepository productRepository;
	/*
	@Test
	void queryDslTest() {
		 
		JPAQuery<Product> query = new JPAQuery(entityManager); // JPAQuery 객체 => entityManager를 활용 
		QProduct qProduct = QProduct.product;
		
		List<Product> productList = query
				.from(qProduct)
				.where(qProduct.name.eq("펜"))
				.orderBy(qProduct.price.asc())
				.fetch(); // query => 빌더 형식으로 생성, 버전에 따른 list() 메서드 사용 필요 
		
		for (Product product : productList) {
			System.out.println("---------");
			System.out.println();
			System.out.println("product num" + product.getNumber());
			System.out.println("product nm" + product.getName());
			System.out.println("product price" + product.getPrice());
			System.out.println();
			System.out.println("---------");
		}
		
	}
	*/
	@Test
	public void auditingTest() {
		Product p = new Product();
		p.setName("연필");
		p.setPrice(1000);
		p.setStock(100);
		
		Product savedP = productRepository.save(p);
		
		System.out.println("productName" + savedP.getName());
		System.out.println("productCreatedAt" + savedP.getCreateAt());
	}
}
