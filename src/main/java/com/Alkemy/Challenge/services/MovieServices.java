package com.Alkemy.Challenge.services;

import java.util.List;

import com.Alkemy.Challenge.dto.MovieBasicDTO;
import com.Alkemy.Challenge.dto.MovieDTO;

public interface MovieServices {

	public List<MovieBasicDTO> getMovies();
	
	public MovieDTO save(MovieDTO dto);
	
	public MovieDTO update(MovieDTO dto);
	
	public void delete(Long id);
}
