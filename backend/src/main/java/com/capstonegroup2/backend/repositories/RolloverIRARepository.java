package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.RolloverIRA;
import com.capstonegroup2.backend.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolloverIRARepository extends JpaRepository<RolloverIRA, Long> {
    RolloverIRA findByAccountHolder(AccountHolder accountHolder);
}
