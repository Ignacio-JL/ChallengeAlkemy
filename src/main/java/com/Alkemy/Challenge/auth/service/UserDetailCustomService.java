package com.Alkemy.Challenge.auth.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Alkemy.Challenge.auth.dto.UserDTO;
import com.Alkemy.Challenge.auth.entity.RoleEntity;
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
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserEntity> userEntity = userRepository.findByEmail(email);
		if (userEntity.isEmpty()) {
			throw new UsernameNotFoundException("User or password not found");
		}
		return new User(userEntity.get().getEmail(), userEntity.get().getPassword(), Collections.emptyList());
	}
	
	private Collection<? extends GrantedAuthority> mappRoles(Set<RoleEntity> roles){
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
	}
	
	public Boolean save(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName(userDTO.getFirstName());
		userEntity.setLastName(userDTO.getLastName());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setPassword(userDTO.getPassword());
		userEntity = this.userRepository.save(userEntity);
		if(userEntity != null) {
			emailService.sendWelcomeEmailTo(userEntity.getEmail());
		}
		return userEntity != null;
	}
	
}
