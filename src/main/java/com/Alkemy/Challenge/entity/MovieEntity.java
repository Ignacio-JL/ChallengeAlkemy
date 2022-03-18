package com.Alkemy.Challenge.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "movie")
@Getter
@Setter
@SQLDelete(sql = "UPDATE movie SET deleted = true WHERE id=?")
public class MovieEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String image;
	
	private String title;
	@Column(name = "creation_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate creationDate;
	
	private int weight;
	
	private String history;
	
	private int qualification;
	
	private boolean deleted = Boolean.FALSE;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "character_movie",
			joinColumns = @JoinColumn(name = "movie_id"),
			inverseJoinColumns = @JoinColumn(name = "character_id"))
	private Set<CharacterEntity> characters = new HashSet<>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gender_id", insertable = false, updatable = false)
	private GenderEntity gender;
	
	@Column(name = "gender_id", nullable = false)
	private Long genderId;

}
