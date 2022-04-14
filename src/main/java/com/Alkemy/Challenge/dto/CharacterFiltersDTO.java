package com.Alkemy.Challenge.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterFiltersDTO {

	private String name;

	private String age;

	private Set<Long> movies;
	
	public CharacterFiltersDTO(String name, String age, Set<Long> movies) {
		this.name = name;
		this.age = age;
		this.movies = movies;
	}
}
