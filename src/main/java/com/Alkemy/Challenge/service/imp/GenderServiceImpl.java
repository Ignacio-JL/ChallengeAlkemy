package com.Alkemy.Challenge.service.imp;

import org.springframework.stereotype.Service;

import com.Alkemy.Challenge.dto.GenderDTO;
import com.Alkemy.Challenge.entity.GenderEntity;
import com.Alkemy.Challenge.mapper.GenderMapper;
import com.Alkemy.Challenge.repository.GenderRepository;
import com.Alkemy.Challenge.service.GenderService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GenderServiceImpl implements GenderService{

	private GenderRepository genderRepository;
	private GenderMapper genderMapper;

	@Override
	public GenderDTO save(GenderDTO dto) {
		GenderEntity entity = genderMapper.genderDTO2Entity(dto);
		GenderEntity entitySaved = genderRepository.save(entity);
		
		return genderMapper.genderEntity2DTO(entitySaved);
	}
	
	
}
