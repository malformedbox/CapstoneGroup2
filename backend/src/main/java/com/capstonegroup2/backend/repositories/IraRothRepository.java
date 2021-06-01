package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.IraRoth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IraRothRepository extends JpaRepository<IraRoth, Long> {
    IraRoth findByAccountHolder(AccountHolder accountHolder);
    IraRoth findByAccountHolderId(Long id);
}
