package com.Alkemy.Challenge.dto;

import java.util.ArrayList;
import java.util.List;

import com.Alkemy.Challenge.entity.GenderEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {

	private Long id;
	
	private String image;
	
	private String title;

	private String creationDate;
	
	private int weight;
	
	private String history;
	
	private int qualification;
	
	private List<CharacterDTO> characters = new ArrayList<>();
	
	private GenderEntity gender;
	
	private Long genderId;
}
