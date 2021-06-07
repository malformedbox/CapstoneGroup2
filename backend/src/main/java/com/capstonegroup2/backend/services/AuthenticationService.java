package com.capstonegroup2.backend.services;

import com.capstonegroup2.backend.controllers.AccountHolderController;
import com.capstonegroup2.backend.dto.AccountHolderDTO;
import com.capstonegroup2.backend.dto.AuthenticationDTO;
import com.capstonegroup2.backend.dto.UserCreationDTO;
import com.capstonegroup2.backend.dto.UserCredentialsDTO;
import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.UserCredentials;
import com.capstonegroup2.backend.repositories.UserCredentialsRepository;
import com.capstonegroup2.backend.security.JwtTokenCreator;
import com.capstonegroup2.backend.security.UserCredentialsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenCreator jwtTokenCreator;

    @Autowired
    private UserCredentialsServiceImplementation userCredentialsServiceImplementation;

    @Autowired
    AccountHolderService accountHolderService;

    @Autowired
    AccountHolderController accountHolderController;

    //Service method to create a new user, should be assigned an id# upon creation
    public ResponseEntity<?> createUser(UserCreationDTO userCreationDTO) {
        //Checks if this username already exists in the UserCredentialsRepository
        if(userCredentialsRepository.existsByUsername(userCreationDTO.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(String.format("Username %s already exists", userCreationDTO.getUsername()));
        }
        UserCredentials newUserCredentials = new UserCredentials(userCreationDTO.getUsername(), passwordEncoder.encode(userCreationDTO.getPassword()));
        // I believe this would be the point at which when creating a user on the frontend you would need to fill out a
        // form giving the account holder parameters we set in the constructor
//        AccountHolder newAccountHolder = new AccountHolder("Bob", "Sam", "Fishman", "345678901", newUserCredentials);
//
//        AccountHolderDTO newAccountHolderDTO = new AccountHolderDTO();
//        newAccountHolderDTO.setFirstName(newAccountHolder.getFirstName());
//        newAccountHolderDTO.setMiddleName(newAccountHolder.getMiddleName());
//        newAccountHolderDTO.setLastName(newAccountHolder.getLastName());
////        newAccountHolderDTO.setId(newUserCredentials.getId());
//        newUserCredentials.setId(newAccountHolder.getId());
////        accountHolderService.addAccountHolder(newAccountHolderDTO);
//        accountHolderController.addAccountHolder(newAccountHolderDTO);

        AccountHolderDTO newAccountHolderDTO = new AccountHolderDTO();
        newAccountHolderDTO.setFirstName(userCreationDTO.getAccountHolderDTO().getFirstName());
        newAccountHolderDTO.setMiddleName(userCreationDTO.getAccountHolderDTO().getMiddleName());
        newAccountHolderDTO.setLastName(userCreationDTO.getAccountHolderDTO().getLastName());
        newAccountHolderDTO.setId(userCreationDTO.getAccountHolderDTO().getId());

        // So this works as long as I do not use the line below. So the problem is this given id must no be null that
        // should be getting auto generated at creation but is not.
        accountHolderService.addAccountHolder(newAccountHolderDTO);

        userCredentialsRepository.save(newUserCredentials);
        return new ResponseEntity<>(newUserCredentials, HttpStatus.CREATED);
    }

    //Service method that should take user's credentials (username + password in body)
    //Should return a jwt token
    public ResponseEntity<?> authenticateUser(UserCredentialsDTO userCredentialsDTO) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userCredentialsDTO.getUsername(), userCredentialsDTO.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails checkUserCredentials = userCredentialsServiceImplementation
                .loadUserByUsername(userCredentialsDTO.getUsername());

        final String jwt = jwtTokenCreator.generateToken(checkUserCredentials);

        return ResponseEntity.ok(new AuthenticationDTO(jwt));
    }
}