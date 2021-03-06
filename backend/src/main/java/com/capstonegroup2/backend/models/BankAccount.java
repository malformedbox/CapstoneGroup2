package com.capstonegroup2.backend.models;

import com.capstonegroup2.backend.enums.AccountType;
import com.capstonegroup2.backend.enums.ActiveStatus;
import com.capstonegroup2.backend.repositories.BankAccountRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@AllArgsConstructor
public abstract class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long id;

    protected long accountNumber;
    protected BigDecimal balance;
    protected BigDecimal interestRate;
    protected String openedOn;

    @Enumerated(EnumType.STRING)
    protected AccountType accountType;

    @Enumerated(EnumType.STRING)
    protected ActiveStatus activeStatus;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "targetAccount")
    protected List<Transaction> transactions = new ArrayList<>();

    public BankAccount(String balance, String interestRate, AccountType accountType) {
        this.balance = new BigDecimal(balance);
        this.interestRate = new BigDecimal(interestRate);
        this.activeStatus = ActiveStatus.OPEN;
        LocalDateTime date = LocalDateTime.now();
        this.openedOn = formatDate(date);
        this.accountNumber = generateAccountNumber();
        this.accountType = accountType;
    }

    public String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);
        return formattedDate;
    }

    public static long generateAccountNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int number = random.nextInt(10);
            stringBuilder.append(number);
        }
        long accountNumber = Long.valueOf(stringBuilder.toString());
        return accountNumber;
    }

    public BigDecimal deposit(BigDecimal depositAmount) {
        BigDecimal updatedBalance = this.getBalance().add(depositAmount);
        return updatedBalance;
    }

    public BigDecimal withdraw(BigDecimal withdrawalAmount) {
        BigDecimal updatedBalance = this.getBalance().subtract(withdrawalAmount);
        return updatedBalance;
    }

    public static double futureValue(double balance, double interestRate, int years) {
        if (years < 1)
            throw new IllegalArgumentException("To calculate a future value a number of positive years " +
                    "greater than 1 must be entered.");
        if (years == 1) {
            return balance * (interestRate + 1);
        } else {
            return (interestRate + 1) * futureValue(balance, interestRate, years - 1);
        }
    }

    public static BigDecimal futureValue(BigDecimal balance, BigDecimal interestRate, int years) {
        if (years < 1)
            throw new IllegalArgumentException("To calculate a future value a number of positive years greater " +
                    "than 1 must be entered.");
        BigDecimal one = new BigDecimal("1");
        BigDecimal result = new BigDecimal(String.valueOf(interestRate.add(one)));
        if (years == 1) {

            return balance.multiply(result);
        } else {
            return (result).multiply(futureValue(balance, interestRate, years - 1));
        }
    }

    public boolean closeAccount(BankAccount account) {
        account.activeStatus = ActiveStatus.CLOSED;
        return true;
    }

    public BigDecimal calculateBalanceAfterIrsTaxOnClose() {
        // calculate 80 percent of accounts balance
        BigDecimal currentBalance = this.getBalance();
        BigDecimal taxAmount = new BigDecimal("0.8");

        BigDecimal updatedBalance = currentBalance.multiply(taxAmount);
        return updatedBalance;
    }
}