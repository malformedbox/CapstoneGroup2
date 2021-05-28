package com.capstonegroup2.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "personal_checking")
@Data
@NoArgsConstructor
public class PersonalChecking extends BankAccount{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_holder_id")
    private AccountHolder accountHolder;

    public PersonalChecking(double balance) {
        super(balance, 0.0001);
    }

}
