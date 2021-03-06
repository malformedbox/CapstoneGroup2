package com.capstonegroup2.backend.controllers;

import com.capstonegroup2.backend.dto.RegisterDTO;
import com.capstonegroup2.backend.dto.LoginDTO;
import com.capstonegroup2.backend.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuthenticationController {

    @Autowired AuthenticationService authenticationService;

    //Controller method to create a new user, should be assigned an id# upon creation,
    //should not display password in response body
    @PostMapping("/createuser")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@RequestBody @Valid RegisterDTO registerDTO) {
        return authenticationService.createUser(registerDTO);
    }

    //Controller method that should take user's credentials (username + password in body)
    //Should return a jwt token
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginDTO loginDTO) throws Exception{
        return authenticationService.authenticateUser(loginDTO);
    }

}