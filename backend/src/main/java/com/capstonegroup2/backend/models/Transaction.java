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
//    private BankAccountType bankAccountType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dba_account_id")
    DbaChecking dbaChecking;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cd_account_id")
    CDAccount cdAccount;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ira_reg_account_id")
    IraRegular iraRegular;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ira_roll_account_id")
    IraRollover iraRollover;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ira_roth_account_id")
    IraRoth iraRoth;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "checking_account_id")
    PersonalChecking personalChecking;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "savings_account_id")
    SavingsAccount savingsAccount;

    public Transaction(double amount, TransactionType transactionType) {
        this.amount = amount;
        this.transactionType = transactionType;
    }

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
