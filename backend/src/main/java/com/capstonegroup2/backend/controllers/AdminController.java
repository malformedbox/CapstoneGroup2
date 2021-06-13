package com.capstonegroup2.backend.controllers;

import com.capstonegroup2.backend.dto.*;
import com.capstonegroup2.backend.models.*;
import com.capstonegroup2.backend.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    /* Account Holders ============================================================================================== */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAllAccountHolders() {
        return adminService.getAllAccountHolders();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountHolder getAccountHolderById(@PathVariable Long id) {
        return adminService.getAccountHolderById(id);
    }


    /* CD Accounts ================================================================================================== */
    @PostMapping("/{id}/cdaccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public CDAccount addCDAccount(@RequestBody CDAccountDTO cdAccountDTO, @PathVariable Long id) {
        AccountHolder accountHolder = adminService.getAccountHolderById(id);
        CDOffering cdOffering = adminService.getCDOfferingById(cdAccountDTO.getCdOffering().getId());
        CDAccount cdAccount = new CDAccount(cdAccountDTO.getBalance(), cdOffering);

        return adminService.addCDAccount(cdAccount, accountHolder);
    }

    @GetMapping("/{id}/cdaccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<CDAccount> getCDAccounts(@PathVariable Long id) {
        AccountHolder accountHolder = adminService.getAccountHolderById(id);
        return adminService.getCDAccounts(accountHolder);
    }


    /* Personal Checking Accounts =================================================================================== */
    @PostMapping("/{id}/personalchecking")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonalChecking addPersonalChecking(@RequestBody PersonalCheckingDTO personalCheckingDTO,
                                                @PathVariable Long id) {
        AccountHolder accountHolder = adminService.getAccountHolderById(id);
        PersonalChecking personalChecking = new PersonalChecking(personalCheckingDTO.getBalance());
        return adminService.addPersonalChecking(personalChecking, accountHolder);
    }

    @GetMapping("/{id}/personalchecking")
    @ResponseStatus(HttpStatus.OK)
    public PersonalChecking getCheckingPersonal(@PathVariable Long id) {
        AccountHolder accountHolder = adminService.getAccountHolderById(id);
        return adminService.getPersonalChecking(accountHolder);
    }

    /* DBA Checking Accounts ======================================================================================== */
    @PostMapping("/{id}/dbachecking")
    @ResponseStatus(HttpStatus.CREATED)
    public DbaChecking addDbaChecking(@RequestBody DbaCheckingDTO dbaCheckingDTO, @PathVariable Long id) {
        AccountHolder accountHolder = adminService.getAccountHolderById(id);
        DbaChecking dbaChecking = new DbaChecking(dbaCheckingDTO.getBalance());
        return adminService.addDbaChecking(dbaChecking, accountHolder);
    }

    @GetMapping("/{id}/dbachecking")
    @ResponseStatus(HttpStatus.OK)
    public List<DbaChecking> getDbaChecking(@PathVariable Long id) {
        AccountHolder accountHolder = adminService.getAccountHolderById(id);
        return adminService.getDbaChecking(accountHolder);
    }

    /* IRA Regular Accounts ========================================================================================= */
    @PostMapping("/{id}/iraregular")
    @ResponseStatus(HttpStatus.CREATED)
    public IraRegular addIraRegular(@RequestBody IraRegularDTO iraRegularDTO, @PathVariable Long id) {
        AccountHolder accountHolder = adminService.getAccountHolderById(id);
        IraRegular iraRegular = new IraRegular(iraRegularDTO.getBalance());
        return adminService.addIraRegular(iraRegular, accountHolder);
    }

    @GetMapping("/{id}/iraregular")
    @ResponseStatus(HttpStatus.OK)
    public IraRegular getIraRegular(@PathVariable Long id) {
        AccountHolder accountHolder = adminService.getAccountHolderById(id);
        return adminService.getIraRegular(accountHolder);
    }

    /* IRA Rollover Accounts ======================================================================================== */
    @PostMapping("/{id}/irarollover")
    @ResponseStatus(HttpStatus.CREATED)
    public IraRollover addIraRollover(@RequestBody IraRolloverDTO iraRolloverDTO, @PathVariable Long id) {
        AccountHolder accountHolder = adminService.getAccountHolderById(id);
        IraRollover iraRollover = new IraRollover(iraRolloverDTO.getBalance());
        return adminService.addIraRollover(iraRollover, accountHolder);
    }

    @GetMapping("/{id}/irarollover")
    @ResponseStatus(HttpStatus.OK)
    public IraRollover getIraRollover(@PathVariable Long id) {
        AccountHolder accountHolder = adminService.getAccountHolderById(id);
        return adminService.getIraRollover(accountHolder);
    }

    /* IRA Roth Accounts ============================================================================================ */
    @PostMapping("/{id}/iraroth")
    @ResponseStatus(HttpStatus.CREATED)
    public IraRoth addIraRoth(@RequestBody IraRothDTO iraRothDTO, @PathVariable Long id) {
        AccountHolder accountHolder = adminService.getAccountHolderById(id);
        IraRoth iraRoth = new IraRoth(iraRothDTO.getBalance());
        return adminService.addIraRoth(iraRoth, accountHolder);
    }

    @GetMapping("/{id}/iraroth")
    @ResponseStatus(HttpStatus.OK)
    public IraRoth getIraRoth(@PathVariable Long id) {
        AccountHolder accountHolder = adminService.getAccountHolderById(id);
        return adminService.getIraRoth(accountHolder);
    }

    /* Savings Account ============================================================================================== */
    @PostMapping("/{id}/savingsaccount")
    @ResponseStatus(HttpStatus.CREATED)
    public SavingsAccount addSavingsAccount(@RequestBody SavingsAccountDTO savingsAccountDTO, @PathVariable Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        SavingsAccount savingsAccount = new SavingsAccount(savingsAccountDTO.getBalance());
        return adminService.addSavingsAccount(savingsAccount, accountHolder);
    }

    @GetMapping("/{id}/savingsaccount")
    @ResponseStatus(HttpStatus.OK)
    public SavingsAccount getSavingsAccount(@PathVariable Long id) {
        AccountHolder accountHolder = adminService.getAccountHolderById(id);
        return adminService.getSavingsAccount(accountHolder);
    }

}
