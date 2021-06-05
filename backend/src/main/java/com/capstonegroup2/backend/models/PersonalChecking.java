package com.capstonegroup2.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class PersonalChecking extends BankAccount{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long id;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_holder_id")
    private AccountHolder accountHolder;

    public PersonalChecking(double balance) {
        super(balance, 0.0001);
    }

    // TODO Override closeAccountResponse
    @Override
    public String closeAccountResponse() {
        return super.closeAccountResponse();
    }
}
