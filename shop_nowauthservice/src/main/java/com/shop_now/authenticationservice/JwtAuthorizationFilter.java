package com.shop_now.authenticationservice;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	
	private final JwtConfig jwtConfig;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig) {
		super(authenticationManager);
		this.jwtConfig = jwtConfig;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
					throws ServletException, IOException{
		
		String header = request.getHeader(jwtConfig.getHeader());
		
		if(header == null || !header.startsWith(jwtConfig.getHeader())) {
			chain.doFilter(request, response);
			return;
		}
		
		String token = header.replace(jwtConfig.getHeader(), "");
		
		try {
			Claims claims = Jwts.parser()
							.setSigningKey(jwtConfig.getSecret().getBytes())
							.parseClaimsJws(token)
							.getBody();
			
			String username = claims.getSubject();
			if(username != null) {
				UsernamePasswordAuthenticationToken auth  = new UsernamePasswordAuthenticationToken(
																username, null, Collections.emptyList());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch(Exception e) {
			SecurityContextHolder.clearContext();
		}
		
		chain.doFilter(request, response);
	}

}
