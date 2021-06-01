package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
