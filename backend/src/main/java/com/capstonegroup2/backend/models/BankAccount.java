package com.capstonegroup2.backend.models;

import com.capstonegroup2.backend.enums.ActiveStatus;
import com.capstonegroup2.backend.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    // To use BigDecimal in mathematical operations method calls must be made instead of using operators
    // Ex. balance.subtract(withdrawal);
    // Also numbers should be sent as Strings and not doubles otherwise it would still use floating point calculation
    // At this point I think the easiest way to implement this is to use Strings for numbers and then assign them
    // as BigDecimals in constructors or method calls using the new keyword

    protected long accountNumber;
//    protected double balance;
//    protected double interestRate; //Value should be received from subclass passing up through the super constructor
//    protected String interestRateFormatted;
    protected BigDecimal balance;
    protected BigDecimal interestRate;
//    protected LocalDateTime openedOn;
    protected String opendOn;
    protected ActiveStatus activeStatus;


    public BankAccount(String balance, String interestRate){
        this.balance = new BigDecimal(balance);
        this.interestRate = new BigDecimal(interestRate);
        this.activeStatus = ActiveStatus.OPEN;
        LocalDateTime date = LocalDateTime.now();
        this.opendOn = formatDate(date);
        this.accountNumber = generateAccountNumber();
    }

    public String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);
        return formattedDate;
    }

//    public Date getOpenedOnAsDate(){
//        return new Date(this.openedOn * 1000);
//    }
//
//    public Date returnLongAsDate(long epoch){
//        //This method takes an epoch as a long and converts to Date format in local time zone
//        //Written as Thu May 27 18:58:08 CDT 2021
//        //Alternatively, the method can be rewritten to receive a string and parse.
//        //String epochString = "1622159888";
//        //long epoch = Long.parseLong(epochString);
//        return new Date(epoch * 1000);
//    }

//    public String formatInterestRate(double interestRate) {
//        NumberFormat numberFormat = NumberFormat.getInstance();
//        numberFormat.setMaximumFractionDigits(4);
//        return numberFormat.format(interestRate);
//    }

//    public Double formatInterestRate(double interestRate) {
//        DecimalFormat formatter = new DecimalFormat("#.####");
//        Double formattedInterestRate = Double.parseDouble(formatter.format(interestRate));
//        return formattedInterestRate;
//
//    }

    public String formatDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
        Date date = new Date();
        return dateFormat.format(date);
    }

    // This method is just an idea of how we can process a transaction based on type
    // It might need to just return the transaction type or be placed in the service
    // layer to redirect an incoming transaction and route it accordingly to the
    // correct method based on type
//    public boolean processTransactionType(Transaction transaction) {
//        if (transaction.getTransactionType().equals(TransactionType.WITHDRAWAL)) {
//            withdraw(transaction);
//            return true;
//        } else if (transaction.getTransactionType().equals(TransactionType.DEPOSIT)) {
//            deposit(transaction);
//            return true;
//        } else if (transaction.getTransactionType().equals(TransactionType.TRANSFER)) {
//            transfer(transaction);
//            return true;
//        }
//        return false;
//    }

    // This method will build a string consisting of 10 random numbers generated one at a
    // time and convert it to a long. The length of the account number  could easily be
    // modified by changing the length in the for loop
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

//
//    public boolean withdraw(Transaction withdrawal) {
//        if (withdrawal.getAmount() > 0 && withdrawal.getAmount() < balance) {
//            balance -= withdrawal.getAmount();
//            return true;
//        }
//        return false;
//    }
//
//    public boolean transfer(Transaction transfer) {
//        return false;
//    }
//
//    public boolean deposit(Transaction deposit) {
//       if (deposit.getAmount() > 0) {
//           balance += deposit.getAmount();
//           return true;
//       }
//       return false;
//    }
//

    public static double futureValue(double balance, double interestRate, int years) {
        if (years < 1) throw new IllegalArgumentException("To calculate a future value a number of positive years greater than 1 must be entered.");
        if (years == 1) {
            return balance * (interestRate + 1);
        } else {
            return (interestRate + 1) * futureValue(balance, interestRate, years - 1);
        }
    }

    public static BigDecimal futureValue(BigDecimal balance, BigDecimal interestRate, int years) {
        if (years < 1) throw new IllegalArgumentException("To calculate a future value a number of positive years greater than 1 must be entered.");
        BigDecimal one = new BigDecimal("1");
        BigDecimal result1 = new BigDecimal(String.valueOf(interestRate.add(one)));
        if (years == 1) {

            return balance.multiply(result1);
        } else {
            return (result1).multiply(futureValue(balance, interestRate, years - 1));
        }
    }

    public String closeAccountResponse() {
        return "Account closed, balance transferred to x account";
    }

    public boolean closeAccount(BankAccount account) {
        account.activeStatus = ActiveStatus.CLOSED;
        return true;
    }
}