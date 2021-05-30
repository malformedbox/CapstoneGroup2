package com.capstonegroup2.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@MappedSuperclass
@NoArgsConstructor
public class BankAccount {

    protected long accountNumber;
    protected double balance;
    protected double interestRate; //Value should be received from subclass passing up through the super constructor
    protected long openedOn;

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

//    public boolean deposit(DepositTransaction deposit) {
//        return false;
//    }

    public static double futureValue(double balance, double interestRate, int years) {
        if (years < 1) throw new IllegalArgumentException();
        if (years == 1) {
            return balance * (interestRate + 1);
        } else {
            return (interestRate + 1) * futureValue(balance, interestRate, years - 1);
        }
    }

    public String closeAccountResponse() {
        return "Account closed, balance transferred to x account";
    }
}