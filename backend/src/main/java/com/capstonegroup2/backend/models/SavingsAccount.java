package com.capstonegroup2.backend.models;

import com.capstonegroup2.backend.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavingsAccount extends BankAccount{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "savings_account_id")
    private Long id;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_holder_id")
    private AccountHolder accountHolder;

    public SavingsAccount(String balance) {
        super(balance, MeritBank.SAVINGS_ACCOUNT_INTEREST_RATE, AccountType.SAVINGS);
    }
}
