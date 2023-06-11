package com.springboot.jpa.data.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
// entity 명시 및 테이블과 일대일 매칭
@Table(name= "product")
// 필수가 아니지만 사용하는 경우는 class의 이름과 테이블의 이름을 다르게 지정해야하는 경우
public class Product {
	
	@Id
	// 엔티티 클래의 필드는 테이블의 칼럼과 매핑, id 어노테이션이 선언된 필드는 테이블의 기본값 역할로
	// 사용됩니다. 모든 entity는 id 어노테이션이 필요합니다.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 보통 id 어노테이션이랑 같이 쓰임 어떻게 자동으로 값이 생성될지에 대해 
	private Long number;
	
	@Column(nullable = false)
	/*
	 * 별다른 설정이 없는경우 사용하지 않아도됨 하지만
	 * name =  table의 이름 설정, 설정하지 않는 경우 필드명으로 지정
	 * nullable = null 값 허
	 * length 저장하는 데이터의 최대 길이 설정
	 * unique 해당 컬럼을 유니크로 설정 
	 */
	private String name;
	
	@Column(nullable = false)
	private Integer price;
	
	@Column(nullable = false)
	private Integer stock;
	
	private LocalDateTime createAt;
	
	private LocalDateTime updateAt;

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}
	
	
	
	
	
}
