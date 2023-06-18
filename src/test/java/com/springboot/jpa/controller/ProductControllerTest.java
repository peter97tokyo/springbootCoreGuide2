package com.springboot.jpa.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.service.impl.ProductServiceImpl;



@WebMvcTest(ProductController.class)
public class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc; 
	// API를 테스트하기 위해 사용된 객체, 정확하게는 서블릿 컨테이너의 구동
	// 없이 가상의 MVC 환경에서의 모의 HTTP 서블릿 요청하는 유틸리티 클래스입니다.
	
	
	@MockBean
	ProductServiceImpl productService;
	
	@Test
	@DisplayName("MockMvc를 통한 Product 데이트 가져오기 테스트")
	void getProduct() throws Exception {
		
		given(productService.getProduct(123L)).willReturn(new ProductResponseDto(123L, "펜슬", 100, 1000));
		String productId = "123";
		mockMvc.perform( 
				/* perform 메서드를 이용하면 서버로 URL 요청을 보내는 것처럼 통신 테스트 코드를 
				   작성해서 컨트롤러를 테스트할 수 있습니다. perform 메서드는 MockMvcRequestBuilders에서
				   제공하는 Http 메서드로 URL을 정의해서 사용합니다.
				*/
				get("/product?number=" + productId)
				).andExpect(status().isOk())
				.andExpect(jsonPath("$.number").exists())
				.andExpect(jsonPath("$.price").exists())
				.andExpect(jsonPath("$.stock").exists())
				.andDo(print()); // 요청과 응답의 전체 내용을 확인하려면 45번 줄과 같이 andDo() 메서드를 사용
		
		
		verify(productService).getProduct(123L);
		// verify 메서드는 지정된 메서드가 실행됐는지 검증하는 역할입니다.
	}
	@Test
	@DisplayName("Product 데이터 생성 테스트")
	void createProductTest() throws Exception {
		// Mock 객체에서 특정 메서드가 실행되는 경우 실제 Return을 줄 수 없기 때문에 아래와 같이 가정 사항을 만들어줌
		given(productService.saveProduct(new ProductDto("볼펜", 100, 1000)))
		// given() 메서드를 통해 ProductService의 saveProduct() 메서드의 동작 규칙을 설정하고, 
		.willReturn(new ProductResponseDto(12315L, "볼펜", 100, 1000));
		
		ProductDto productDto = ProductDto.builder()
				.name("볼펜")
				.price(100)
				.stock(1000)
				.build(); // 테스트의 필요한 객체 생성 ProductDto에 @builder 추가해야함  
		
		Gson gson = new Gson(); 
		// 구글에서 개발 json 파싱 라이브러리 자바객체를 json 문자열로 변환하거나 json 문자열을 자바 객체로
		// 변환하는 역할을 합니다.
		String content= gson.toJson(productDto);
		
		// 실제 테스트를 수행하는 코드
		mockMvc.perform(
				post("/product")
				.content(content) // @RequestBody 값을 던지기 위해 productDto를 매개변수로 넣어줌
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.number").exists())
				// post 요청을 통해 도출된 결괏값에 대해 각 항목이 존재하는지 jsonPath().exit를 통해 검증
				.andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.price").exists())
				.andExpect(jsonPath("$.stock").exists())
				.andDo(print());
		
		verify(productService).saveProduct(new ProductDto("볼펜", 100, 1000));
				
				
	}
}
