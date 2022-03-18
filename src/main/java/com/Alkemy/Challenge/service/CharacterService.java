package com.Alkemy.Challenge.service;

import java.util.List;

import com.Alkemy.Challenge.dto.CharacterBasicDTO;
import com.Alkemy.Challenge.dto.CharacterDTO;


public interface CharacterService{

	List<CharacterBasicDTO> getCharacters();
	
	CharacterDTO save(CharacterDTO dto);
	
	CharacterDTO update(CharacterDTO dto);
	
	void delete(Long id);
}
