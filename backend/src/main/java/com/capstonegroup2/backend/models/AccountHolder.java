package com.capstonegroup2.backend.models;

import com.capstonegroup2.backend.enums.ActiveStatus;
import com.capstonegroup2.backend.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account_holder")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    private ActiveStatus activeStatus;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private UserCredentials userCredentials;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private PersonalChecking personalChecking = new PersonalChecking();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private List<DbaChecking> dbaCheckingList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private SavingsAccount savingsAccount = new SavingsAccount();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private IraRollover iraRollover = new IraRollover();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private IraRegular iraRegular = new IraRegular();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private IraRoth iraRoth = new IraRoth();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private List<CDAccount> cdAccountsList = new ArrayList<>();

    public AccountHolder(String firstName, String middleName, String lastName, String ssn, UserCredentials userCredentials) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.userCredentials = userCredentials;
        this.activeStatus = ActiveStatus.OPEN;
    }

    public int numbertOfHoldersExistingDbaAccounts(AccountHolder accountHolder) {
        return dbaCheckingList.size();
    }

    // TODO - Functionality to Close Accounts and Transfer Balance based on business Logic
    public boolean closeAccount(BankAccount account) {
        transferBalanceOnAccountClose(account);
        account.setActiveStatus(ActiveStatus.CLOSED);
        return true;
    }

    // TODO - This might be moved to LoggedIn Service
    private boolean transferBalanceOnAccountClose(BankAccount sourceAccount) {

        // If closing checking account, transfer balance to savings account
        if (sourceAccount.getClass() == personalChecking.getClass() ||
                sourceAccount.getClass() == dbaCheckingList.get(0).getClass()) {

            String balance = String.valueOf(sourceAccount.getBalance());
            Transaction transaction = new Transaction(balance, TransactionType.TRANSFER, sourceAccount, savingsAccount);

            sourceAccount.withdraw(sourceAccount.getBalance());
            savingsAccount.deposit(sourceAccount.getBalance());



        }
        return false;
    }

}
