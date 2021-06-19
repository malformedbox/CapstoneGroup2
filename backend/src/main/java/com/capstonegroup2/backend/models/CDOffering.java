package com.capstonegroup2.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cdOffering")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CDOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_offering_id")
    private Long id;

    private String interestRate;
    private int term;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cdOffering")
    private List<CDAccount> cdAccounts;

    public CDOffering(int term, String interestRate) {
        this.interestRate = interestRate;
        this.term = term;
    }
}
