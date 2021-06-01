package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {

    AccountHolder findById(long id);
    AccountHolder findByUserDetails(UserDetails userDetails);
    List<AccountHolder> findAll();

}
