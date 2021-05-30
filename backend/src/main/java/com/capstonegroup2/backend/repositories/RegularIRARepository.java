package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.RegularIRA;
import com.capstonegroup2.backend.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegularIRARepository extends JpaRepository<RegularIRA, Long> {
    RegularIRA findByAccountHolder(AccountHolder accountHolder);
}
