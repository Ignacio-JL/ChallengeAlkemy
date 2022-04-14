package com.Alkemy.Challenge.dto;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class MovieFiltersDTO {
	
	private String name;
	private String gender;
	private String order;
	
	public MovieFiltersDTO(String name, String gender, String order) {
		this.name = name;
		this.gender = gender;
		this.order = order;
	}
	public boolean isDESC() {
		return this.order.compareToIgnoreCase("DESC") == 0;
	}
}
