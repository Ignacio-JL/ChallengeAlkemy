package com.Alkemy.Challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Alkemy.Challenge.entity.GenderEntity;

@Repository
public interface GenderRepository extends JpaRepository<GenderEntity, Long>{

}
