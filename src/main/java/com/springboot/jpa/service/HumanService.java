package com.springboot.jpa.service;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.springboot.jpa.data.dto.HumanDto;
import com.springboot.jpa.data.dto.HumanResponseDto;
import com.springboot.jpa.data.entity.Human;

public interface HumanService {
	HumanResponseDto getHuman(Long number);
	HumanResponseDto saveHuman(HumanDto humanDto);
	HumanResponseDto changeHumanJob(Long number, String job) throws Exception;
	void deleteHuman(Long number)throws Exception;
	String getHumanPageable(String name)throws Exception;
}
