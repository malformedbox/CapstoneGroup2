package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.CDAccount;
import com.capstonegroup2.backend.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CDAccountRepository extends JpaRepository<CDAccount, Long> {
    List<CDAccount> findAll();
    List<CDAccount> findByAccountHolder(AccountHolder accountHolder);
    List<CDAccount> findByAccountHolderId(Long id);
}
