package com.springboot.jpa.data.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass // JPA의 엔티티 클래스가 상속받을 경우 자식 클래스에게 매핑 정보를 전달
@EntityListeners(AuditingEntityListener.class) 
// EntityListeners => 엔티티를 db에 적용하기 전후로 콜백을 요청할 수 있게 하는
// AuditingEntityListener.class => 엔티티의 auditing 정보를 주입하는 JPA 엔티티 리스너 클래스입니다.
public class BaseEntity {
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createAt;
	
	@LastModifiedDate
	private LocalDateTime updateAt;
	
}
