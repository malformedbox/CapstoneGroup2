package com.capstonegroup2.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountHolderNotFoundException extends Exception{

    public AccountHolderNotFoundException() {
        super("Account Holder could not be located.");
    }

    public AccountHolderNotFoundException(String message) {
        super(message);
    }



}
