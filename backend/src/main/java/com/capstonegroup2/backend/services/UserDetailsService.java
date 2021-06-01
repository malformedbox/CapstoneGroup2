package com.capstonegroup2.backend.services;

import com.capstonegroup2.backend.dto.UserDetailsDTO;
import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.UserDetails;
import com.capstonegroup2.backend.repositories.AccountHolderRepository;
import com.capstonegroup2.backend.repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public List<UserDetails> getAllUserDetails() {
        return userDetailsRepository.findAll();
    }

    public UserDetails getAccountHoldersUserDetailsById(Long id) {
        return userDetailsRepository.findById(id).orElse(null);
    }

    public UserDetails addAccountHoldersUserDetails(UserDetailsDTO userDetailsDTO) {
        UserDetails userDetails = new UserDetails(userDetailsDTO.getUserName(), userDetailsDTO.getPassword());
        return userDetailsRepository.save(userDetails);
    }
}
