package com.Alkemy.Challenge.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Alkemy.Challenge.dto.GenderDTO;
import com.Alkemy.Challenge.service.GenderService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/genders")
public class GenderController {

	private final GenderService genderService;
	
	@PostMapping
	public ResponseEntity<GenderDTO> save(@RequestBody GenderDTO dto){
		GenderDTO genderSaved = genderService.save(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(genderSaved);
	}
}
