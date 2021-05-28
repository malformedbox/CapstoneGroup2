package com.capstonegroup2.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "savings_account")
@Data
@NoArgsConstructor
public class SavingsAccount extends BankAccount{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_holder_id")
    private AccountHolder accountHolder;

    public SavingsAccount(double balance) {
        super(balance, 0.01);
    }

}
