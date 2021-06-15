package com.capstonegroup2.backend.models;

import com.capstonegroup2.backend.enums.ActiveStatus;
import com.capstonegroup2.backend.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
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
    public Transaction closeAccount(BankAccount account) {
        Transaction transaction = transferBalanceOnAccountClose(account);
        account.setActiveStatus(ActiveStatus.CLOSED);
        return transaction;
    }

    // TODO TEST
    private Transaction transferBalanceOnAccountClose(BankAccount sourceAccount) {
        // If closing checking account, transfer balance to savings account
        if (sourceAccount.getClass() == personalChecking.getClass() ||
                sourceAccount.getClass() == dbaCheckingList.get(0).getClass()) {
            String balance = String.valueOf(sourceAccount.getBalance());
            sourceAccount.withdraw(sourceAccount.getBalance());
            savingsAccount.deposit(sourceAccount.getBalance());
            return new Transaction(balance, TransactionType.CLOSE_ACCOUNT_TRANSFER, sourceAccount, savingsAccount);
        }
        // If closing an IRS Account, deduct 20% for IRS and transfer to personal checking else savings
        if (sourceAccount.getClass() == iraRegular.getClass() || sourceAccount.getClass() == iraRoth.getClass() ||
                sourceAccount.getClass() == iraRollover.getClass()) {
            String balance = String.valueOf(sourceAccount.getBalance());
            BigDecimal balanceToBeTransferred = sourceAccount.calculateBalanceAfterIrsTaxOnClose();
            if (personalChecking != null) {
                sourceAccount.withdraw(sourceAccount.getBalance());
                personalChecking.deposit(balanceToBeTransferred);
                return new Transaction(balance, TransactionType.CLOSE_ACCOUNT_TRANSFER,
                        sourceAccount, personalChecking);
            } else {
                sourceAccount.withdraw(sourceAccount.getBalance());
                savingsAccount.deposit(balanceToBeTransferred);
                return new Transaction(balance, TransactionType.CLOSE_ACCOUNT_TRANSFER,
                        sourceAccount, savingsAccount);
            }
        }
        // To close a savings account, all other accounts must already be closed
        if (sourceAccount.getClass() == savingsAccount.getClass()) {
            if (personalChecking.getActiveStatus() == ActiveStatus.OPEN
                    && dbaCheckingList.size() > 1
                    && iraRegular.getActiveStatus() == ActiveStatus.OPEN
                    && iraRoth.getActiveStatus() == ActiveStatus.OPEN
                    && iraRollover.getActiveStatus() == ActiveStatus.OPEN
                    && cdAccountsList.size() > 1) {
                throw new IllegalArgumentException("To close a savings account, an account holder may not have any " +
                        "other open accounts.");
            } else {
                savingsAccount.withdraw(savingsAccount.getBalance());
                // If this method moves classes 'this' refers to the Account Holder here
                this.activeStatus = ActiveStatus.CLOSED;
                return new Transaction("0", TransactionType.CLOSE_ACCOUNT_TRANSFER,
                        new SavingsAccount("0"));
            }
        }
        //If closing CDAccount transfer to personal checking if not transfer to savings account
        if (sourceAccount.getClass() == cdAccountsList.get(0).getClass()) {
            if (personalChecking != null) {
                String balance = String.valueOf(sourceAccount.getBalance());
                sourceAccount.withdraw(sourceAccount.getBalance());
                personalChecking.deposit(sourceAccount.getBalance());
                return new Transaction(balance, TransactionType.CLOSE_ACCOUNT_TRANSFER,
                        sourceAccount, personalChecking);
            } else {
                String balance = String.valueOf(sourceAccount.getBalance());

                sourceAccount.withdraw(sourceAccount.getBalance());
                savingsAccount.deposit(sourceAccount.getBalance());
                return new Transaction(balance, TransactionType.CLOSE_ACCOUNT_TRANSFER,
                        sourceAccount, savingsAccount);
            }
        }
        return null;
    }

}
