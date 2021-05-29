package com.capstonegroup2.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ira_rollover")
@Data
@NoArgsConstructor
public class IRARollover extends BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_holder_id")
    private AccountHolder accountHolder;

    public IRARollover(double balance) {
        super(balance, 0.08);
    }

    @Override
    public String closeAccountResponse() {
        return super.closeAccountResponse();
    }
}
