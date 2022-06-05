package com.Alkemy.Challenge.auth.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.Alkemy.Challenge.auth.entity.RoleEntity;

@Repository
public interface RoleRepository {
	
	public Optional<RoleEntity> findByName(String name);
}
