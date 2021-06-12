package com.capstonegroup2.backend.models;

import com.capstonegroup2.backend.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private String dateOfTransaction;
    private TransactionType transactionType;

//    @ManyToOne
//    @JoinColumn(name = "source_account_id")
//    @JsonIgnore
//    private BankAccount sourceAccount;
//
//
//    @ManyToOne
//    @JoinColumn(name = "target_account_id")
//    @JsonIgnore
//    private BankAccount targetAccount;

    //region
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "dba_account_id")
//    DbaChecking dbaChecking;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "cd_account_id")
//    CDAccount cdAccount;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "ira_reg_account_id")
//    IraRegular iraRegular;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "ira_roll_account_id")
//    IraRollover iraRollover;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "ira_roth_account_id")
//    IraRoth iraRoth;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "checking_account_id")
//    PersonalChecking personalChecking;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "savings_account_id")
//    SavingsAccount savingsAccount;
    //endregion

    public Transaction(String amount, TransactionType transactionType) {
        this.amount = new BigDecimal(amount);
        this.transactionType = transactionType;
        LocalDateTime date = LocalDateTime.now();
        this.dateOfTransaction = formatDate(date);
    }

//    public Transaction(String amount, TransactionType transactionType, long sourceAccount, long targetAccount) {
//        this.amount = new BigDecimal(amount);
//        this.transactionType = transactionType;
//        LocalDateTime date = LocalDateTime.now();
//        this.dateOfTransaction = formatDate(date);
//        this.sourceAccount = findByAccountNumber(sourceAccount);
//        this.targetAccount = findByAccountNumber(targetAccount);
//    }

    public Transaction(String amount, TransactionType transactionType, BankAccount sourceAccount, BankAccount targetAccount) {
        this.amount = new BigDecimal(amount);
        this.transactionType = transactionType;
        LocalDateTime date = LocalDateTime.now();
        this.dateOfTransaction = formatDate(date);
//        this.sourceAccount = sourceAccount;
//        this.targetAccount = targetAccount;
    }


    private String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);
        return formattedDate;
    }

//    public boolean deposit(String depositAmount) {
//        BigDecimal deposit = new BigDecimal(depositAmount);
//        BigDecimal balance = this.targetAccount.getBalance();
//        BigDecimal newBalance = deposit.add(balance);
//
//        this.targetAccount.setBalance(newBalance);
//
//        return true;
//    }

//    public BankAccount findByAccountNumber(long accountNumber) {
//        if (cdAccount.getAccountNumber() == accountNumber) {
//            return cdAccount;
//        } else if (dbaChecking.getAccountNumber() == accountNumber) {
//            return dbaChecking;
//        } else if (iraRegular.getAccountNumber() == accountNumber) {
//            return iraRegular;
//        } else if (iraRollover.getAccountNumber() == accountNumber) {
//            return iraRollover;
//        } else if (iraRoth.getAccountNumber() == accountNumber) {
//            return iraRoth;
//        } else if (savingsAccount.getAccountNumber() == accountNumber) {
//            return savingsAccount;
//        } else if (personalChecking.getAccountNumber() == accountNumber) {
//            return personalChecking;
//        }
//        return null;
//    }

}
