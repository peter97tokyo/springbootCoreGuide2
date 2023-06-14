package com.springboot.jpa.service;

import com.springboot.jpa.data.dto.HumanDto;
import com.springboot.jpa.data.dto.HumanResponseDto;

public interface HumanService {
	HumanResponseDto getHuman(Long number);
	HumanResponseDto saveHuman(HumanDto humanDto);
	HumanResponseDto changeHumanJob(Long number, String job) throws Exception;
	void deleteHuman(Long number)throws Exception;
}
