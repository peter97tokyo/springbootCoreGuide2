package com.springboot.jpa.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jpa.data.entity.Product;
// JpaRepository은 Entity로 만들어진 데이터베이스에 접근하는데 사용
public interface ProductRepository extends JpaRepository<Product, Long>{

}
