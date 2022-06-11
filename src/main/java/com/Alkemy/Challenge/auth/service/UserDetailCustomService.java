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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Alkemy.Challenge.auth.dto.UserDTO;
import com.Alkemy.Challenge.auth.entity.RoleEntity;
import com.Alkemy.Challenge.auth.entity.UserEntity;
import com.Alkemy.Challenge.auth.repository.RoleRepository;
import com.Alkemy.Challenge.auth.repository.UserRepository;
import com.Alkemy.Challenge.exception.EmailAlreadyExistException;
import com.Alkemy.Challenge.service.EmailService;

@Service
public class UserDetailCustomService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		Nuestro username sera nuestro email
		UserEntity userEntity = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario/email no encontrado"));
		
		return new User(userEntity.getEmail(), userEntity.getPassword(), mappRoles(userEntity.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mappRoles(Set<RoleEntity> roles){
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
	}
	
	public Boolean save(UserDTO userDTO) {
		if(userRepository.existsByEmail(userDTO.getEmail())) {
			throw new EmailAlreadyExistException(userDTO.getEmail());
		}
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName(userDTO.getFirstName());
		userEntity.setLastName(userDTO.getLastName());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		RoleEntity roles = roleRepository.findByName("ROLE_ADMIN").get();
		userEntity.setRoles(Collections.singleton(roles));
		
		userEntity = this.userRepository.save(userEntity);
		if(userEntity != null) {
			emailService.sendWelcomeEmailTo(userEntity.getEmail());
		}
		return userEntity != null;
	}
	
}
