package com.Alkemy.Challenge.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.Alkemy.Challenge.dto.CharacterBasicDTO;
import com.Alkemy.Challenge.dto.CharacterDTO;
import com.Alkemy.Challenge.dto.MovieDTO;
import com.Alkemy.Challenge.entity.CharacterEntity;
import com.Alkemy.Challenge.entity.MovieEntity;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CharacterMapper {
	
	private final MovieMapper movieMapper;
//	para guardar un personaje envia un entity
	public CharacterEntity characterDTO2Entity(CharacterDTO dto, boolean loadMovies) {
		CharacterEntity entity = new CharacterEntity();
		entity.setImage(dto.getImage());
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		entity.setWeight(dto.getWeight());
		entity.setHistory(dto.getHistory());
		if (loadMovies) {
			List<MovieEntity> movieEntityList = this.movieMapper.movieDTOList2Entity(dto.getMovies());
			entity.setMovies(movieEntityList);
		}
		return entity;
	}
//	para mostrar un personaje, debe mostrar un dto y no entity
	public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadMovies) {
		CharacterDTO dto = new CharacterDTO();
		dto.setId(entity.getId());
		dto.setImage(entity.getImage());
		dto.setName(entity.getName());
		dto.setAge(entity.getAge());
		dto.setWeight(entity.getWeight());
		dto.setHistory(entity.getHistory());
		if (loadMovies) {
			List<MovieDTO> moviesDTO = this.movieMapper.movieEntityList2DTOList(entity.getMovies(), loadMovies);
			dto.setMovies(moviesDTO);
		} 
		
		return dto;
	}
	
	
//	se implementa en movieDTO2Entity
	public Set<CharacterEntity> characterDTOList2Entity(List<CharacterDTO> dtos){
		Set<CharacterEntity> entites = new HashSet<>();
		for (CharacterDTO dto : dtos) {
			entites.add(characterDTO2Entity(dto, false));
		}
		return entites;
	}
	
	//se implementa en movieEntity2DTO
	public List<CharacterDTO> characterEntityList2DTOList(Set<CharacterEntity> entities, boolean loanMovies){
		List<CharacterDTO> dtos = new ArrayList<>();
		for (CharacterEntity entity : entities) {
			dtos.add(this.characterEntity2DTO(entity, loanMovies));
		}
		
		return dtos;
	}

	//devuelve todos los personajes para CharacterServices(habria que hacer uno similar para MovieServices), mas seguro usemos basicDTO
	public List<CharacterDTO> characterEntitySet2DTOList(List<CharacterEntity> entities, boolean loadMovies) {
		List<CharacterDTO> dtos = new ArrayList<>();
		for (CharacterEntity entity : entities) {
			dtos.add(this.characterEntity2DTO(entity, loadMovies));
		}
		return dtos;
	}
	// mostrar el listado de peliculas, ya que no mostraremos todos los datos
	public List<CharacterBasicDTO> characterEntitySet2BasicDTOList(List<CharacterEntity> entities){
		List<CharacterBasicDTO> dtos = new ArrayList<>();
		CharacterBasicDTO basicDTO;
		for (CharacterEntity entity: entities) {
			basicDTO = new CharacterBasicDTO();
			basicDTO.setId(entity.getId());
			basicDTO.setImage(entity.getImage());
			basicDTO.setName(entity.getName());
			dtos.add(basicDTO);
		}
		
		return dtos;
	}
	
	//update
	public void characterEntityRefreshValues(CharacterEntity entity, CharacterDTO dto) {
		entity.setImage(dto.getImage());
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		entity.setWeight(dto.getWeight());
		entity.setHistory(dto.getHistory());
	}
}
