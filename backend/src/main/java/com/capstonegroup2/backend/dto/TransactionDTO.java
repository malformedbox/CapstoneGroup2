package com.capstonegroup2.backend.dto;

import com.capstonegroup2.backend.enums.TransactionType;
import lombok.Data;

@Data
public class TransactionDTO {

    private double amount;
    private long dateOfTransaction;
    private TransactionType transactionType;

}
