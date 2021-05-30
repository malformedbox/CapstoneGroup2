package com.capstonegroup2.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
@NoArgsConstructor
public class Transaction {

    protected BankAccount sourceAccount;
    protected BankAccount targetAccount;
    protected double amount;
    protected long dateOfTransaction;

    public Transaction(BankAccount sourceAccount, double amount) {
        this.sourceAccount = sourceAccount;
        this.amount = amount;
    }

    public Transaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.amount = amount;
    }

    public Date getDateofTransactionAsDate(){
        return new Date(this.dateOfTransaction * 1000);
    }

    public Date returnLongAsDate(long epoch){
        //This method takes an epoch as a long and converts to Date format in local time zone
        //Written as Thu May 27 18:58:08 CDT 2021
        //Alternatively, the method can be rewritten to receive a string and parse.
        //String epochString = "1622159888";
        //long epoch = Long.parseLong(epochString);
        return new Date(epoch * 1000);
    }

}
