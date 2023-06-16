package com.springboot.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpa.data.dto.ChangeProductNameDto;
import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	/*
	 * 컨트롤러는 클라이언트로부터 요청을 받고 해당 요청에 대해 서비스 레이어에 구현된 적절한 메서드를 호출해서 결괏값을 받습
	 * 니다. 이처럼 컨트롤러는 요청과 응답을 전달하는 역할만 맡는 것이 좋습니다. 
	 */
	
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping()
	public ResponseEntity<ProductResponseDto> getProduct(Long number){
		ProductResponseDto productResponseDto = productService.getProduct(number);
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto); 
	}
	
	
	@PostMapping()
	public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto){
		ProductResponseDto productResponseDto = productService.saveProduct(productDto);
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
	}
	@PutMapping()
	public ResponseEntity<ProductResponseDto> changeProduct(@RequestBody ChangeProductNameDto changeProductNameDto) throws Exception{
		ProductResponseDto productResponseDto = productService.changeProductName(changeProductNameDto.getNumber(), changeProductNameDto.getName());
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
	}
	@DeleteMapping()
	public ResponseEntity<String> deleteProduct(Long number) throws Exception{
		productService.deleteProduct(number);
		return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
	}
}
