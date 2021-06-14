package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.BankAccount;
import com.capstonegroup2.backend.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findById(long id);
    List<Transaction> findBySourceAccount(BankAccount sourceAccount);
    List<Transaction> findByTargetAccount(BankAccount targetAccount);
    List<Transaction> findAllByTargetAccount(BankAccount targetAccount);
}
