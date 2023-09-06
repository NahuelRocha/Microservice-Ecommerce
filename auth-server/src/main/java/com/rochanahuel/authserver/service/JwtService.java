package com.rochanahuel.authserver.service;


import java.security.Key;
import java.util.Map;

public interface JwtService {

    String generateToken(String userName);

    String createToken(Map<String,Object> claims , String userName);

    Key getSignInKey();

    void validateToken(final String token);
}
