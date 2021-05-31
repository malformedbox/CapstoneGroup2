package com.capstonegroup2.backend.models;

import com.capstonegroup2.backend.enums.ActiveStatus;
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

    private ActiveStatus activeStatus;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private UserDetails userDetails;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private CheckingPersonal persdonalChecking;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private List<CheckingDBA> checkingDBAList;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private SavingsAccount savingsAccount;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private RolloverIRA rolloverIRA;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private RegularIRA regularIRA;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private RothIRA rothIRA;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private List<CDAccount> cdAccountsList;

    public AccountHolder(String firstName, String middleName, String lastName, String ssn, UserDetails userDetails) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.userDetails = userDetails;
        this.activeStatus = ActiveStatus.OPEN;
    }

}
