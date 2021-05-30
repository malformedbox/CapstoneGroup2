package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.UserDetails;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByAccountHolder(AccountHolder accountHolder);
    UserDetails findByAccountHolderId(Long id);
    UserDetails findById(long id);
    List<UserDetails> findAll();
}
