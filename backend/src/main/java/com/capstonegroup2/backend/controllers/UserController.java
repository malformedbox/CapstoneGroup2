package com.capstonegroup2.backend.controllers;

import com.capstonegroup2.backend.dto.UserCredentialsDTO;
import com.capstonegroup2.backend.models.UserCredentials;
import com.capstonegroup2.backend.services.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/usercredentials")
public class UserController {

    @Autowired
    UserCredentialsService userCredentialsService;

    /* User Details ================================================================================================= */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserCredentials> getAllUserDetails(){
        return userCredentialsService.getAllUserDetails();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserCredentials getAccountHoldersUserDetails(@PathVariable Long id) {
        return userCredentialsService.getAccountHoldersUserDetailsById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserCredentials addAccountHoldersContactDetails(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        return userCredentialsService.addAccountHoldersUserDetails(userCredentialsDTO);
    }

}
