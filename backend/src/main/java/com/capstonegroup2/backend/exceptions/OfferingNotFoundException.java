package com.capstonegroup2.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OfferingNotFoundException extends Exception{

    public OfferingNotFoundException() {
        super("The CDOffering could not be located.");
    }

    public OfferingNotFoundException(String message) { super(message); }
}
