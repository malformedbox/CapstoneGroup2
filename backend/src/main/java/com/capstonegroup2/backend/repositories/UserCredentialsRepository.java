package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {
    UserCredentials findByAccountHolder(AccountHolder accountHolder);
    UserCredentials findByAccountHolderId(Long id);
    Optional<UserCredentials> findById(Long id);
    List<UserCredentials> findAll();
    Optional<UserCredentials> findByUsername(String username);
    Boolean existsByUsername(String username);
}
