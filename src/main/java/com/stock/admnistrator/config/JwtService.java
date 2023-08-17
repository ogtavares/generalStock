package com.stock.admnistrator.config;

import java.security.Key;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET_KEY = "cd24589563a81317825368c019d5f3b6a59502f2f2d1172424a3f7a9006d0bb4";

	public String extractUsername(String token) {

		return null;
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		Key key = Keys.hmacShaKeyFor(keyBytes);
		return key;
	}

}
