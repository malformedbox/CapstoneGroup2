package com.capstonegroup2.backend.controllers;

import com.capstonegroup2.backend.dto.*;
import com.capstonegroup2.backend.enums.TransactionType;
import com.capstonegroup2.backend.exceptions.AccountHolderNotFoundException;
import com.capstonegroup2.backend.exceptions.AccountNotFoundException;
import com.capstonegroup2.backend.models.*;
import com.capstonegroup2.backend.repositories.UserCredentialsRepository;
import com.capstonegroup2.backend.services.LoggedInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class LoggedInController {

    @Autowired LoggedInService loggedInService;

    @Autowired UserCredentialsRepository userCredentialsRepository;

    /* Account Holder ============================================================================================== */
    @GetMapping
    public AccountHolder getLoggedInAccountHolder(@RequestHeader(name = "Authorization") String token) {
        return loggedInService.getLoggedInAccountHolder(token);
    }

    /* CD Accounts ================================================================================================== */
    @PostMapping("/cdaccounts")
    public CDAccount addCDAccount(@RequestHeader(name = "Authorization") String token,
                                  @RequestBody CDAccountDTO cdAccountDTO) throws AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        CDAccount cdAccount = new CDAccount(cdAccountDTO.getBalance(), cdAccountDTO.getCdOffering());
        return loggedInService.addCDAccount(accountHolder, cdAccount);
    }

    @GetMapping("/cdaccounts")
    public List<CDAccount> getCDAccounts(@RequestHeader(name = "Authorization") String token)
            throws AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        return loggedInService.getCDAccounts(accountHolder);
    }

    /* Personal Checking Accounts =================================================================================== */
    @PostMapping("/personalchecking")
    public PersonalChecking addPersonalChecking(@RequestHeader(name = "Authorization") String token,
                                                @RequestBody PersonalCheckingDTO personalCheckingDTO)
            throws AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        PersonalChecking personalChecking = new PersonalChecking(personalCheckingDTO.getBalance());
        return loggedInService.addPersonalChecking(accountHolder, personalChecking);
    }

    @GetMapping("/personalchecking")
    public PersonalChecking getPersonalChecking(@RequestHeader(name = "Authorization") String token)
            throws AccountHolderNotFoundException, AccountNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        return loggedInService.getPersonalChecking(accountHolder);
    }

    /* DBA Checking Accounts ======================================================================================== */
    @PostMapping("/dbachecking")
    public DbaChecking addDbaChecking(@RequestHeader(name = "Authorization") String token,
                                      @RequestBody DbaCheckingDTO dbaCheckingDTO)
            throws AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        DbaChecking dbaChecking = new DbaChecking(dbaCheckingDTO.getBalance());
        return loggedInService.addDbaChecking(accountHolder, dbaChecking);
    }

    @GetMapping("/dbachecking")
    public List<DbaChecking> getDbaChecking(@RequestHeader(name = "Authorization") String token)
            throws AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        return loggedInService.getDbaChecking(accountHolder);
    }

    /* IRA Regular Accounts ========================================================================================= */
    @PostMapping("/iraregular")
    public IraRegular addIraRegular(@RequestHeader(name = "Authorization") String token,
                                    @RequestBody IraRegularDTO iraRegularDTO) throws AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        IraRegular iraRegular = new IraRegular(iraRegularDTO.getBalance());
        return loggedInService.addIraRegular(accountHolder, iraRegular);
    }

    @GetMapping("/iraregular")
    public IraRegular getIraRegular(@RequestHeader(name = "Authorization") String token)
            throws AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        return loggedInService.getIraRegular(accountHolder);
    }

    /* IRA Rollover Accounts ======================================================================================== */
    @PostMapping("/irarollover")
    public IraRollover addIraRollover(@RequestHeader(name = "Authorization") String token,
                                      @RequestBody IraRegularDTO iraRegularDTO) throws AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        IraRollover iraRollover = new IraRollover(iraRegularDTO.getBalance());
        return loggedInService.addIraRollover(accountHolder, iraRollover);
    }

    @GetMapping("/irarollover")
    public IraRollover getIraRollover(@RequestHeader(name = "Authorization") String token)
            throws AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        return loggedInService.getIraRollover(accountHolder);
    }

    /* IRA Roth Accounts ============================================================================================ */
    @PostMapping("/iraroth")
    public IraRoth addIraRoth(@RequestHeader(name = "Authorization") String token,
                              @RequestBody IraRothDTO iraRothDTO) throws AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        IraRoth iraRoth = new IraRoth(iraRothDTO.getBalance());
        return loggedInService.addIraRoth(accountHolder, iraRoth);
    }

    @GetMapping("/iraroth")
    public IraRoth getIraRoth(@RequestHeader(name = "Authorization") String token)
            throws AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        return loggedInService.getIraRoth(accountHolder);
    }

    /* Savings Accounts ============================================================================================= */
    @PostMapping("/savings")
    public SavingsAccount addSavingsAccount(@RequestHeader(name = "Authorization") String token,
                                            @RequestBody SavingsAccountDTO savingsAccountDTO)
            throws AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        SavingsAccount savingsAccount = new SavingsAccount(savingsAccountDTO.getBalance());
        return loggedInService.addSavingsAccount(accountHolder, savingsAccount);
    }

    @GetMapping("/savings")
    public SavingsAccount getSavingsAccount(@RequestHeader(name = "Authorization") String token)
            throws AccountHolderNotFoundException {
        AccountHolder accountHolder = loggedInService.getLoggedInAccountHolder(token);
        return loggedInService.getSavingsAccount(accountHolder);
    }

    /* Transactions ================================================================================================= */

    // OPTION A
    @PostMapping("/transaction")
    public Transaction postTransaction(@RequestHeader(name = "Authorization") String token,
                                   @RequestBody TransactionDTO transactionDTO) throws AccountNotFoundException {

        // Constructing and Routing transaction object based on type
        BankAccount targetAccount = loggedInService.getAccountByAccountNumber(transactionDTO.getTargetAccountNumber());

        if (transactionDTO.getTransactionType() != TransactionType.TRANSFER) {

            Transaction transaction = new Transaction(transactionDTO.getAmount(), transactionDTO.getTransactionType(),
                    targetAccount);

            if (transaction.getTransactionType() == TransactionType.DEPOSIT) {
                return loggedInService.postDeposit(transaction);

            } else if (transaction.getTransactionType() == TransactionType.WITHDRAWAL) {
                return loggedInService.postWithdrawal(transaction);
            }

        } else if (transactionDTO.getTransactionType() == TransactionType.TRANSFER) {

            BankAccount sourceAccount = loggedInService.getAccountByAccountNumber(
                    transactionDTO.getSourceAccountNumber());
            Transaction transaction = new Transaction(transactionDTO.getAmount(), transactionDTO.getTransactionType(),
                    sourceAccount, targetAccount);

            return loggedInService.postTransfer(transaction);
        }
        return null;
    }

    // OPTIONS B
    @PostMapping("/deposit")
    public Transaction postDeposit(@RequestHeader(name = "Authorization") String token,
                               @RequestBody TransactionDTO transactionDTO) throws AccountNotFoundException {
        BankAccount bankAccount = loggedInService.getAccountByAccountNumber(transactionDTO.getTargetAccountNumber());
        Transaction transaction = new Transaction(transactionDTO.getAmount(), transactionDTO.getTransactionType(),
                bankAccount);
        return loggedInService.postDeposit(transaction);
    }

    @PostMapping("/withdraw")
    public Transaction postWithdrawal(@RequestHeader(name = "Authorization") String token,
                                  @RequestBody TransactionDTO transactionDTO) throws AccountNotFoundException {
        BankAccount bankAccount = loggedInService.getAccountByAccountNumber(transactionDTO.getTargetAccountNumber());
        Transaction transaction = new Transaction(transactionDTO.getAmount(), transactionDTO.getTransactionType(),
                bankAccount);
        return loggedInService.postWithdrawal(transaction);
    }

    @PostMapping("/transfer")
    public Transaction postTransfer(@RequestHeader(name = "Authorization") String token,
                                @RequestBody TransactionDTO transactionDTO) throws AccountNotFoundException {
        BankAccount sourceAccount = loggedInService.getAccountByAccountNumber(transactionDTO.getSourceAccountNumber());
        BankAccount targetAccount = loggedInService.getAccountByAccountNumber(transactionDTO.getTargetAccountNumber());
        Transaction transaction = new Transaction(transactionDTO.getAmount(), transactionDTO.getTransactionType(),
                sourceAccount, targetAccount);
        return loggedInService.postTransfer(transaction);
    }


}
