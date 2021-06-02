package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountHolder(AccountHolder accountHolder);
}
