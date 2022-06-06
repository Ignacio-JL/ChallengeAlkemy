package com.Alkemy.Challenge.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Alkemy.Challenge.dto.MovieBasicDTO;
import com.Alkemy.Challenge.dto.MovieDTO;
import com.Alkemy.Challenge.service.MovieService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {
	
	private final MovieService movieService;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<MovieBasicDTO>> getAll(){
		List<MovieBasicDTO> movies = movieService.getMovies();
		return ResponseEntity.status(HttpStatus.OK).body(movies);
	}
	
	@GetMapping
	public ResponseEntity<List<MovieDTO>> getByFilter(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String gender,
			@RequestParam(required = false, defaultValue = "ASC") String order
			){
		List<MovieDTO> movies = movieService.getByFilters(name, gender, order);
		return ResponseEntity.ok(movies);
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<MovieDTO> getDetail (@PathVariable Long id){
		MovieDTO movie = movieService.getById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(movie);
	}
	
	@PostMapping 
	public ResponseEntity<MovieDTO> save(@Valid @RequestBody MovieDTO dto){
		MovieDTO movieSaved = movieService.save(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
	}
	
	@PutMapping
	public ResponseEntity<MovieDTO> update(@Valid @RequestBody MovieDTO dto){
		MovieDTO movieUpdated = movieService.update(dto);
		
		return ResponseEntity.status(HttpStatus.OK).body(movieUpdated);
	}

	@DeleteMapping("/{id}") 
	public ResponseEntity<Void> delete(@PathVariable Long id){
		movieService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
