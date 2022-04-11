package com.Alkemy.Challenge.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.Alkemy.Challenge.dto.CharacterBasicDTO;
import com.Alkemy.Challenge.dto.CharacterDTO;
import com.Alkemy.Challenge.dto.CharacterFiltersDTO;
import com.Alkemy.Challenge.entity.CharacterEntity;
import com.Alkemy.Challenge.exception.ParamNotFound;
import com.Alkemy.Challenge.mapper.CharacterMapper;
import com.Alkemy.Challenge.repository.CharacterRepository;
import com.Alkemy.Challenge.repository.specifications.CharacterSpecification;
import com.Alkemy.Challenge.service.CharacterService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CharacterServiceImpl implements CharacterService{
	
	private final CharacterRepository characterRepository;
	
	private final CharacterSpecification characterSpecification;

	private final CharacterMapper characterMapper;
	
	@Override
	public List<CharacterBasicDTO> getCharacters() {
		List<CharacterEntity> entity = characterRepository.findAll();
		
		List<CharacterBasicDTO> dtoBasicList = characterMapper.characterEntitySet2BasicDTOList(entity);
		
		
		return dtoBasicList;
	}
	
	@Override
	public List<CharacterDTO> getByFilters(String name, String age, Set<Long> movies) {
		CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, movies);
		List<CharacterEntity> entities = characterRepository.findAll(characterSpecification.getByFilters(filtersDTO));
		List<CharacterDTO> dtos = characterMapper.characterEntitySet2DTOList(entities, true); 
		return dtos;
	}

	@Override
	public CharacterDTO save(CharacterDTO dto) {

		CharacterEntity entity = characterMapper.characterDTO2Entity(dto, false);
		CharacterEntity entitySaved = characterRepository.save(entity);

		return characterMapper.characterEntity2DTO(entitySaved, false);
	}

	@Override
	public void delete(Long id) {
		if(characterRepository.findById(id).isEmpty()){
			throw new ParamNotFound("Id no encontrado");
		}
		characterRepository.deleteById(id);
		
		
	}

	@Override
	public CharacterDTO update(CharacterDTO dto) {
		
		Optional<CharacterEntity> character2Update = characterRepository.findById(dto.getId());
		if(character2Update.isEmpty()) {
			throw new ParamNotFound("Id no encontrado");			
		}
		characterMapper.characterEntityRefreshValues(character2Update.get(), dto);
		CharacterEntity characterSaved = characterRepository.save(character2Update.get());
		
		return characterMapper.characterEntity2DTO(characterSaved, true);
	}


}
