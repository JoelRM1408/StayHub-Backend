package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
	private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private static final long EXPIRATION_TIME = 86400000;

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
	    List<String> roles = userDetails.getAuthorities()
	        .stream()
	        .map(GrantedAuthority::getAuthority)
	        .collect(Collectors.toList());
	    
	    claims.put("roles", roles);
	    return Jwts.builder()
	            .setClaims(claims)
	            .setSubject(userDetails.getUsername())
	            .setIssuedAt(new Date())
	            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
	            .signWith(SECRET_KEY)
	            .compact();
	}

	public String extractEmail(String token) {
		return getClaims(token).getSubject();
	}

	public List<String> extractRoles(String token) {
	    Claims claims = getClaims(token);
	    List<String> roles = claims.get("roles", List.class);

	    return roles;
	}

	public boolean validateToken(String token) {
		try {
			getClaims(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}

	private Claims getClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(SECRET_KEY)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
}