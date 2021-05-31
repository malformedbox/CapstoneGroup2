package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.CheckingDBA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckingDBARepository extends JpaRepository<CheckingDBA, Long> {
    List<CheckingDBA> findByAccountHolder(AccountHolder accountHolder);
    List<CheckingDBA> findByAccountHolderId(Long id);
}
