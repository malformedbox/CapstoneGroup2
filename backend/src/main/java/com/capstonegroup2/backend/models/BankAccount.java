package com.capstonegroup2.backend.models;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class BankAccount {
    private long accountNumber;
    private double balance;
    private double interestRate; //Value should be received from subclass passing up through the super constructor
    private long openedOn;

    public BankAccount(){} //Default constructor
    public BankAccount(double balance, double interestRate){
        this.balance = balance;
        this.interestRate = interestRate;
    }

    public Date getOpenedOnAsDate(){
        return new Date(this.openedOn * 1000);
    }

    public Date returnLongAsDate(long epoch){
        //This method takes an epoch as a long and converts to Date format in local time zone
        //Written as Thu May 27 18:58:08 CDT 2021
        //Alternatively, the method can be rewritten to receive a string and parse.
        //String epochString = "1622159888";
        //long epoch = Long.parseLong(epochString);
        return new Date(epoch * 1000);
    }

//    public boolean withdraw(WithdrawTransaction withdraw) {
//        return false;
//    }

//    public boolean transfer(TransferTransaction transfer) {
//        return false;
//    }

    public String closeAccountResponse() {
        return "Account closed, balance transferred to x account";
    }
}