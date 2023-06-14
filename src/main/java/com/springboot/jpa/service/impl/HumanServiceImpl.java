package com.springboot.jpa.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jpa.data.dto.HumanDto;
import com.springboot.jpa.data.dto.HumanResponseDto;
import com.springboot.jpa.data.entity.Human;
import com.springboot.jpa.data.repository.HumanRepository;
import com.springboot.jpa.service.HumanService;

@Service
public class HumanServiceImpl implements HumanService{
	
	private final Logger LOGGER = LoggerFactory.getLogger(HumanServiceImpl.class);
	private final HumanRepository humanRepository;
	
	@Autowired
	public HumanServiceImpl(HumanRepository humanRepository) {
		this.humanRepository = humanRepository;
	}
	
	@Override
	public HumanResponseDto getHuman(Long number) {
		LOGGER.info("[getHuman] input number {}", number);
		Human human = humanRepository.findById(number).get();
		
		LOGGER.info("[getHuman] human number : {}, name ", human.getNumber(), human.getName());
		HumanResponseDto humanResponseDto = new HumanResponseDto();
		humanResponseDto.setNumber(number);
		humanResponseDto.setName(human.getName());
		humanResponseDto.setAge(human.getAge());
		humanResponseDto.setJop(human.getJob());
		
		return humanResponseDto;
	}
	@Override
	public HumanResponseDto saveHuman(HumanDto humanDto) {
		LOGGER.info("[saveHuman] humanDto {}", humanDto.toString());
		Human human = new Human();
		human.setName(humanDto.getName());
		human.setAge(humanDto.getAge());
		human.setJob(humanDto.getJob());
		human.setBirth(LocalDateTime.now());
		Human savedHuman = humanRepository.save(human);
		LOGGER.info("[savedHuman] savedHuman {}", savedHuman);
		HumanResponseDto humanResponseDto = new HumanResponseDto();
		humanResponseDto.setNumber(savedHuman.getNumber());
		humanResponseDto.setName(savedHuman.getName());
		humanResponseDto.setAge(savedHuman.getAge());
		humanResponseDto.setJop(savedHuman.getJob());
		
		return humanResponseDto;
	}
	@Override
	public HumanResponseDto changeHumanJob(Long number, String job) throws Exception{
		Human foundHuman = humanRepository.findById(number).get();
		foundHuman.setJob(job);
		foundHuman.setChangeDt(LocalDateTime.now());
		Human changedHuman= humanRepository.save(foundHuman);
		
		HumanResponseDto humanResponseDto = new HumanResponseDto();
		humanResponseDto.setNumber(changedHuman.getNumber());
		humanResponseDto.setName(changedHuman.getName());
		humanResponseDto.setAge(changedHuman.getAge());
		humanResponseDto.setJop(changedHuman.getJob());
		
		return humanResponseDto;
	}
	@Override
	public void deleteHuman(Long number) throws Exception{
		humanRepository.deleteById(number);
		
	}
}
