package com.springboot.jpa.service.impl;

import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.springboot.jpa.data.dto.HumanResponseDto;
import com.springboot.jpa.data.entity.Human;
import com.springboot.jpa.data.repository.HumanRepository;


public class HumanServiceTest {
	private HumanRepository humanRepository = Mockito.mock(HumanRepository.class);
	private HumanServiceImpl humanService;
	
	@BeforeEach
	public void setUpTest() {
		humanService = new HumanServiceImpl(humanRepository);
	}
	/* getHuman의 단위테스트
	 * 단위테스트를 위해서는 외부 요인을 모두 배제하도록 코드를 작성해야합니다.
	 * @SpringzBootTestm @WebMvcTest 등의 @... Test 어노테이션이 선언돼 있지 않습니다.
	 */
	@Test
	void gethumanTest() {
		Human givenHuman = new Human(); // 여기서 부터
		givenHuman.setName("홍길동");
		givenHuman.setAge(25);
		givenHuman.setJob("경찰");
		
		Mockito.when(humanRepository.findById(123L))
		.thenReturn(Optional.of(givenHuman)); // 여기까지
		/*
		 *  테스트에 사용될 human 엔티티 객체를생성하고 
		 *  HumanRepository의 동작에 대한 결괏값 리턴을 설정합니다.
		 */
		
		
		HumanResponseDto humanResponseDto = humanService.getHuman(123L);
		Assertions.assertEquals(humanResponseDto.getName(), givenHuman.getName());
		Assertions.assertEquals(humanResponseDto.getAge(), givenHuman.getAge());
		Assertions.assertEquals(humanResponseDto.getJop(), givenHuman.getJob());
		// 테스트에서 리턴받은 HumanRepository 객체에 대해서 38 ~ 41번 줄과 같이 Assertion을 통해 값을 검증
		// 함으로써 테스트의 목적을 달성하는지 확인합니다.
		
		verify(humanRepository).findById(123L);// 부가 검증 
	}
}
