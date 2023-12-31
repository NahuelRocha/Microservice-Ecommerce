package com.rochanahuel.purchase.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PurchaseNotFoundException extends RuntimeException{

    public PurchaseNotFoundException(String message){
        super(message);
    }
}
