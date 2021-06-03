package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {

    AccountHolder findById(long id);
    AccountHolder findByUserCredentials(UserCredentials userCredentials);
    List<AccountHolder> findAll();

}
