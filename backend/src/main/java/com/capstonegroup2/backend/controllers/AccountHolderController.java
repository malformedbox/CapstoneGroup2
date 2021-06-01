package com.capstonegroup2.backend.controllers;

import com.capstonegroup2.backend.dto.*;
import com.capstonegroup2.backend.models.*;
import com.capstonegroup2.backend.services.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/AccountHolders")
public class AccountHolderController {

    @GetMapping("/Test")
    public String test() {
        return "<h1>Hello World!</h1>";
    }

    @Autowired
    AccountHolderService accountHolderService;

    /* CD Accounts ================================================================================================== */
    @PostMapping("/{id}/CDAccounts")
    public CDAccount addCDAccount(@RequestBody CDAccountDTO cdAccountDTO, @PathVariable Long id) {
        return accountHolderService.addCDAccount(cdAccountDTO, id);
    }

    @GetMapping("/{id}/CDAccounts")
    public List<CDAccount> getCDAccounts(@PathVariable Long id) {
        return accountHolderService.getCDAccounts(id);
    }

    /* Personal Checking Accounts =================================================================================== */
    @PostMapping("/{id}/PersonalChecking")
    public PersonalChecking addPersonalChecking(@RequestBody PersonalCheckingDTO personalCheckingDTO,
                                                @PathVariable Long id) {
        return accountHolderService.addPersonalChecking(personalCheckingDTO, id);
    }

    @GetMapping("/{id}/PersonalChecking")
    public PersonalChecking getCheckingPersonal(@PathVariable Long id) {
        return accountHolderService.getPersonalChecking(id);
    }

    /* DBA Checking Accounts ======================================================================================== */
    @PostMapping("/{id}/DbaChecking")
    public DbaChecking addDbaChecking(@RequestBody DbaCheckingDTO dbaCheckingDTO, @PathVariable Long id) {
        return accountHolderService.addDbaChecking(dbaCheckingDTO, id);
    }

    @GetMapping("/{id}/DbaChecking")
    public List<DbaChecking> getDbaChecking(@PathVariable Long id) {
        return accountHolderService.getDbaChecking(id);
    }

    /* IRA Regular Accounts ========================================================================================= */
    @PostMapping("/{id}/IraRegular")
    public IraRegular addIraRegular(@RequestBody IraRegularDTO iraRegularDTO, @PathVariable Long id) {
        return accountHolderService.addIraRegular(iraRegularDTO, id);
    }

    @GetMapping("/{id}/IraRegular")
    public IraRegular getIraRegular(@PathVariable Long id) {
        return accountHolderService.getIraRegular(id);
    }

    /* IRA Rollover Accounts ======================================================================================== */
    @PostMapping("/{id}/IraRollover")
    public IraRollover addIraRollover(@RequestBody IraRolloverDTO iraRolloverDTO, @PathVariable Long id) {
        return accountHolderService.addIraRollover(iraRolloverDTO, id);
    }

    @GetMapping("/{id}/IraRollover")
    public IraRollover getIraRollover(@PathVariable Long id) {
        return accountHolderService.getIraRollover(id);
    }

    /* IRA Roth Accounts ============================================================================================ */

    /* Savings Accounts ============================================================================================= */
}
