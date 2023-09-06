package com.rochanahuel.gateway.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class MissTokenJwtException extends RuntimeException {

    public MissTokenJwtException(String message) {
        super(message);
    }

}
