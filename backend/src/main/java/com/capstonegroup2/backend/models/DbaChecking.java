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
public class DbaChecking extends BankAccount {


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_holder_id")
    private AccountHolder accountHolder;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "dbaChecking")
//    private List<Transaction> transactions = new ArrayList<>();

    public DbaChecking(String balance) {
        super(balance, "0.0005");
    }


    // TODO Override closeAccountResponse
    @Override
    public String closeAccountResponse() {
        return super.closeAccountResponse();
    }
}
