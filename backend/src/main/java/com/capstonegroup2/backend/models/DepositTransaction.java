package com.capstonegroup2.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "deposits")
@Data
@NoArgsConstructor
public class DepositTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "account_id")
//    protected BankAccount account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    protected CheckingPersonal checkingPersonal;


    protected double amount;

    public DepositTransaction(double amount) {
//        this.account = account;
        this.amount = amount;

    }
}
