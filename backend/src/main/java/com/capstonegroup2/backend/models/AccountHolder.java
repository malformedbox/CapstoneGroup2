package com.capstonegroup2.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "account_holder")
@Data
@NoArgsConstructor
public class AccountHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_holder_id")
    private Long id;

    @NotBlank(message = "First Name is a required field")
    private String firstName;
    private String middleName;
    @NotBlank(message = "Last Name is a required field")
    private String lastName;
    @NotBlank(message = "SSN is a required field")
    private String ssn;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private UserDetails userDetails;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private PersonalChecking persdonalChecking;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private DBAChecking dbaChecking;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private SavingsAccount savingsAccount;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private IRARoth iraRoth;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private IRARollover iraRollover;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private IRARegular iraRegular;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private List<CDAccount> cdAccounts;

    public AccountHolder(String firstName, String middleName, String lastName, String ssn) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.ssn = ssn;
    }



}
