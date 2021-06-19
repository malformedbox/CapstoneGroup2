package com.capstonegroup2.backend.controllers;

import com.capstonegroup2.backend.dto.LoginDTO;
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
    public List<UserCredentials> getAllUserCredentials(){
        return userCredentialsService.getAllUserCredentials();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserCredentials getUserCredentialsById(@PathVariable Long id) {
        return userCredentialsService.getUserCredentialsById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserCredentials addUserCredentials(@RequestBody LoginDTO loginDTO) {
        return userCredentialsService.addUserCredentials(loginDTO);
    }

}
