package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.IraRegular;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IraRegularRepository extends JpaRepository<IraRegular, Long> {
    IraRegular findByAccountHolder(AccountHolder accountHolder);
    IraRegular findByAccountHolderId(Long id);
    IraRegular findAccountById(Long id);
}
