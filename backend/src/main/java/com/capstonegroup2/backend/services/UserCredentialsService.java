package com.capstonegroup2.backend.services;

import com.capstonegroup2.backend.dto.UserCredentialsDTO;
import com.capstonegroup2.backend.models.UserCredentials;
import com.capstonegroup2.backend.repositories.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCredentialsService {

    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    public List<UserCredentials> getAllUserDetails() {
        return userCredentialsRepository.findAll();
    }

    public UserCredentials getAccountHoldersUserDetailsById(Long id) {
        return userCredentialsRepository.findById(id).orElse(null);
    }

    public UserCredentials addAccountHoldersUserDetails(UserCredentialsDTO userCredentialsDTO) {
        UserCredentials userCredentials = new UserCredentials(userCredentialsDTO.getUsername(), userCredentialsDTO.getPassword());
        return userCredentialsRepository.save(userCredentials);
    }


}
