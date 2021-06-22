package com.capstonegroup2.backend.services;

import com.capstonegroup2.backend.dto.LoginDTO;
import com.capstonegroup2.backend.models.UserCredentials;
import com.capstonegroup2.backend.repositories.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCredentialsService {

    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    public List<UserCredentials> getAllUserCredentials() {
        return userCredentialsRepository.findAll();
    }

    public UserCredentials getUserCredentialsById(Long id) {
        return userCredentialsRepository.findById(id).orElse(null);
    }

    public UserCredentials addUserCredentials(LoginDTO loginDTO) {
        UserCredentials userCredentials = new UserCredentials(loginDTO.getUsername(), loginDTO.getPassword());
        return userCredentialsRepository.save(userCredentials);
    }


}
