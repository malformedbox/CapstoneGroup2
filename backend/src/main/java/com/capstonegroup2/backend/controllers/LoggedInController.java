package com.capstonegroup2.backend.controllers;

import com.capstonegroup2.backend.dto.CDAccountDTO;
import com.capstonegroup2.backend.dto.TransactionDTO;
import com.capstonegroup2.backend.exceptions.AccountHolderNotFoundException;
import com.capstonegroup2.backend.exceptions.AccountNotFoundException;
import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.CDAccount;
import com.capstonegroup2.backend.models.Transaction;
import com.capstonegroup2.backend.services.LoggedInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class LoggedInController {

    @Autowired
    LoggedInService loggedInService;

    @GetMapping
    public AccountHolder getLoggedInAccountHolder(@RequestHeader(name = "Authorization") String token) {
        return loggedInService.getLoggedInAccountHolder(token);
    }

    /* CD Accounts ================================================================================================== */
    @PostMapping("/cdaccounts")
    public CDAccount addLoggedInCDAccount(@RequestHeader(name = "Authorization") String token, CDAccountDTO cdAccountDTO)
            throws AccountHolderNotFoundException {
        return loggedInService.addLoggedInCDAccount(token, cdAccountDTO);
    }

    @GetMapping("/cdaccounts")
    public List<CDAccount> getLoggedInCDAccounts(@RequestHeader(name = "Authorization") String token)
            throws AccountHolderNotFoundException {
        return loggedInService.getLoggedInCDAccounts(token);
    }

    @PostMapping("/personalchecking/deposit")
    public Transaction depositIntoPersonalChecking(@RequestHeader(name = "Authorization")
                                                               String token, TransactionDTO transactionDTO)
            throws AccountNotFoundException, AccountHolderNotFoundException {
        return loggedInService.depositIntoPersonalChecking(token, transactionDTO);
    }
}
