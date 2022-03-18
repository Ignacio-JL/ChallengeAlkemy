package com.Alkemy.Challenge.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDTO {
	
	private Long id;
	
	private String image;
	
	private String name;
	
	private int age;
	
	private int weight;
	
	private String history;
	
	private List<MovieDTO> movies = new ArrayList<>();
}
