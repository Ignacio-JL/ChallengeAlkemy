package com.Alkemy.Challenge.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Alkemy.Challenge.auth.entity.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	public Optional<UserEntity>findByEmail(String email);
	
	public Boolean existsByEmail(String email);
}
