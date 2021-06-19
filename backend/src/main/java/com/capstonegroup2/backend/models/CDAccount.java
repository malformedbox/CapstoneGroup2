package com.capstonegroup2.backend.models;

import com.capstonegroup2.backend.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "CD_Account")
@NoArgsConstructor
@AllArgsConstructor
public class CDAccount extends BankAccount {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_holder_id")
    private AccountHolder accountHolder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_offering_id")
    private CDOffering cdOffering;

    public CDAccount(String balance, CDOffering cdOffering) {
        super(balance, cdOffering.getInterestRate(), AccountType.CD);
        this.cdOffering = cdOffering;
    }
}
