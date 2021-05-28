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

//    private UserDetails userDetails;

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
    or an Enum of the different types and grab the relative accounts whenever we
    need them by that string and pull the various different subclasses with that
    when we need them by its probably not necessary but would be slightly sleeker
    than my current implementation as we wouldn't have to use a dummy object
        'getAccountBalanceByType("CheckingPersonal")'

    The current implementation would be to call it using a dummy object of the BankAccount
    class you want to call as the type parameter so you create a dummy object of that class
    and pass it as a parameter
        BankAccount typeOfAccountYouWant = new CheckingPersonal();
        getAccountBalanceByType(typeOfAccountYouWant);


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
