package com.Alkemy.Challenge.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Alkemy.Challenge.entity.MovieEntity;

@Repository
public interface MovieRepostory extends JpaRepository<MovieEntity, Long>{
	
	List<MovieEntity> findAll(Specification<MovieEntity> spec);
}
