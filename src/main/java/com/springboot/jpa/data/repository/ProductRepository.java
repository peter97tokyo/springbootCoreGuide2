package com.springboot.jpa.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jpa.data.entity.Product;
// JpaRepository은 Entity로 만들어진 데이터베이스에 접근하는데 사용
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	// 08 Spring Data JPA
	
	/*
	 * JPQL은 JPA Query Language의 줄임말로 JPA에서 사용할 수 있는 쿼리를 의미합니다.
	 * 엔티티 객체를 대상으로 수행하는 쿼리이기 때문에 매핑된 엔티티의 이름과 필드의 이름을 사용합니다.
	 */
	
	/*
	 * 쿼리 메서드는 크게 동작을 결정하는 주제(subject)와 서술어(Predicate)로 구분합니다. find...by, exists...by
	 * 와 같은 키워드로 쿼리의 주제를 정하며 by는 서술어의 시작을 나타내는 구분자 역할을 합니다. 서술어 부분은 검색 및
	 * 정렬 조건을 지정하는 영역입니다. 기본적으로 엔티티 속성으로 정의할 수 있고, AND나 OR를 사용해 조건을 확장하는 것도
	 * 가능합니다.
	 * 
	 * 조회하는 기능의 키워드입니다.  ...으로 표시하 영역에는 도메(엔티티)을 표현할 수 있습니다.
	 * 그러나 리포지토리에서 이미 도메인을 설정한 후에 메서드를 사용하기 때문에 중복으로 판단해 생략 하기도 합니다.
	 * 리턴타입으로는 Collection이나 Stream에 속한 하위 타입을 설정할 수 있습니다.
	 * get...By
	 * 
	 * query...By
	 * 
	 * search...By
	 * 
	 * stream...By
	 */
	
	// find...by
	Optional<Product> findByNumber(Long number);
	// (return type) + {주제 + 서술어(속성)} 구조의 메서드
 	List<Product> findAllByName(String name);

	Product queryByNumber(Long number);
	
	// exists...by
	boolean existsByNumber(Long number);
	// 특정 데이터가 존재하는지에 대한 여부 리턴타입으로 boolean을 사용함
	
	// count...by
	Long countByName(String name);
	// 조회 후 쿼리 결과로 나온 레코드의 개수를 리턴합니다.
	
	// delete...by, remove...by
	void deleteByNumber(Long number);
	Long removeByNumber(Long number);
	// 삭제를 실행 리턴타입이 없거나 삭제한 횟수를 리턴합니다.
	
	// first...by, top...by
	List<Product> findFirst5ByName(String name);
	// (return type) + {주제 + first<숫자>by + 서술어(속성)} 구조의 메서드
	List<Product> findTop10ByName(String name);
	// 쿼리를 통해 조회되는 개수를 제한하는 쿼리입니다. 처음부터 5개까지, 
	// 위에서 10까지 그리고 단건으로 조회하기 위해서는 숫자를 생략하면 됨
	
	// (Is)Not
	Product findByNumberIsNot(Long number);
	Product findByNumberNot(Long number);
	// 값의 불일치를 조건으로 조회하는 기능, is는 생략가능
	
	// (Is)Null, (Is)NotNull
	List<Product> findByUpdateAtNull();
	List<Product> findByUpdateAtIsNull();
	List<Product> findByUpdateAtNotNull();
	List<Product> findByUpdateAtIsNotNull();
	// 값이 null인지 검사하는 조건자 키워드
	
	//(Is)True, (Is)False
	/*
	List<Product> findByIsActiveTrue();
	List<Product> findByIsActiveIsTrue();
	List<Product> findByIsActiveFalse();
	List<Product> findByIsActiveIsFalse();
	*/
	// boolean 타입으로 지정된 컬럼값을 확인하는 키워드입니다. Product 엔티티에 boolean 타입을 사용하는
	// 컬럼(Active) 이 없기때문에 실제로 사용하는 경우 에러가 발생합니다.
}

 