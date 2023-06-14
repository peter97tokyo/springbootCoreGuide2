package com.springboot.jpa.data.dto;

public class HumanResponseDto {
	private Long number;
	private String name;
	private int age;
	private String jop;
	public HumanResponseDto(Long number, String name, int age, String jop) {
		super();
		this.number = number;
		this.name = name;
		this.age = age;
		this.jop = jop;
	}
	public HumanResponseDto() {
		super();
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getJop() {
		return jop;
	}
	public void setJop(String jop) {
		this.jop = jop;
	}
}
