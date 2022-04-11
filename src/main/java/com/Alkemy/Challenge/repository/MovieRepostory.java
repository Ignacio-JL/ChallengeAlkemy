package com.Alkemy.Challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Alkemy.Challenge.entity.MovieEntity;

@Repository
public interface MovieRepostory extends JpaRepository<MovieEntity, Long>{

}
