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
    private PersonalChecking personalChecking;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private DbaChecking dbaChecking;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private IraRegular iraRegular;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private IraRollover iraRollover;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private IraRoth iraRoth;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private SavingsAccount savingsAccount;

    public DepositTransaction(double amount) {
        this.amount = amount;

    }
}
