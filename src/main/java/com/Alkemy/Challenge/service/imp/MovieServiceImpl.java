package com.Alkemy.Challenge.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Alkemy.Challenge.dto.MovieBasicDTO;
import com.Alkemy.Challenge.dto.MovieDTO;
import com.Alkemy.Challenge.entity.MovieEntity;
import com.Alkemy.Challenge.exception.ParamNotFound;
import com.Alkemy.Challenge.mapper.MovieMapper;
import com.Alkemy.Challenge.repository.MovieRepostory;
import com.Alkemy.Challenge.service.MovieService;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService{

	private final MovieRepostory movieRepostory;
	private final MovieMapper movieMapper;
	
	@Override
	public List<MovieBasicDTO> getMovies() {
		List<MovieEntity> entities = movieRepostory.findAll();
		
		return movieMapper.movieEntitySet2BasicDTOList(entities);
	}

	@Override
	public MovieDTO save(MovieDTO dto) {
		MovieEntity entity = movieMapper.movieDTO2Entity(dto, true);
		MovieEntity entitySaved = movieRepostory.save(entity);
		
		return movieMapper.movieEntity2DTO(entitySaved, true);
	}

	@Override
	public MovieDTO update(MovieDTO dto) {
		Optional<MovieEntity> movie2Update = movieRepostory.findById(dto.getId());
		if(movie2Update.isEmpty()) {
			throw new ParamNotFound("Id ingresado no encontrado");
		}
		movieMapper.movieEntityRefreshValues(movie2Update.get(), dto);
		MovieEntity movieSaved = movieRepostory.save(movie2Update.get());
		
		return movieMapper.movieEntity2DTO(movieSaved, false);
	}

	@Override
	public void delete(Long id) {
		if(movieRepostory.findById(id).isEmpty()) {
			throw new ParamNotFound("Id ingresado no encontrado");
		}
		movieRepostory.deleteById(id);
		
	}

}
