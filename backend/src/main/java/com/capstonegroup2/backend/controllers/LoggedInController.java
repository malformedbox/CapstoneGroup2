package com.capstonegroup2.backend.controllers;

import com.capstonegroup2.backend.dto.CDAccountDTO;
import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.CDAccount;
import com.capstonegroup2.backend.services.LoggedInService;
import com.capstonegroup2.backend.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/loggedin")
public class LoggedInController {

    @Autowired
    LoggedInService loggedInService;

    @GetMapping
    public AccountHolder getLoggedInAccountHolder(@RequestHeader(name = "Authorization") String token) {
        return loggedInService.getLoggedInAccountHolder(token);
    }

    @PostMapping("/cdaccounts")
    public CDAccount postCDAccount(@RequestHeader(name = "Authroization") String token, CDAccountDTO cdAccountDTO) {
        return loggedInService.postCDAccount(token, cdAccountDTO);
    }

}
