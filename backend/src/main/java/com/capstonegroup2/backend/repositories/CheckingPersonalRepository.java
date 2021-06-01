package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.CheckingPersonal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckingPersonalRepository extends JpaRepository<CheckingPersonal, Long> {
    CheckingPersonal findByAccountHolder(AccountHolder accountHolder);
    CheckingPersonal findByAccountHolderId(Long id);
}
