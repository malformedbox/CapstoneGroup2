package com.capstonegroup2.backend.controllers;

import com.capstonegroup2.backend.dto.UserCredentialsDTO;
import com.capstonegroup2.backend.models.UserCredentials;
import com.capstonegroup2.backend.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/UserCredentials")
public class UserController {

    @Autowired
    UserDetailsService userDetailsService;

    /* User Details ================================================================================================= */
    @GetMapping
    public List<UserCredentials> getAllUserDetails(){
        return userDetailsService.getAllUserDetails();
    }

    @GetMapping("/{id}")
    public UserCredentials getAccountHoldersUserDetails(@PathVariable Long id) {
        return userDetailsService.getAccountHoldersUserDetailsById(id);
    }

    @PostMapping
    public UserCredentials addAccountHoldersContactDetails(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        return userDetailsService.addAccountHoldersUserDetails(userCredentialsDTO);
    }

}
