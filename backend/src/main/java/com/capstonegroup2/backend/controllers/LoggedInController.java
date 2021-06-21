package com.capstonegroup2.backend.controllers;

import com.capstonegroup2.backend.dto.*;
import com.capstonegroup2.backend.exceptions.AccountHolderNotFoundException;
import com.capstonegroup2.backend.exceptions.AccountLimitExceededException;
import com.capstonegroup2.backend.exceptions.AccountNotFoundException;
import com.capstonegroup2.backend.exceptions.OfferingNotFoundException;
import com.capstonegroup2.backend.models.*;
import com.capstonegroup2.backend.services.CDOfferingService;
import com.capstonegroup2.backend.services.LoggedInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class LoggedInController {

    @Autowired LoggedInService loggedInService;

    @Autowired CDOfferingService cdOfferingService;


    /* Account Holder =============================================================================================== */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public AccountHolder getLoggedInAccountHolder(@RequestHeader(name = "Authorization") String token) {
        return loggedInService.getLoggedInAccountHolder(token);
    }

    // TODO TEST
    @DeleteMapping("/delete")
    public String deleteLoggedInAccountHolder(@RequestHeader(name = "Authorization") String token)
            throws AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);

        return loggedInService.deleteLoggedInAccountHolder(accountHolder);
    }


    /* CD Accounts ================================================================================================== */
    // TODO TEST
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cdaccounts")
    public CDAccount addCDAccount(@RequestHeader(name = "Authorization") String token,
                                  @RequestBody CDAccountDTO cdAccountDTO)
            throws AccountHolderNotFoundException, OfferingNotFoundException, AccountNotFoundException {

        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        CDOffering cdOffering = cdOfferingService.getCDOfferingById(cdAccountDTO.getCdOffering().getId());
        CDAccount cdAccount = new CDAccount(cdAccountDTO.getBalance(), cdOffering);

        return loggedInService.addCDAccount(accountHolder, cdAccount);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cdaccounts")
    public List<CDAccount> getCDAccounts(@RequestHeader(name = "Authorization") String token)
            throws AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        return loggedInService.getCDAccounts(accountHolder);
    }


    /* Personal Checking Accounts =================================================================================== */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/personalchecking")
    public PersonalChecking addPersonalChecking(@RequestHeader(name = "Authorization") String token,
                                                @RequestBody PersonalCheckingDTO personalCheckingDTO)
            throws AccountHolderNotFoundException, AccountLimitExceededException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        PersonalChecking personalChecking = new PersonalChecking(personalCheckingDTO.getBalance());
        return loggedInService.addPersonalChecking(accountHolder, personalChecking);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/personalchecking")
    public PersonalChecking getPersonalChecking(@RequestHeader(name = "Authorization") String token)
            throws AccountHolderNotFoundException, AccountNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        return loggedInService.getPersonalChecking(accountHolder);
    }

    // TODO FIX - currently deletes everything related to the account holder
    @DeleteMapping("/personalchecking")
    public Transaction deletePersonalChecking(@RequestHeader(name = "Authorization") String token)
            throws AccountNotFoundException, AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        return loggedInService.closePersonalChecking(accountHolder);
    }


    /* DBA Checking Accounts ======================================================================================== */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/dbachecking")
    public DbaChecking addDbaChecking(@RequestHeader(name = "Authorization") String token,
                                      @RequestBody DbaCheckingDTO dbaCheckingDTO)
            throws AccountHolderNotFoundException, AccountLimitExceededException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        DbaChecking dbaChecking = new DbaChecking(dbaCheckingDTO.getBalance());
        return loggedInService.addDbaChecking(accountHolder, dbaChecking);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/dbachecking")
    public List<DbaChecking> getDbaChecking(@RequestHeader(name = "Authorization") String token)
            throws AccountHolderNotFoundException, AccountNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        return loggedInService.getDbaChecking(accountHolder);
    }


    /* IRA Regular Accounts ========================================================================================= */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/iraregular")
    public IraRegular addIraRegular(@RequestHeader(name = "Authorization") String token,
                                    @RequestBody IraRegularDTO iraRegularDTO)
            throws AccountHolderNotFoundException, AccountLimitExceededException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        IraRegular iraRegular = new IraRegular(iraRegularDTO.getBalance());
        return loggedInService.addIraRegular(accountHolder, iraRegular);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/iraregular")
    public IraRegular getIraRegular(@RequestHeader(name = "Authorization") String token)
            throws AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        return loggedInService.getIraRegular(accountHolder);
    }

    // TODO TEST
    @DeleteMapping("/iraregular")
    public Transaction deleteIraRegular(@RequestHeader(name = "Authorization") String token)
            throws AccountNotFoundException, AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        return loggedInService.closeIraRegular(accountHolder);
    }

    /* IRA Rollover Accounts ======================================================================================== */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/irarollover")
    public IraRollover addIraRollover(@RequestHeader(name = "Authorization") String token,
                                      @RequestBody IraRegularDTO iraRegularDTO)
            throws AccountHolderNotFoundException, AccountLimitExceededException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        IraRollover iraRollover = new IraRollover(iraRegularDTO.getBalance());
        return loggedInService.addIraRollover(accountHolder, iraRollover);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/irarollover")
    public IraRollover getIraRollover(@RequestHeader(name = "Authorization") String token)
            throws AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        return loggedInService.getIraRollover(accountHolder);
    }


    /* IRA Roth Accounts ============================================================================================ */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/iraroth")
    public IraRoth addIraRoth(@RequestHeader(name = "Authorization") String token,
                              @RequestBody IraRothDTO iraRothDTO)
            throws AccountHolderNotFoundException, AccountLimitExceededException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        IraRoth iraRoth = new IraRoth(iraRothDTO.getBalance());
        return loggedInService.addIraRoth(accountHolder, iraRoth);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/iraroth")
    public IraRoth getIraRoth(@RequestHeader(name = "Authorization") String token)
            throws AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        return loggedInService.getIraRoth(accountHolder);
    }


    /* Savings Accounts ============================================================================================= */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/savings")
    public SavingsAccount addSavingsAccount(@RequestHeader(name = "Authorization") String token,
                                            @RequestBody SavingsAccountDTO savingsAccountDTO)
            throws AccountHolderNotFoundException, AccountLimitExceededException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        SavingsAccount savingsAccount = new SavingsAccount(savingsAccountDTO.getBalance());
        return loggedInService.addSavingsAccount(accountHolder, savingsAccount);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/savings")
    public SavingsAccount getSavingsAccount(@RequestHeader(name = "Authorization") String token)
            throws AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        return loggedInService.getSavingsAccount(accountHolder);
    }


    /* Transactions ================================================================================================= */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/transaction")
    public Transaction postTransaction(@RequestHeader(name = "Authorization") String token,
                                       @RequestBody TransactionDTO transactionDTO) throws AccountNotFoundException {

        // Constructing and Routing transaction object based on type

        BankAccount targetAccount = loggedInService.getAccountByAccountNumber(transactionDTO.getTargetAccountNumber());

        switch (transactionDTO.getTransactionType()) {
            case DEPOSIT:
                Transaction depositTransaction = new Transaction(transactionDTO.getAmount(),
                        transactionDTO.getTransactionType(), targetAccount);
                return loggedInService.postDeposit(depositTransaction);

            case WITHDRAWAL:
                Transaction withdrawalTransaction = new Transaction(transactionDTO.getAmount(),
                        transactionDTO.getTransactionType(), targetAccount);
                return loggedInService.postWithdrawal(withdrawalTransaction);

            case TRANSFER:
                BankAccount sourceAccount = loggedInService.getAccountByAccountNumber(
                        transactionDTO.getSourceAccountNumber());
                Transaction transaction = new Transaction(transactionDTO.getAmount(),
                        transactionDTO.getTransactionType(), sourceAccount, targetAccount);

                return loggedInService.postTransfer(transaction);
        }
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/transactions/{accountNumber}")
    public List<Transaction> getAccountTransactions(@RequestHeader(name = "Authorization") String token,
                                                    @PathVariable long accountNumber)
            throws AccountNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        BankAccount bankAccount = loggedInService.getAccountByAccountNumber(accountNumber);
        return loggedInService.getAccountTransactions(bankAccount);
    }

}
