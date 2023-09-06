package com.rochanahuel.authserver.service.impl;

import com.rochanahuel.authserver.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    private static final String SECRET_KEY = "B374A26A71421437AA024E4FADD5B497FDFF1A8EA6FF12F6FB65AF2720B59CXF";

    @Override
    public String generateToken(String userName) {

        Map<String, Object> claims = new HashMap<>();

        return createToken(claims, userName);
    }

    @Override
    public String createToken(Map<String, Object> claims, String userName) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Key getSignInKey() {

        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyByte);
    }

    @Override
    public void validateToken(String token) {

        Jws<Claims> claimsJws = Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token);
    }
}
