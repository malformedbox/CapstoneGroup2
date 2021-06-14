package com.capstonegroup2.backend.exceptions;

public class AccountLimitExceededException extends Exception {

    //Default Message
    public AccountLimitExceededException() {
        super("Account Holder exceeds the amount of permitted accounts of the requested type.");
    }

    public AccountLimitExceededException(String message) { super(message); }

}
