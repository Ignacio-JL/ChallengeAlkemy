package com.Alkemy.Challenge.auth.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Alkemy.Challenge.auth.dto.UserDTO;
import com.Alkemy.Challenge.auth.entity.UserEntity;
import com.Alkemy.Challenge.auth.repository.UserRepository;
import com.Alkemy.Challenge.service.EmailService;

@Service
public class UserDetailCustomService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailService emailService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUsername(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException("Username or password not found");
		}
		return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
	}
	
	public Boolean save(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(userDTO.getUsername());
		userEntity.setPassword(userDTO.getPassword());
		userEntity = this.userRepository.save(userEntity);
		if(userEntity != null) {
			emailService.sendWelcomeEmailTo(userEntity.getUsername());
		}
		return userEntity != null;
	}
	
}
