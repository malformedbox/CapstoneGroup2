package com.capstonegroup2.backend.controllers;

import com.capstonegroup2.backend.dto.*;
import com.capstonegroup2.backend.exceptions.AccountHolderNotFoundException;
import com.capstonegroup2.backend.exceptions.AccountNotFoundException;
import com.capstonegroup2.backend.models.*;
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

    /* Account Holder ============================================================================================== */
    @GetMapping
    public AccountHolder getLoggedInAccountHolder(@RequestHeader(name = "Authorization") String token) {
        return loggedInService.getLoggedInAccountHolder(token);
    }

    @PostMapping("/createaccountholder")
    public AccountHolder createLoggedInAccountHolder(@RequestHeader(name = "Authorization") String token,
                                                     AccountHolderDTO accountHolderDTO) {
        return loggedInService.createLoggedInAccountHolder(token, accountHolderDTO);
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

    /* Personal Checking Accounts =================================================================================== */
    @PostMapping("/personalchecking")
    public PersonalChecking addLoggedInPersonalChecking(@RequestHeader(name = "Authorization") String token,
                                                        PersonalCheckingDTO personalCheckingDTO)
            throws AccountHolderNotFoundException {
        return loggedInService.addLoggedInPersonalChecking(token, personalCheckingDTO);
    }

    @GetMapping("/personalchecking")
    public PersonalChecking getLoggedInPersonalChecking(@RequestHeader(name = "Authorization") String token)
            throws AccountHolderNotFoundException {
        return loggedInService.getLoggedInPersonalChecking(token);
    }

    @PostMapping("/personalchecking/deposit")
    public Transaction depositIntoPersonalChecking(@RequestHeader(name = "Authorization")
                                                               String token, TransactionDTO transactionDTO)
            throws AccountNotFoundException, AccountHolderNotFoundException {
        return loggedInService.depositIntoPersonalChecking(token, transactionDTO);
    }

    @PostMapping("/dbachecking")
    public DbaChecking addDbaChecking(@RequestHeader(name = "Authorization") String token, DbaCheckingDTO dbaCheckingDTO) {
        return loggedInService.addDbaChecking(token, dbaCheckingDTO);
    }
}
