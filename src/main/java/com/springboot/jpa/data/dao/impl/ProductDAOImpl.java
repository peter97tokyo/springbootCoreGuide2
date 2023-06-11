package com.springboot.jpa.data.dao.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.repository.ProductRepository;



@Component
// ProductDAOImpl 클래스를 스프링이 관리하는 빈으로 등록할려면 component 어노테이션 또는 service를 사
public class ProductDAOImpl implements ProductDAO{
	
	private final ProductRepository productRepository;
	
	public ProductDAOImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	@Override
	public Product insertProduct(Product product) {
		Product saveProduct = productRepository.save(product);
		return saveProduct;
	}
	
	@Override
	public Product selectProduct(Long number) {
		Product selectedProduct = productRepository.getById(number);
		/*
		 * 내부적으로 entityManager의 getReference() 메서드를 호출합니다. getReference()
		 * 메서드를 호출하면 프락시 객체를 리턴합니다. 실제 쿼리는 프락시 객체를 통해 최초로 데이터에 
		 * 접근하는 시점에 실행됩니다. 이때 데이터가 존재하지 않는 경우에는 entityNotFoundException이
		 * 발생 
		 */
		
		// @@@@@@@@@@@findById@@@@@@@@@@@
		
		/*
		 * 내부적으로 entityManager의 find() 메서드를 호출, 이 메서드는 영속성 컨텍스트의 캐시에서
		 * 값을 조회한 후 영속성 컨텍스트에 값이 존재하지 않는다면 실제 데이터베이스를 조회합니다.
		 * 리턴 값으로 optional 객체를 전달합니다. 
		 */
		return selectedProduct;
	}
	@Override
	public Product updateProduct(Long number, String name) throws Exception {
		Optional<Product> selectedProduct = productRepository.findById(number);
		
		Product updatedProduct;
		if(selectedProduct.isPresent()) {
			Product product = selectedProduct.get();
			
			product.setName(name);
			product.setUpdateAt(LocalDateTime.now());
			
			updatedProduct = productRepository.save(product);
		}else {
			throw new Exception();
		}
		return updatedProduct;
	}
	@Override
	public void deleteProduct(Long number) throws Exception {
		Optional<Product> selectedProduct = productRepository.findById(number);
		
		if(selectedProduct.isPresent()) {
			Product product = selectedProduct.get();
			
			productRepository.delete(product);
		}else {
			throw new Exception();
		}

	}
}
