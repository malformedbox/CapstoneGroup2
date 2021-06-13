package com.capstonegroup2.backend.exceptions;

public class AccountLimitExceededException extends Exception {
    public AccountLimitExceededException(String message) { super(message); }
}
