package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.RothIRA;
import com.capstonegroup2.backend.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RothIRARepository extends JpaRepository<RothIRA, Long> {
    RothIRA findByAccountHolder(AccountHolder accountHolder);
}
