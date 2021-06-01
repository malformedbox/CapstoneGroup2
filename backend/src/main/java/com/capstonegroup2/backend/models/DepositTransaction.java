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

    protected double amount;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private CDAccount cdAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private CheckingPersonal checkingPersonal;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private CheckingDBA checkingDBA;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private RegularIRA regularIRA;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private RolloverIRA rolloverIRA;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private RothIRA rothIRA;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private SavingsAccount savingsAccount;

    public DepositTransaction(double amount) {
        this.amount = amount;

    }
}
