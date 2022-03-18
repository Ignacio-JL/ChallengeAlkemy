package com.Alkemy.Challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Alkemy.Challenge.entity.CharacterEntity;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long>{

}
