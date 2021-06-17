package com.capstonegroup2.backend.services;

import com.capstonegroup2.backend.exceptions.AccountHolderNotFoundException;
import com.capstonegroup2.backend.exceptions.AccountLimitExceededException;
import com.capstonegroup2.backend.exceptions.AccountNotFoundException;
import com.capstonegroup2.backend.exceptions.OfferingNotFoundException;
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

    @Autowired AccountHolderRepository accountHolderRepository;

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
        return accountHolderRepository.findById(user.getAccountHolder().getId()).orElse(null);
    }

    public String deleteLoggedInAccountHolder(AccountHolder accountHolder) throws AccountHolderNotFoundException {
        if (accountHolder == null) throw new AccountHolderNotFoundException();
        if (accountHolder.getSavingsAccount() != null && accountHolder.getPersonalChecking() != null) {
            throw new IllegalArgumentException("An account holder must close all their accounts before their records" +
                    " can be deleted.");
        }
        accountHolderRepository.delete(accountHolder);
        return "The Account Holder has been deleted";
    }

    public BankAccount getAccountByAccountNumber(long accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber);
    }

    /* CD Accounts ================================================================================================== */
    public CDAccount addCDAccount(AccountHolder accountHolder, CDAccount cdAccount)
            throws AccountHolderNotFoundException, AccountNotFoundException, OfferingNotFoundException {

        if (accountHolder == null) throw new AccountHolderNotFoundException();
        if (cdAccount == null) throw new AccountNotFoundException();
        if (cdAccount.getCdOffering() == null) throw new OfferingNotFoundException();

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
        personalChecking.setInterestRate(new BigDecimal(MeritBank.PERSONAL_CHECKING_INTEREST_RATE));
        return personalChecking;
    }

    // Can switch this to a transaction and move the transaction fron AccountHolder class in
    // transferBalanceOnAccountClose to over here and save them into the transaction repository
    public Transaction closePersonalChecking(AccountHolder accountHolder)
            throws AccountNotFoundException, AccountHolderNotFoundException {
        if (accountHolder == null) throw new AccountHolderNotFoundException();
        if (accountHolder.getPersonalChecking() == null) throw new AccountNotFoundException();
        Transaction transaction = accountHolder.closeAccount(accountHolder.getPersonalChecking());
        personalCheckingRepository.delete(accountHolder.getPersonalChecking());
        transactionRepository.save(transaction);
        return transaction;
    }


    /* DBA Checking Accounts ======================================================================================== */
    public DbaChecking addDbaChecking(AccountHolder accountHolder, DbaChecking dbaChecking)
            throws AccountHolderNotFoundException, AccountLimitExceededException {
        if (accountHolder == null) throw new AccountHolderNotFoundException();

        int numberOfDbaAccounts = accountHolder.numberOfHoldersExistingDbaAccounts(accountHolder);
        if (numberOfDbaAccounts >= 3) throw new AccountLimitExceededException("An account holder may not have more " +
                "than 3 DBA Checking accounts.");

        dbaChecking.setAccountHolder(accountHolder);
        return dbaCheckingRepository.save(dbaChecking);
    }

    public List<DbaChecking> getDbaChecking(AccountHolder accountHolder) throws AccountHolderNotFoundException, AccountNotFoundException {
        if (accountHolder == null) throw new AccountHolderNotFoundException();

        List<DbaChecking> dbaCheckingList = dbaCheckingRepository.findByAccountHolder(accountHolder);
        if (dbaCheckingList.size() < 1) throw new AccountNotFoundException();

        for (DbaChecking dbaChecking : dbaCheckingList) {
            dbaChecking.setInterestRate(new BigDecimal(MeritBank.DBA_CHECKING_INTEREST_RATE));
        }
        return dbaCheckingList;
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

    public Transaction closeIraRegular(AccountHolder accountHolder)
            throws AccountNotFoundException, AccountHolderNotFoundException {
        if (accountHolder == null) throw new AccountHolderNotFoundException();
        if (accountHolder.getIraRegular() == null) throw new AccountNotFoundException();
        Transaction transaction = accountHolder.closeAccount(accountHolder.getIraRegular());
        iraRegularRepository.delete(accountHolder.getIraRegular());
        transactionRepository.save(transaction);
        return transaction;
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
    public IraRoth addIraRoth(AccountHolder accountHolder, IraRoth iraRoth)
            throws AccountHolderNotFoundException, AccountLimitExceededException {
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

        // TODO Consider implementation
        // This is a hypothetical validation check if we decide to implement restrictions based on the method of a
        // transaction(in this case withdrawal through ATM) or limits on amounts based on the method type of transaction

//        if(transaction.getTransactionMethod() == TransactionMethod.ATM &&
//                Double.parseDouble(String.valueOf(transaction.getAmount())) > MeritBank.MAX_WITHDRAWAL_LIMIT_ATM) {
//            throw new ExceedsAmountLimit();
//        }

        BankAccount targetAccount = transaction.getTargetAccount();

        if (targetAccount == null) throw new AccountNotFoundException();
        if (Double.parseDouble(String.valueOf(transaction.getAmount())) >
                Double.parseDouble(String.valueOf(targetAccount.getBalance()))) throw new
                IllegalArgumentException("A withdrawal cannot exceed an accounts available balance");


        BigDecimal updatedBalance = targetAccount.withdraw(transaction.getAmount());
        targetAccount.setBalance(updatedBalance);

        return transactionRepository.save(transaction);
    }

    public Transaction postTransfer(Transaction transaction) throws AccountNotFoundException {

        BankAccount sourceAccount = transaction.getSourceAccount();
        BankAccount targetAccount = transaction.getTargetAccount();

        if (targetAccount == null || sourceAccount == null) throw new AccountNotFoundException();
        if (Double.parseDouble(String.valueOf(sourceAccount.getBalance())) <
                Double.parseDouble(String.valueOf(transaction.getAmount()))) throw new IllegalArgumentException
                ("The source account of a transfer must have a balance greater than the amount being transferred.");

        BigDecimal updatedSourceBalance = sourceAccount.withdraw(transaction.getAmount());
        BigDecimal updatedTargetBalance = targetAccount.deposit(transaction.getAmount());

        sourceAccount.setBalance(updatedSourceBalance);
        targetAccount.setBalance(updatedTargetBalance);

        return transactionRepository.save(transaction);
    }


    public List<Transaction> getAccountTransactions(BankAccount bankAccount) throws AccountNotFoundException {
        if (bankAccount == null) throw new AccountNotFoundException();

        return transactionRepository.findAllByTargetAccount(bankAccount);
    }


}
