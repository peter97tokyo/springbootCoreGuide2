package com.springboot.jpa;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestLifeCycle {
	
	@BeforeAll // 테스트를 시작하기 전에 호출되는 메서드를 정의
	static void beforeAll() {
		System.out.println("@BeforeAll");
		System.out.println();
	}
	@AfterAll // 테스트를 종료하면서 호출되는 메서드를 정의 
	static void afterAll() {
		System.out.println("@AfterAll");
		System.out.println();
	}
	@BeforeEach // 각 테스트 메서드가 실행되기 전에 동작하는 메서드를 정의 
	void beforeEach() {
		System.out.println("@BeforeEach");
		System.out.println();
	}
	@AfterEach // 각 테스트 메서드가 종료되면서 호출되는 메서드를 정의
	void afterEach() {
		System.out.println("@AfterEach");
		System.out.println();
	}
	@Test // 테스트 코드를 포함한 메서드를 정의
	void test1() {
		System.out.println("test1");
		System.out.println();
	}
	@Test
	@DisplayName("Test Case 2!!!")
	void test2() {
		System.out.println("test2");
		System.out.println();
	}
	@Test
	@Disabled
	void test3() {
		System.out.println("test3");
		System.out.println();
	}
}
