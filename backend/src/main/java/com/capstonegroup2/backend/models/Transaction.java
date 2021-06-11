package com.capstonegroup2.backend.models;

import com.capstonegroup2.backend.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
//    private BankAccount sourceAccount;
//    private BankAccount targetAccount;

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

    public Transaction(String amount, TransactionType transactionType) {
        this.amount = new BigDecimal(amount);
        this.transactionType = transactionType;
        LocalDateTime date = LocalDateTime.now();
        this.dateOfTransaction = formatDate(date);
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

}
