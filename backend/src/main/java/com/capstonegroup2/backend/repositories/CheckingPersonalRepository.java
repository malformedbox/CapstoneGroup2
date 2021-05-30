package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.CheckingPersonal;
import com.capstonegroup2.backend.models.UserDetails;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckingPersonalRepository extends JpaRepository<CheckingPersonal, Long> {
    CheckingPersonal findByAccountHolder(AccountHolder accountHolder);
}
