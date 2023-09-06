package com.rochanahuel.gateway.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtServiceImpl {

    private static final String SECRET_KEY = "B374A26A71421437AA024E4FADD5B497FDFF1A8EA6FF12F6FB65AF2720B59CXF";

    public Key getSignInKey() {

        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyByte);
    }

    public void validateToken(String token) {

        Jws<Claims> claimsJws = Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token);
    }
}

