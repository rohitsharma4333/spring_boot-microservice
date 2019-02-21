package com.shop_now.authenticationservice;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop_now.authenticationservice.user.AppUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	private final JwtConfig jwtConfig;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
			JwtConfig jwtConfig) {
		this.authenticationManager = authenticationManager;
		this.jwtConfig = jwtConfig;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException{
		try {
			AppUser appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
			
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					appUser.getUsername(), appUser.getPassword(), Collections.emptyList());
			return(authenticationManager.authenticate(authToken));
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException{
		Long now = System.currentTimeMillis();
		String token = Jwts.builder()
				.setSubject(auth.getName())
				.setIssuedAt(new Date(now))
				.setExpiration(new Date(now + jwtConfig.getExpiration()*1000))
				.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
				.compact();
		
		response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);
	}

}
