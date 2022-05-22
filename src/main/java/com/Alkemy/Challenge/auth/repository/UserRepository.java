package com.Alkemy.Challenge.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Alkemy.Challenge.auth.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findByUsername(String username);
}
