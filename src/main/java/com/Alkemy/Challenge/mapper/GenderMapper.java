package com.Alkemy.Challenge.mapper;

import org.springframework.stereotype.Component;

import com.Alkemy.Challenge.dto.GenderDTO;
import com.Alkemy.Challenge.entity.GenderEntity;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class GenderMapper {

	public GenderEntity genderDTO2Entity (GenderDTO dto) {
		GenderEntity entity = new GenderEntity();
		entity.setName(dto.getName());
		entity.setImage(dto.getImage());
		
		return entity;
	}
	
	public GenderDTO genderEntity2DTO(GenderEntity entity) {
		GenderDTO dto = new GenderDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setImage(entity.getImage());
		
		return dto;
		 
	}
}
