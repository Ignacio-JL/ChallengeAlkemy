package com.Alkemy.Challenge.service;

import java.util.List;
import java.util.Set;

import com.Alkemy.Challenge.dto.CharacterBasicDTO;
import com.Alkemy.Challenge.dto.CharacterDTO;


public interface CharacterService{

	List<CharacterBasicDTO> getCharacters();
	
	List<CharacterDTO> getByFilters(String name, String age, Set<Long> movies);
	
	CharacterDTO save(CharacterDTO dto);
	
	CharacterDTO update(CharacterDTO dto);
	
	void delete(Long id);
}
