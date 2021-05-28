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
    private int id;

    @NotBlank
    private String firstName;
    @NotBlank
    private String middleName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String ssn;

    /* Bank Accounts can be called by type using 'variableName.getClass()' */
    private List<BankAccount> bankAccountList;

    public AccountHolder(String firstName, String middleName, String lastName, String ssn) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.ssn = ssn;
    }


    /*
    I think we could add a String variable named 'type' to the Bank Account class
    or an Enum of the different and grab the relative accounts whenever we need them
    types of Bank Accounts and pull the various different subclasses with that when we
    need them instead of how I have it implemented below which hypothetically functions
    by calling this method by creating an instance of the class of the type of account
    you want and using that empty object to pull the relative class you send it.

    I'm leaving out methods for now that want the balance of certain combinations of types
    of accounts (for instance if we decide we want to be able to get the balance of all
    accounts excluding ira accounts) until we decide what combinations will be useful for
    our endpoints

    I write long comments to procrastinate lol
     */
    public double getAccountBalanceByType(BankAccount typeOfAccount) {
        double balance = 0;
        for (BankAccount currentAccount : bankAccountList) {
            if (typeOfAccount.getClass() == currentAccount.getClass()) {
                balance = currentAccount.getBalance();
            }
        }
        return balance;
    }

    public double getBalanceOfAllAccounts() {
        double balance = 0;
        for (BankAccount account : bankAccountList) {
            balance += account.getBalance();
        }
        return balance;
    }

    public int getNumberOfAccountsByType(BankAccount typeOfAccount) {
        int numberOfAccounts = 0;
        for (BankAccount currentAccount : bankAccountList) {
            if (typeOfAccount.getClass() == currentAccount.getClass()) {
                numberOfAccounts += 1;
            }
        }
        return numberOfAccounts;
    }

}
