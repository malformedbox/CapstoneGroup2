package com.capstonegroup2.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cd_offerings")
@Data
@NoArgsConstructor
public class CDOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double interestRate;
    private int term;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cdOffering")
    private List<CDAccount> cdAccounts;

    public CDOffering(int term, double interestRate) {
        this.interestRate = interestRate;
        this.term = term;
    }
}
