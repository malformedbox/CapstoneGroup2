package com.capstonegroup2.backend.services;

import com.capstonegroup2.backend.exceptions.AccountHolderNotFoundException;
import com.capstonegroup2.backend.exceptions.AccountLimitExceededException;
import com.capstonegroup2.backend.exceptions.AccountNotFoundException;
import com.capstonegroup2.backend.models.*;
import com.capstonegroup2.backend.repositories.*;
import com.capstonegroup2.backend.security.JwtTokenCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class LoggedInService {

    @Autowired
    AdminService adminService;

    @Autowired UserCredentialsRepository userCredentialsRepository;

    @Autowired BankAccountRepository bankAccountRepository;

    @Autowired CDAccountRepository cdAccountRepository;

    @Autowired PersonalCheckingRepository personalCheckingRepository;

    @Autowired DbaCheckingRepository dbaCheckingRepository;

    @Autowired IraRegularRepository iraRegularRepository;

    @Autowired IraRolloverRepository iraRolloverRepository;

    @Autowired IraRothRepository iraRothRepository;

    @Autowired SavingsAccountRepository savingsAccountRepository;

    @Autowired TransactionRepository transactionRepository;

    @Autowired JwtTokenCreator jwtTokenCreator;


    /* Account Holder ============================================================================================== */
    public AccountHolder getLoggedInAccountHolder(String token) {
        token = token.substring(7);
        UserCredentials user = userCredentialsRepository.findByUsername(jwtTokenCreator.extractUsername(token)).get();
        return adminService.getAccountHolderById(user.getAccountHolder().getId());
    }

    public BankAccount getAccountByAccountNumber(long accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber);
    }

    /* CD Accounts ================================================================================================== */
    public CDAccount addCDAccount(AccountHolder accountHolder, CDAccount cdAccount)
            throws AccountHolderNotFoundException {
        if (accountHolder == null) {
            throw new AccountHolderNotFoundException("Account Holder could not be located : CD Account failed to post");
        }
        cdAccount.setAccountHolder(accountHolder);
        return cdAccountRepository.save(cdAccount);
    }

    public List<CDAccount> getCDAccounts(AccountHolder accountHolder) throws AccountHolderNotFoundException {
        if (accountHolder == null) throw new AccountHolderNotFoundException();
        return cdAccountRepository.findByAccountHolder(accountHolder);
    }

    /* Personal Checking Accounts =================================================================================== */
    public PersonalChecking addPersonalChecking(AccountHolder accountHolder, PersonalChecking personalChecking)
            throws AccountHolderNotFoundException, AccountLimitExceededException {
        if (accountHolder == null) throw new AccountHolderNotFoundException();
        if (accountHolder.getPersonalChecking() != null) throw new AccountLimitExceededException("An account holder" +
                " may only have one personal checking account.");
        personalChecking.setAccountHolder(accountHolder);
        return personalCheckingRepository.save(personalChecking);
    }

    public PersonalChecking getPersonalChecking(AccountHolder accountHolder)
            throws AccountHolderNotFoundException, AccountNotFoundException {
        if (accountHolder ==  null) throw new AccountHolderNotFoundException();
        PersonalChecking personalChecking = personalCheckingRepository.findByAccountHolder(accountHolder);
        if (personalChecking == null) throw new AccountNotFoundException();
        return personalChecking;
    }

    /* DBA Checking Accounts ======================================================================================== */
    // TODO adding validation for number of Dba Accounts - Limit is 3 per holder
    public DbaChecking addDbaChecking(AccountHolder accountHolder, DbaChecking dbaChecking)
            throws AccountHolderNotFoundException, AccountLimitExceededException {
        if (accountHolder == null) throw new AccountHolderNotFoundException();

        int numberOfDbaAccounts = accountHolder.numbertOfHoldersExistingDbaAccounts(accountHolder);
        if (numberOfDbaAccounts >= 3) throw new AccountLimitExceededException("An account holder may not have more " +
                "than 3 DBA Checking accounts.");

        dbaChecking.setAccountHolder(accountHolder);
        return dbaCheckingRepository.save(dbaChecking);
    }

    public List<DbaChecking> getDbaChecking(AccountHolder accountHolder) throws AccountHolderNotFoundException {
        if (accountHolder == null) throw new AccountHolderNotFoundException();
        return dbaCheckingRepository.findByAccountHolder(accountHolder);
    }

    /* IRA Regular Accounts ========================================================================================= */
    public IraRegular addIraRegular(AccountHolder accountHolder, IraRegular iraRegular)
            throws AccountHolderNotFoundException, AccountLimitExceededException {
        if (accountHolder ==  null) throw new AccountHolderNotFoundException();
        if (accountHolder.getIraRegular() != null) throw new AccountLimitExceededException("An account holder may not" +
                " have more than one IRA Regular account.");
        iraRegular.setAccountHolder(accountHolder);
        return iraRegularRepository.save(iraRegular);
    }

    public IraRegular getIraRegular(AccountHolder accountHolder) throws AccountHolderNotFoundException {
        if (accountHolder == null) throw new AccountHolderNotFoundException();
        return iraRegularRepository.findByAccountHolder(accountHolder);
    }

    /* IRA Rollover Accounts ======================================================================================== */
    public IraRollover addIraRollover(AccountHolder accountHolder, IraRollover iraRollover)
            throws AccountHolderNotFoundException, AccountLimitExceededException {
        if (accountHolder == null) throw new AccountHolderNotFoundException();
        if (accountHolder.getIraRollover() != null) throw new AccountLimitExceededException("An account holder may" +
                " not have more than one IRA Rollover account.");
        iraRollover.setAccountHolder(accountHolder);
        return iraRolloverRepository.save(iraRollover);
    }

    public IraRollover getIraRollover(AccountHolder accountHolder) throws AccountHolderNotFoundException {
        if (accountHolder == null) throw new AccountHolderNotFoundException();
        return iraRolloverRepository.findByAccountHolder(accountHolder);
    }

    /* IRA Roth Accounts ============================================================================================ */
    public IraRoth addIraRoth(AccountHolder accountHolder, IraRoth iraRoth) throws AccountHolderNotFoundException, AccountLimitExceededException {
        if (accountHolder == null) throw new AccountHolderNotFoundException();
        if (accountHolder.getIraRoth() != null) throw new AccountLimitExceededException("An account holder may not" +
                " have more than one IRA Roth account.");
        iraRoth.setAccountHolder(accountHolder);
        return iraRothRepository.save(iraRoth);
    }

    public IraRoth getIraRoth(AccountHolder accountHolder) throws AccountHolderNotFoundException {
        if (accountHolder == null) throw new AccountHolderNotFoundException();
        return iraRothRepository.findByAccountHolder(accountHolder);
    }

    /* Savings Accounts ============================================================================================= */
    public SavingsAccount addSavingsAccount(AccountHolder accountHolder, SavingsAccount savingsAccount)
            throws AccountHolderNotFoundException, AccountLimitExceededException {
        if (accountHolder == null) throw new AccountHolderNotFoundException();
        if (accountHolder.getSavingsAccount() != null) throw new AccountLimitExceededException("An account holder may" +
                " not have more than one Savings Account.");
        savingsAccount.setAccountHolder(accountHolder);
        return savingsAccountRepository.save(savingsAccount);
    }

    public SavingsAccount getSavingsAccount(AccountHolder accountHolder) throws AccountHolderNotFoundException {
        if (accountHolder == null) throw new AccountHolderNotFoundException();
        return savingsAccountRepository.findByAccountHolder(accountHolder);
    }

    /* Transactions ================================================================================================= */
    public Transaction postDeposit(Transaction transaction) throws AccountNotFoundException {

        if (transaction.getTargetAccount() == null) throw new AccountNotFoundException();
        if (Double.parseDouble(String.valueOf(transaction.getAmount())) < 0) throw new IllegalArgumentException
                ("A deposit amount must be greater than $0.00");

        BankAccount targetAccount = transaction.getTargetAccount();
        BigDecimal updatedBalance = targetAccount.deposit(transaction.getAmount());
        targetAccount.setBalance(updatedBalance);

        return transactionRepository.save(transaction);
    }

    public Transaction postWithdrawal(Transaction transaction) throws AccountNotFoundException {

        BankAccount targetAccount = transaction.getTargetAccount();

        if (targetAccount == null) throw new AccountNotFoundException();
        if (Double.parseDouble(String.valueOf(transaction.getAmount())) >
                Double.parseDouble(String.valueOf(targetAccount.getBalance()))) throw new
                IllegalArgumentException("A withdrawal cannot exceed an accounts available balance");


        BigDecimal updatedBalance = targetAccount.withdraw(transaction.getAmount());
        targetAccount.setBalance(updatedBalance);

        return transactionRepository.save(transaction);
    }

    // TODO Test
    public Transaction postTransfer(Transaction transaction) throws AccountNotFoundException {

        BankAccount sourceAccount = transaction.getSourceAccount();
        BankAccount targetAccount = transaction.getTargetAccount();

        if (targetAccount == null || sourceAccount == null) throw new AccountNotFoundException();
        if (Double.parseDouble(String.valueOf(sourceAccount.getBalance())) <
                Double.parseDouble(String.valueOf(transaction.getAmount()))) throw new IllegalArgumentException
                ("The source account of a transfer must have a greater balance than the the transfer amount.");

        BigDecimal updatedSourceBalance = sourceAccount.withdraw(transaction.getAmount());
        BigDecimal updatedTargetBalance = targetAccount.deposit(transaction.getAmount());

        sourceAccount.setBalance(updatedSourceBalance);
        targetAccount.setBalance(updatedTargetBalance);

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAccountTransactions(BankAccount bankAccount) throws AccountNotFoundException {
        if (bankAccount == null) throw new AccountNotFoundException();
        return transactionRepository.findByTargetAccount(bankAccount);
    }

}
