package com.capstonegroup2.backend.models;

import com.capstonegroup2.backend.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private long dateOfTransaction;
    private TransactionType transactionType;
    private Long bankAccountId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_holder_id")
    private AccountHolder accountHolder;

    @ManyToOne
    @JoinColumn(name = "account_holder_id", insertable = false, updatable = false)
    private CDAccount cdAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_holder_id", insertable = false, updatable = false)
    private PersonalChecking personalChecking;

    @ManyToOne
    @JoinColumn(name = "account_holder_id", insertable = false, updatable = false)
    private DbaChecking dbaChecking;

    @ManyToOne
    @JoinColumn(name = "account_holder_id", insertable = false, updatable = false)
    private IraRegular iraRegular;

    @ManyToOne
    @JoinColumn(name = "account_holder_id", insertable = false, updatable = false)
    private IraRollover iraRollover;

    @ManyToOne
    @JoinColumn(name = "account_holder_id", insertable = false, updatable = false)
    private IraRoth iraRoth;

    @ManyToOne
    @JoinColumn(name = "account_holder_id", insertable = false, updatable = false)
    private SavingsAccount savingsAccount;

    public Transaction(double amount, long dateOfTransaction, TransactionType transactionType) {
        this.amount = amount;
        this.dateOfTransaction = dateOfTransaction;
        this.transactionType = transactionType;
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

    public double deposit(double depositAmount) {
        this.amount += depositAmount;
        return amount;
    }
}
