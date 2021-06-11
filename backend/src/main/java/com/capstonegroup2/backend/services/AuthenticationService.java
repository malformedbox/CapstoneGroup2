package com.capstonegroup2.backend.services;

import com.capstonegroup2.backend.controllers.AccountHolderController;
import com.capstonegroup2.backend.dto.ResponseMessage;
import com.capstonegroup2.backend.dto.UserCreationDTO;
import com.capstonegroup2.backend.dto.UserCredentialsDTO;
import com.capstonegroup2.backend.models.RoleName;
import com.capstonegroup2.backend.models.Role;
import com.capstonegroup2.backend.models.UserCredentials;
import com.capstonegroup2.backend.repositories.RoleRepository;
import org.springframework.security.core.userdetails.UserDetails;
import com.capstonegroup2.backend.repositories.UserCredentialsRepository;
import com.capstonegroup2.backend.security.JwtResponse;
import com.capstonegroup2.backend.security.JwtTokenCreator;
import com.capstonegroup2.backend.security.UserCredentialsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    @Autowired
    RoleRepository roleRepository;

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
        if (userCredentialsRepository.existsByUsername(userCreationDTO.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username already exists!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        UserCredentials user = new UserCredentials(userCreationDTO.getUsername(), passwordEncoder.encode(userCreationDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        if(userCreationDTO.getRole() != null) {
            Set<String> strRoles = userCreationDTO.getRole();

            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Admin Role not find."));
                        roles.add(adminRole);

                        break;

                    default:
                        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                        roles.add(userRole);
                }
            });
        } else {
            // default mode : User register
            List<Role> rolez = roleRepository.findAll();
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Default User Role not find."));
            roles.add(userRole);
        }

        user.setRoles(roles);
        user.setAccountHolder(userCreationDTO.getAccountHolder());
        userCredentialsRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage("Account for "+ userCreationDTO.getAccountHolder().getFirstName() + ", is registered successfully!"), HttpStatus.OK);
    }

    //Service method that should take user's credentials (username + password in body)
    //Should return a jwt token
    public ResponseEntity<?> authenticateUser(UserCredentialsDTO userCredentialsDTO) throws Exception{
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredentialsDTO.getUsername(), userCredentialsDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenCreator.generateToken(authentication);
        UserDetails  userCredentials = (UserDetails ) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userCredentials.getUsername(), userCredentials.getAuthorities()));
    }
}