package com.springboot.jpa.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
}
