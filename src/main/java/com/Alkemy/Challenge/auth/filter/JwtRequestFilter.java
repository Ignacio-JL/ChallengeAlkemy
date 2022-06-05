package com.Alkemy.Challenge.auth.filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Alkemy.Challenge.auth.service.JwtUtils;
import com.Alkemy.Challenge.auth.service.UserDetailCustomService;

import io.jsonwebtoken.Claims;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserDetailCustomService userDetailCustomService;
	@Autowired
	private JwtUtils jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwt = null;
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
		Claims claims = jwtUtil.extractAllClaims(authorizationHeader);
		List<String> authorities = (List) claims.get("auhtorities");
		if(authorities != null) {
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
					claims.getSubject(), 
					null,
					getGrantedAuthorities(authorities));
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		else {
			SecurityContextHolder.clearContext();
		}
	}
		
//		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//			jwt = authorizationHeader.substring(7);
//			username = jwtUtil.extractUserName(jwt);
//		}
//		
//		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//			
//			UserDetails userDetails = this.userDetailCustomService.loadUserByUsername(username);
//			
//			if(jwtUtil.validateToken(jwt, userDetails)) {
//				UsernamePasswordAuthenticationToken authReq = 
//							new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
//				Authentication auth = authenticationManager.authenticate(authReq);
//				
//				SecurityContextHolder.getContext().setAuthentication(auth);
//			}
//		}
		filterChain.doFilter(request, response);	
	}
	
	private List<SimpleGrantedAuthority> getGrantedAuthorities(List<String> authorities) {
	    return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	  }
	
}
