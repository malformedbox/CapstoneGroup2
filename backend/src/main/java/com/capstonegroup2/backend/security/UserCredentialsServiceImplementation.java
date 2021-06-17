package com.capstonegroup2.backend.security;

import com.capstonegroup2.backend.models.UserCredentials;
import com.capstonegroup2.backend.repositories.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserCredentialsServiceImplementation implements UserDetailsService {

    @Autowired UserCredentialsRepository userCredentialsRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredentials userCredentials = userCredentialsRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserCredentialsImplementation.build(userCredentials);
    }
}