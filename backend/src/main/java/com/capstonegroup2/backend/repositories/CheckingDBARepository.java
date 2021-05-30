package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.CheckingDBA;
import com.capstonegroup2.backend.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckingDBARepository extends JpaRepository<CheckingDBA, Long> {
    CheckingDBA findByAccountHolder(AccountHolder accountHolder);
    CheckingDBA findByAccountHolderId(Long id);
}
