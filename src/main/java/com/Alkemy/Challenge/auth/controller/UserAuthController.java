package com.Alkemy.Challenge.auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Alkemy.Challenge.auth.dto.AuthenticationRequest;
import com.Alkemy.Challenge.auth.dto.AuthenticationResponse;
import com.Alkemy.Challenge.auth.dto.UserDTO;
import com.Alkemy.Challenge.auth.security.JwtTokenProvider;
import com.Alkemy.Challenge.auth.service.UserDetailCustomService;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailCustomService userDetailCustomService;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	/*@Autowired
	public UserAuthController(
			UserDetailCustomService userDetailCustomService,
			AuthenticationManager authenticationManager,
			JwtTokenProvider jwtTokenProvider) {
		this.userDetailCustomService = userDetailCustomService;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}*/
	
	@PostMapping("/signup")
	public ResponseEntity<AuthenticationResponse> signUp(@Valid @RequestBody UserDTO dto) throws Exception{
		this.userDetailCustomService.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthenticationResponse> signIn(@RequestBody AuthenticationRequest authRequest)throws Exception{
		
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
				);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		final String jwt = jwtTokenProvider.generateToken(auth);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
