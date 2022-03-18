package com.Alkemy.Challenge.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Alkemy.Challenge.dto.CharacterBasicDTO;
import com.Alkemy.Challenge.dto.CharacterDTO;
import com.Alkemy.Challenge.entity.CharacterEntity;
import com.Alkemy.Challenge.mapper.CharacterMapper;
import com.Alkemy.Challenge.repository.CharacterRepository;
import com.Alkemy.Challenge.service.CharacterService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CharacterServiceImp implements CharacterService{
	
	private final CharacterRepository characterRepository;

	private final CharacterMapper characterMapper;
	
	@Override
	public List<CharacterBasicDTO> getCharacters() {
		List<CharacterEntity> entity = characterRepository.findAll();
		
		List<CharacterBasicDTO> dtoBasicList = characterMapper.characterEntitySet2BasicDTOList(entity);
		
		
		return dtoBasicList;
	}

	@Override
	public CharacterDTO save(CharacterDTO dto) {

		CharacterEntity entity = characterMapper.characterDTO2Entity(dto, false);
		CharacterEntity entitySaved = characterRepository.save(entity);

		return characterMapper.characterEntity2DTO(entitySaved, false);
	}

	@Override
	public void delete(Long id) {

		characterRepository.deleteById(id);
	}

	@Override
	public CharacterDTO update(CharacterDTO dto) {
		
		Optional<CharacterEntity> character2Update = characterRepository.findById(dto.getId());
		characterMapper.characterEntityRefreshValues(character2Update.get(), dto);
		CharacterEntity characterSaved = characterRepository.save(character2Update.get());
		
		return characterMapper.characterEntity2DTO(characterSaved, true);
	}

}
