package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.PersonalChecking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalCheckingRepository extends JpaRepository<PersonalChecking, Long> {
    PersonalChecking findByAccountHolder(AccountHolder accountHolder);
    PersonalChecking findByAccountHolderId(Long id);
}
