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

    // So this create user is a pretty important method we need to figure out. Its currently an issue with the ids. I
    // think we could possibly simplify it by just only having account holder or user create the id and then setting the
    // other essentially meaning that the same id number will reference both the account holder and the user.



    //Service method to create a new user, should be assigned an id# upon creation
    public ResponseEntity<?> createUser(UserCreationDTO userCreationDTO) {
        //Checks if this username already exists in the UserCredentialsRepository
        if(userCredentialsRepository.existsByUsername(userCreationDTO.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(String.format("Username %s already exists", userCreationDTO.getUsername()));
        }
        UserCredentials newUserCredentials = new UserCredentials(userCreationDTO.getUsername(), passwordEncoder.encode(userCreationDTO.getPassword()));
        newUserCredentials.setAccountHolder(userCreationDTO.getAccountHolder());
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