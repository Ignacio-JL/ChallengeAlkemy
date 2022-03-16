package com.Alkemy.Challenge.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "characters")
@Getter
@Setter
public class CharacterEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String image;
	
	private String name;
	
	private int age;
	
	private int weight;
	
	private String history;
	
	@ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL)
	private List<MovieEntity> movies = new ArrayList<>();
}
