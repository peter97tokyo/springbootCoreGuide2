package com.springboot.jpa.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jpa.data.entity.Human;

public interface HumanRepository extends JpaRepository<Human, Long>{

}
