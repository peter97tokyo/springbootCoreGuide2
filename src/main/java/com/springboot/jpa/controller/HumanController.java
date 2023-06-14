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
import com.springboot.jpa.data.dto.HumanDto;
import com.springboot.jpa.data.dto.HumanResponseDto;
import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.data.repository.HumanRepository;
import com.springboot.jpa.service.HumanService;
import com.springboot.jpa.service.impl.HumanServiceImpl;

@RestController
@RequestMapping("/human")
public class HumanController {
	
	private final HumanService humanService;
	
	@Autowired
	public HumanController(HumanService humanService) {
		this.humanService = humanService;
	}
	
	@GetMapping()
	public ResponseEntity<HumanResponseDto> getProduct(Long number){
		HumanResponseDto productResponseDto = humanService.getHuman(number);
		
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto); 
	}
	
	@PostMapping()
	public ResponseEntity<HumanResponseDto> createProduct(@RequestBody HumanDto humanDto){
		HumanResponseDto productResponseDto = humanService.saveHuman(humanDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
	}
	@PutMapping()
	public ResponseEntity<HumanResponseDto> changeProduct(@RequestBody HumanResponseDto humanResponseDto) throws Exception{
		HumanResponseDto productResponseDto = humanService.changeHumanJob(humanResponseDto.getNumber(), humanResponseDto.getName());
		
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
	}
	@DeleteMapping()
	public ResponseEntity<String> deleteProduct(Long number) throws Exception{
		humanService.deleteHuman(number);
		
		return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
	}
}
