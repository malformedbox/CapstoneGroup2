package com.capstonegroup2.backend.dto;

import com.capstonegroup2.backend.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private double amount;
    private long dateOfTransaction;
    private TransactionType transactionType;
    private long sourceAccountNumber;
    private long targetAccountNumber;

}
