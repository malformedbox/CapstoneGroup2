package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    BankAccount findByAccountNumber(long accountNumber);
}
