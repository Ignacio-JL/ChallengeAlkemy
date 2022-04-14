package com.Alkemy.Challenge.service;

import java.util.List;

import com.Alkemy.Challenge.dto.MovieBasicDTO;
import com.Alkemy.Challenge.dto.MovieDTO;

public interface MovieService {

	List<MovieBasicDTO> getMovies ();
	
	List<MovieDTO> getByFilters(String name, String gender, String order);
	
	MovieDTO save (MovieDTO dto);
	
	MovieDTO update (MovieDTO dto);
	
	void delete (Long id);

	
}
