package com.capstonegroup2.backend.models;

import com.capstonegroup2.backend.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbaChecking extends BankAccount {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_holder_id")
    private AccountHolder accountHolder;

    public DbaChecking(String balance) {
        super(balance, MeritBank.DBA_CHECKING_INTEREST_RATE, AccountType.CHECKING);
    }
}
