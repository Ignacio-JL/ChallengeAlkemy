package com.Alkemy.Challenge.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Alkemy.Challenge.dto.CharacterBasicDTO;
import com.Alkemy.Challenge.dto.CharacterDTO;
import com.Alkemy.Challenge.service.CharacterService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/characters")
public class CharacterController {
	
	private final CharacterService characterService;
	
	@GetMapping("/all")
	public ResponseEntity<List<CharacterBasicDTO>> getAll(){
		List<CharacterBasicDTO> characters = characterService.getCharacters();
		return ResponseEntity.status(HttpStatus.OK).body(characters);
	}
	
	@GetMapping()
	public ResponseEntity<List<CharacterDTO>> getByFilters(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String age,
			@RequestParam(required = false) Set<Long> movies
			){
		List<CharacterDTO> characters = characterService.getByFilters(name, age, movies);
		
		return ResponseEntity.ok(characters);
	}
	
	@PostMapping
	public ResponseEntity<CharacterDTO> save(@Valid @RequestBody CharacterDTO character){
		CharacterDTO characterSaved = characterService.save(character);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
	}
	
	@PutMapping
	public ResponseEntity<CharacterDTO> update(@Valid @RequestBody CharacterDTO character){
		CharacterDTO characterUpdated = characterService.update(character);
		
		return ResponseEntity.status(HttpStatus.OK).body(characterUpdated);
		//DESPUES PROBAR return new ResponseEntity<CharacterDTO>(characterUpdated, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id){
		characterService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();		
	}

}
