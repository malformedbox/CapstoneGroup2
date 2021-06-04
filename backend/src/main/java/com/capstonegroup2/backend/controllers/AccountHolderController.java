package com.capstonegroup2.backend.controllers;

import com.capstonegroup2.backend.dto.*;
import com.capstonegroup2.backend.models.*;
import com.capstonegroup2.backend.services.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/accountholders")
public class AccountHolderController {

    @Autowired
    AccountHolderService accountHolderService;

    /* Account Holders ============================================================================================== */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder addAccountHolder(@Valid @RequestBody AccountHolderDTO accountHolderDTO) {
        return accountHolderService.addAccountHolder(accountHolderDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAllAccountHolders() {
        return accountHolderService.getAllAccountHolders();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountHolder getAccountHolderById(@PathVariable Long id) {
        return accountHolderService.getAccountHolderById(id);
    }


    /* CD Accounts ================================================================================================== */
    @PostMapping("/{id}/cdaccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public CDAccount addCDAccount(@RequestBody CDAccountDTO cdAccountDTO, @PathVariable Long id) {
        return accountHolderService.addCDAccount(cdAccountDTO, id);
    }

    @GetMapping("/{id}/cdaccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<CDAccount> getCDAccounts(@PathVariable Long id) {
        return accountHolderService.getCDAccounts(id);
    }


    /* Personal Checking Accounts =================================================================================== */
    @PostMapping("/{id}/personalchecking")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonalChecking addPersonalChecking(@RequestBody PersonalCheckingDTO personalCheckingDTO,
                                                @PathVariable Long id) {
        return accountHolderService.addPersonalChecking(personalCheckingDTO, id);
    }

    @GetMapping("/{id}/personalchecking")
    @ResponseStatus(HttpStatus.OK)
    public PersonalChecking getCheckingPersonal(@PathVariable Long id) {
        return accountHolderService.getPersonalChecking(id);
    }

    /* DBA Checking Accounts ======================================================================================== */
    @PostMapping("/{id}/dbachecking")
    @ResponseStatus(HttpStatus.CREATED)
    public DbaChecking addDbaChecking(@RequestBody DbaCheckingDTO dbaCheckingDTO, @PathVariable Long id) {
        return accountHolderService.addDbaChecking(dbaCheckingDTO, id);
    }

    @GetMapping("/{id}/dbachecking")
    @ResponseStatus(HttpStatus.OK)
    public List<DbaChecking> getDbaChecking(@PathVariable Long id) {
        return accountHolderService.getDbaChecking(id);
    }

    /* IRA Regular Accounts ========================================================================================= */
    @PostMapping("/{id}/iraregular")
    @ResponseStatus(HttpStatus.CREATED)
    public IraRegular addIraRegular(@RequestBody IraRegularDTO iraRegularDTO, @PathVariable Long id) {
        return accountHolderService.addIraRegular(iraRegularDTO, id);
    }

    @GetMapping("/{id}/iraregular")
    @ResponseStatus(HttpStatus.OK)
    public IraRegular getIraRegular(@PathVariable Long id) {
        return accountHolderService.getIraRegular(id);
    }

    /* IRA Rollover Accounts ======================================================================================== */
    @PostMapping("/{id}/irarollover")
    @ResponseStatus(HttpStatus.CREATED)
    public IraRollover addIraRollover(@RequestBody IraRolloverDTO iraRolloverDTO, @PathVariable Long id) {
        return accountHolderService.addIraRollover(iraRolloverDTO, id);
    }

    @GetMapping("/{id}/irarollover")
    @ResponseStatus(HttpStatus.OK)
    public IraRollover getIraRollover(@PathVariable Long id) {
        return accountHolderService.getIraRollover(id);
    }

    /* IRA Roth Accounts ============================================================================================ */
    @PostMapping("/{id}/iraroth")
    @ResponseStatus(HttpStatus.CREATED)
    public IraRoth addIraRoth(@RequestBody IraRothDTO iraRothDTO, @PathVariable Long id) {
        return accountHolderService.addIraRoth(iraRothDTO, id);
    }

    @GetMapping("/{id}/iraroth")
    @ResponseStatus(HttpStatus.OK)
    public IraRoth getIraRoth(@PathVariable Long id) {
        return accountHolderService.getIraRoth(id);
    }

    /* Savings Account ============================================================================================== */
    @PostMapping("/{id}/savingsaccount")
    @ResponseStatus(HttpStatus.CREATED)
    public SavingsAccount addSavingsAccount(@RequestBody SavingsAccountDTO savingsAccountDTO, @PathVariable Long id) {
        return accountHolderService.addSavingsAccount(savingsAccountDTO, id);
    }

    @GetMapping("/{id}/savingsaccount")
    @ResponseStatus(HttpStatus.OK)
    public SavingsAccount getSavingsAccount(@PathVariable Long id) {
        return accountHolderService.getSavingsAccount(id);
    }

    /* Transactions ================================================================================================= */
//    @PostMapping("/{id}/transactions")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Transaction addTransaction(@RequestBody TransactionDTO transactionDTO, @PathVariable Long id) {
//        return accountHolderService.addTransaction(transactionDTO, id);
//    }
//
//    @GetMapping("/{id}/transactions")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Transaction> getTransactions(@PathVariable Long id) {
//        return accountHolderService.getTransactions(id);
//    }
}
