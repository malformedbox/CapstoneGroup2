package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.IraRollover;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IraRolloverRepository extends JpaRepository<IraRollover, Long> {
    IraRollover findByAccountHolder(AccountHolder accountHolder);
    IraRollover findByAccountHolderId(Long id);
}
