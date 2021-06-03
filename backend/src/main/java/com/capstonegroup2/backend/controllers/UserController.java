package com.capstonegroup2.backend.controllers;

import com.capstonegroup2.backend.dto.UserDetailsDTO;
import com.capstonegroup2.backend.models.UserDetails;
import com.capstonegroup2.backend.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/userdetails")
public class UserController {

    @Autowired
    UserDetailsService userDetailsService;

    /* User Details ================================================================================================= */
    @GetMapping
    public List<UserDetails> getAllUserDetails(){
        return userDetailsService.getAllUserDetails();
    }

    @GetMapping("/{id}")
    public UserDetails getAccountHoldersUserDetails(@PathVariable Long id) {
        return userDetailsService.getAccountHoldersUserDetailsById(id);
    }

    @PostMapping
    public UserDetails addAccountHoldersContactDetails(@RequestBody UserDetailsDTO userDetailsDTO) {
        return userDetailsService.addAccountHoldersUserDetails(userDetailsDTO);
    }

}
