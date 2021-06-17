package com.capstonegroup2.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalChecking extends BankAccount{

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_holder_id")
    private AccountHolder accountHolder;

    public PersonalChecking(String balance) {
        super(balance, MeritBank.PERSONAL_CHECKING_INTEREST_RATE);
    }

    // TODO Override closeAccountResponse
    @Override
    public String closeAccountResponse() {
        return super.closeAccountResponse();
    }
}
