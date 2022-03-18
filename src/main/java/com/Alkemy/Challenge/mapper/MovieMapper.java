package com.Alkemy.Challenge.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.Alkemy.Challenge.dto.CharacterDTO;
import com.Alkemy.Challenge.dto.MovieBasicDTO;
import com.Alkemy.Challenge.dto.MovieDTO;
import com.Alkemy.Challenge.entity.CharacterEntity;
import com.Alkemy.Challenge.entity.MovieEntity;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MovieMapper {

	private final CharacterMapper characterMapper;
	
	public MovieEntity movieDTO2Entity(MovieDTO dto, boolean loadCharacters) {
		MovieEntity entity = new MovieEntity();
		entity.setImage(dto.getImage());
		entity.setTitle(dto.getTitle());
		entity.setCreationDate(this.string2LocalDate(dto.getCreationDate()));
		entity.setWeight(dto.getWeight());
		entity.setHistory(dto.getHistory());
		entity.setQualification(dto.getQualification());
		entity.setGender(dto.getGender());
		entity.setGenderId(dto.getGenderId());
		if(loadCharacters) {
			Set<CharacterEntity> characterEntityList = this.characterMapper.characterDTOList2Entity(dto.getCharacters());
			entity.setCharacters(characterEntityList);
		}
		
		return entity;
	}
	
	public MovieDTO movieEntity2DTO(MovieEntity entity, boolean loadCharacters) {
		MovieDTO dto = new MovieDTO();
		dto.setId(entity.getId());
		dto.setImage(entity.getImage());
		dto.setTitle(entity.getTitle());
		dto.setCreationDate(entity.getCreationDate().toString());
		dto.setWeight(entity.getWeight());
		dto.setHistory(entity.getHistory());
		dto.setQualification(entity.getQualification());
		dto.setGender(entity.getGender());
		dto.setGenderId(entity.getGenderId());
		if(loadCharacters) {
			List<CharacterDTO> charactersDTO = this.characterMapper.characterEntityList2DTOList(entity.getCharacters(), loadCharacters);
			dto.setCharacters(charactersDTO);
		}
		
		return dto;
	}
	
	public LocalDate string2LocalDate(String stringDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate result = LocalDate.parse(stringDate, formatter);
		
		return result;
	}
	
	
	//se implementa en characterEntity2DTO
	public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities, boolean loadCharacters){
		List<MovieDTO> dtos = new ArrayList<>();
		for (MovieEntity entity : entities) {
			dtos.add(this.movieEntity2DTO(entity, loadCharacters));
		}
		
		return dtos;
	}
	
//	se implementa en characterDTO2Entity
	public List<MovieEntity> movieDTOList2Entity(List<MovieDTO> dtos){
		List<MovieEntity> entities = new ArrayList<>();
		
		for (MovieDTO dto : dtos) {
			entities.add(this.movieDTO2Entity(dto, false));//FALSE...
		}
		
		return entities;
	}
	
	//mostrar el listado de peliculas, ya que no mostraremos todos los datos
	public List<MovieBasicDTO> movieEntitySet2BasicDTOList(List<MovieEntity> entities) {
		List<MovieBasicDTO> dtos = new ArrayList<>();
		MovieBasicDTO basicDTO;
		for (MovieEntity entity : entities) {
			basicDTO = new MovieBasicDTO();
			basicDTO.setId(entity.getId());
			basicDTO.setImage(entity.getImage());
			basicDTO.setTitle(entity.getTitle());
			basicDTO.setCreationDate(entity.getCreationDate().toString());
		}
		
		return dtos;
	}
}
