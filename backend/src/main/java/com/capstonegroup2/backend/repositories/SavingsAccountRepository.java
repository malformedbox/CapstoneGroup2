package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
    SavingsAccount findByAccountHolder(AccountHolder accountHolder);
    SavingsAccount findByAccountHolderId(Long id);
}
