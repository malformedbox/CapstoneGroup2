package com.capstonegroup2.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends Exception {

    public AccountNotFoundException() {
        super("Account could not be located.");
    }

    public AccountNotFoundException(String message) { super(message); }
}
