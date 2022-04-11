package com.Alkemy.Challenge.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDTO {
	
	private Long id;
	@NotBlank(message = "{La imagen es obligatoria}")
	@Size(max = 60, message = "{Max 60 caracteres}")
	private String image;
	@NotBlank(message = "{El nombre es obligatorio}")
	@Size(max = 100, message = "{Max 100 caracteres}")
	private String name;
	@Min(value = 4, message = "{valor minimo 4}")
	@Max(value = 200, message = "{valor max 200}")
	private int age;
	
	private int weight;
	
	private String history;
	
	private List<MovieDTO> movies = new ArrayList<>();
}
