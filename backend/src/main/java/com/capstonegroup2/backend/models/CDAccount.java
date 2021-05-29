package com.capstonegroup2.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cd_accounts")
@Data
@NoArgsConstructor
public class CDAccount extends BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_holder_id")
    private AccountHolder accountHolder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_offering_id")
    private CDOffering cdOffering;

    public CDAccount(double balance, CDOffering cdOffering) {
        super(balance, cdOffering.getInterestRate());
    }

    public static double futureValue(double balance, CDOffering cdOffering) {
        return futureValue(balance, cdOffering.getInterestRate(), cdOffering.getTerm());
    }

    @Override
    public String closeAccountResponse() {
        return super.closeAccountResponse();
    }


}
