package com.Alkemy.Challenge.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Alkemy.Challenge.dto.MovieBasicDTO;
import com.Alkemy.Challenge.services.MovieServices;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {
	
	private final MovieServices movieServices;
	
	@GetMapping
	public ResponseEntity<List<MovieBasicDTO>> getAll(){
		//List<MovieBasicDTO> movies =
		return null;
	}
	

}
