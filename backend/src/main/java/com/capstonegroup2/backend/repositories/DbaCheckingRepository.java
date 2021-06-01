package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.DbaChecking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DbaCheckingRepository extends JpaRepository<DbaChecking, Long> {
    List<DbaChecking> findByAccountHolder(AccountHolder accountHolder);
    List<DbaChecking> findByAccountHolderId(Long id);
}
