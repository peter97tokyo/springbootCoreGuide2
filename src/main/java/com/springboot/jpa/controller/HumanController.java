package com.springboot.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.springboot.jpa.data.entity.Human;
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
	public ResponseEntity<HumanResponseDto> getHuman(Long number){
		HumanResponseDto HumanResponseDto = humanService.getHuman(number);
		
		return ResponseEntity.status(HttpStatus.OK).body(HumanResponseDto); 
	}
	
	@PostMapping()
	public ResponseEntity<HumanResponseDto> createHuman(@RequestBody HumanDto humanDto){
		HumanResponseDto HumanResponseDto = humanService.saveHuman(humanDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(HumanResponseDto);
	}
	@PutMapping()
	public ResponseEntity<HumanResponseDto> changeHuman(@RequestBody HumanResponseDto humanResponseDto) throws Exception{
		HumanResponseDto HumanResponseDto = humanService.changeHumanJob(humanResponseDto.getNumber(), humanResponseDto.getName());
		
		return ResponseEntity.status(HttpStatus.OK).body(HumanResponseDto);
	}
	@DeleteMapping()
	public ResponseEntity<String> deleteHuman(Long number) throws Exception{
		humanService.deleteHuman(number);
		
		return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<String> getHumanPageable(Long number){
		
		
		return ResponseEntity.status(HttpStatus.OK).body("정상적으로 작동합니다.");
	}
}
