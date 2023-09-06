package com.rochanahuel.authserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandleExceptions {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {

        Map<String, String> resp = new HashMap<>();

        resp.put("ERROR", ex.getMessage());

        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(UsernameInUseException.class)
    public ResponseEntity<Map<String, String>> handleUsernameInUseException(UserNotFoundException ex) {

        Map<String, String> resp = new HashMap<>();

        resp.put("ERROR", ex.getMessage());

        return new ResponseEntity<>(resp, HttpStatus.CONFLICT);

    }
}
