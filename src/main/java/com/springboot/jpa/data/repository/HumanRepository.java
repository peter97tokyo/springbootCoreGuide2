package com.springboot.jpa.data.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.jpa.data.entity.Human;

public interface HumanRepository extends JpaRepository<Human, Long>{
	// 정렬과 페이징 처리
	
	/*
	 * 일반적인 쿼리문에서 정렬을 사용하기 위해서는 ORDER BY를 사용합니다. 쿼리 메서드도 정렬 기능에 동일한 키워드
	 * 가 사용됩니다.  
	 */
	List<Human> findByNameOrderByAgeAsc(String name); // 오름차순
	List<Human> findByNameOrderByAgeDesc(String name); // 내림차순
	// 사람이름을 검색 후 나이순으로 오름차순/내림차순으로 정렬함
	
	/* 
	 * 쿼리 메서드의 이름에 정렬 키워드를 삽입해서 정렬을 수행하는 것도 가능하지만 메서드의 이름이 길어질수록 가독성이
	 * 떨어지는 문제가 생깁니다 이를 해결하기 위해 매개변수를 활용하는 방법이 있습니다.
	 */
	//List<Human> findByName(String name, org.springframework.data.domain.Sort sort);
	// Sort 객체를 활용해 매개변수로 받아들인 정렬 기준을 가지고 쿼리문을 작성합니다.
	
	// return에 Page를 사용해야함, 매개변수로 Pageable를 사용해야함 =>  두개의 조건이 필요함
	Page<Human> findByName(String name, org.springframework.data.domain.Pageable pageable);
	/* 
	 * findByName이 27라인이랑 겹쳐서 사용할 수 없었음 
	 *  사용법 : HumanServiceImpl 82
	 */
	
	//@@@@Query 어노테이션 사용하기@@@@
	//@Query("SELECT h FROM human AS h WHERE h.name = ?1")
	//List<Human> findByName(String name);
	// 위에 커리를 사용시 가동성 상승, 단 쿼리의 순서가 바뀐 경우 에러 발생 가능, AS 생략 가능
	
	//@Query("SELECT h FROM human h WHERE h.name = :name")
	//List<Human> findByNameParma(@Param("name") String name);
	// List<Human> findByName(String name); 의 순서가 바뀔수 있는 부분 보안됨
	
	
	//@@@@QueryDSL의 장점@@@@
	/*
	 * 메서드의 이름을 기반으로 생성하는 JPQL의 한계는 @query 어노테이션을 통해 대부분 해소할수 있지만 직접 문자열
	 * 을 입력하기 때문에 컴파일 시점에 에러를 잡지 못하고 런타임 에러가 발생할 수 있다.
	 * 
	 * 이 같은 문제를 해결하기 위한 것이 QueryDSL입니다. QueryDSL은 문자열이 아니라 코드로 작성할 수 있도록 도와줍니다.
	 */
	
	
	
	
}
