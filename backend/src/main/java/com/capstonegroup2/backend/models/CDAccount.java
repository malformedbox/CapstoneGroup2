package com.capstonegroup2.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
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
        super(balance, cdOffering.getInterestRate());
        this.cdOffering = cdOffering;
    }

//    public static double futureValue(double balance, CDOffering cdOffering) {
//        return futureValue(balance, cdOffering.getInterestRate(), cdOffering.getTerm());
//    }

    // TODO Override closeAccountResponse
    @Override
    public String closeAccountResponse() {
        return super.closeAccountResponse();
    }

}
