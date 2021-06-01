package com.capstonegroup2.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class IraRoth extends BankAccount{


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_holder_id")
    private AccountHolder accountHolder;

    public IraRoth(double balance) {
        super(balance, 0.08);
    }

    // TODO Override closeAccountResponse
    @Override
    public String closeAccountResponse() {
        return super.closeAccountResponse();
    }
}