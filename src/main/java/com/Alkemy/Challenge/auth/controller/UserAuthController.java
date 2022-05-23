package com.Alkemy.Challenge.auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Alkemy.Challenge.auth.dto.AuthenticationRequest;
import com.Alkemy.Challenge.auth.dto.AuthenticationResponse;
import com.Alkemy.Challenge.auth.dto.UserDTO;
import com.Alkemy.Challenge.auth.service.JwtUtils;
import com.Alkemy.Challenge.auth.service.UserDetailCustomService;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
	
	private UserDetailCustomService userDetailCustomService;
	private AuthenticationManager authenticationManager;
	private JwtUtils jwtUtil;
	
	@Autowired
	public UserAuthController(
			UserDetailCustomService userDetailCustomService,
			AuthenticationManager authenticationManager,
			JwtUtils jwtUtil) {
		this.userDetailCustomService = userDetailCustomService;
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	@PostMapping("/Signup")
	public ResponseEntity<AuthenticationResponse> signUp(@Valid @RequestBody UserDTO dto) throws Exception{
		this.userDetailCustomService.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthenticationResponse> signIn(@RequestBody AuthenticationRequest authRequest)throws Exception{
		UserDetails userDetails;
		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getClass(), authRequest.getPassword())
					);
			userDetails = (UserDetails) auth.getPrincipal();
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
