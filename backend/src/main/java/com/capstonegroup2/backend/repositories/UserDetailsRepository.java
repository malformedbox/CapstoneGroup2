package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByAccountHolder(AccountHolder accountHolder);
    UserDetails findByAccountHolderId(Long id);
    Optional<UserDetails> findById(Long id);
    List<UserDetails> findAll();
}
