package com.rochanahuel.authserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsernameInUseException extends RuntimeException {

    public UsernameInUseException(String message) {
        super(message);
    }
}
