package com.springboot.jpa;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.springboot.jpa.data.entity.Human;
import com.springboot.jpa.data.repository.HumanRepository;

@SpringBootTest
public class HumanRepositoryTest {
	
	@Autowired
	HumanRepository humanRepository;
	
	@Test
	void sortingAndPagingTest() {
		Human human1 = new Human();
		human1.setName("홍길동");
		human1.setAge(25);
		human1.setJob("무직");
		human1.setBirth(LocalDateTime.now());
		
		Human human2 = new Human();
		human2.setName("홍길동");
		human2.setAge(17);
		human2.setJob("학생");
		human2.setBirth(LocalDateTime.now());
		
		Human human3 = new Human();
		human3.setName("길동");
		human3.setAge(30);
		human3.setJob("회사원");
		human3.setBirth(LocalDateTime.now());
		
		Human savedHuman1 = humanRepository.save(human1);
		Human savedHuman2 = humanRepository.save(human2);
		Human savedHuman3 = humanRepository.save(human3);
		
		// Sort.by( 여러개 넣을수 있음, )
		//System.out.println(humanRepository.findByName("홍길동", (Pageable) Sort.by(Order.asc("age"))));
		
		// humanRepository.findByName("홍길동", Sort.by(Order.asc("age"),  Order.asc("job")) );
		// 위 예시처럼 하면 결국에 가독성이 떨어집니다. getSort()처럼 함수화하는 방법도 있습니다.
		//System.out.println(humanRepository.findByName("홍길동", (Pageable) getSort() ));
	}
	
	private Sort getSort() {
		return Sort.by(
				Order.asc("age"),  Order.asc("job")
				);
				
	}
}
