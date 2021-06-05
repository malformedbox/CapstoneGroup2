package com.capstonegroup2.backend.services;

import com.capstonegroup2.backend.dto.*;
import com.capstonegroup2.backend.models.*;
import com.capstonegroup2.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountHolderService {


    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    @Autowired
    CDAccountRepository cdAccountRepository;

    @Autowired
    CDOfferingRepository cdOfferingRepository;

    @Autowired
    DbaCheckingRepository dbaCheckingRepository;

    @Autowired
    PersonalCheckingRepository personalCheckingRepository;

    @Autowired
    IraRegularRepository iraRegularRepository;

    @Autowired
    IraRolloverRepository iraRolloverRepository;

    @Autowired
    IraRothRepository iraRothRepository;

    @Autowired
    SavingsAccountRepository savingsAccountRepository;

//    @Autowired
//    TransactionRepository transactionRepository;


    /* Account Holders ============================================================================================== */
    public List<AccountHolder> getAllAccounts(){
        return accountHolderRepository.findAll();
    }

    public AccountHolder addAccountHolder(AccountHolderDTO accountHolderDTO)  {

        UserCredentials user = userCredentialsRepository.findById(accountHolderDTO.getId()).orElse(null);

        AccountHolder newHolder = new AccountHolder(accountHolderDTO.getFirstName(), accountHolderDTO.getMiddleName(),
                accountHolderDTO.getLastName(), accountHolderDTO.getSsn(), user);
        return accountHolderRepository.save(newHolder);
    }

    public AccountHolder getAccountHolderById(Long id) {
        return accountHolderRepository.findById(id).orElse(null);
    }

    public List<AccountHolder> getAllAccountHolders() {
        return accountHolderRepository.findAll();
    }


    /* CD Accounts ================================================================================================== */
    public CDAccount addCDAccount(CDAccountDTO cdAccountDTO, Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        CDOffering cdOffering = getCDOfferingById(cdAccountDTO.getCdOffering().getId());
        CDAccount cdAccount = new CDAccount(cdAccountDTO.getBalance(), cdOffering);
        cdAccount.setAccountHolder(accountHolder);
        return cdAccountRepository.save(cdAccount);
    }

    private CDOffering getCDOfferingById(long id) {
        return cdOfferingRepository.findById(id);
    }


    public List<CDAccount> getCDAccounts(Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        return cdAccountRepository.findByAccountHolder(accountHolder);
    }


    /* Personal Checking Accounts =================================================================================== */
    public PersonalChecking addPersonalChecking(PersonalCheckingDTO personalCheckingDTO, Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        PersonalChecking personalChecking = new PersonalChecking(personalCheckingDTO.getBalance());
        personalChecking.setAccountHolder(accountHolder);
        return personalCheckingRepository.save(personalChecking);
    }

    public PersonalChecking getPersonalChecking(Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        return personalCheckingRepository.findByAccountHolder(accountHolder);
    }

    /* DBA Checking Accounts ======================================================================================== */
    public DbaChecking addDbaChecking(DbaCheckingDTO dbaCheckingDTO, Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        DbaChecking dbaChecking = new DbaChecking(dbaCheckingDTO.getBalance());
        dbaChecking.setAccountHolder(accountHolder);
        return dbaCheckingRepository.save(dbaChecking);
    }

    public List<DbaChecking> getDbaChecking(Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        return dbaCheckingRepository.findByAccountHolder(accountHolder);
    }

    /* IRA Regular Accounts ========================================================================================= */
    public IraRegular addIraRegular(IraRegularDTO iraRegularDTO, Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        IraRegular iraRegular = new IraRegular(iraRegularDTO.getBalance());
        iraRegular.setAccountHolder(accountHolder);
        return iraRegularRepository.save(iraRegular);
    }

    public IraRegular getIraRegular(Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        return  iraRegularRepository.findByAccountHolder(accountHolder);
    }

    /* IRA Rollover Accounts ======================================================================================== */
    public IraRollover addIraRollover(IraRolloverDTO iraRolloverDTO, Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        IraRollover iraRollover = new IraRollover(iraRolloverDTO.getBalance());
        iraRollover.setAccountHolder(accountHolder);
        return iraRolloverRepository.save(iraRollover);
    }

    public IraRollover getIraRollover(Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        return iraRolloverRepository.findByAccountHolder(accountHolder);
    }

    /* IRA Roth Accounts ============================================================================================ */
    public IraRoth addIraRoth(IraRothDTO iraRothDTO, Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        IraRoth iraRoth = new IraRoth(iraRothDTO.getBalance());
        iraRoth.setAccountHolder(accountHolder);
        return iraRothRepository.save(iraRoth);
    }

    public IraRoth getIraRoth(Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        return iraRothRepository.findByAccountHolder(accountHolder);
    }

    /* Savings Accounts ============================================================================================= */
    public SavingsAccount addSavingsAccount(SavingsAccountDTO savingsAccountDTO, Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        SavingsAccount savingsAccount = new SavingsAccount(savingsAccountDTO.getBalance());
        savingsAccount.setAccountHolder(accountHolder);
        return savingsAccountRepository.save(savingsAccount);
    }

    public SavingsAccount getSavingsAccount(Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        return savingsAccountRepository.findByAccountHolder(accountHolder);
    }


    /* Transactions ================================================================================================= */

    public BankAccount getBankAccountById(Long id) {
        List<CDAccount> cdAccounts = cdAccountRepository.findAll();
        for (CDAccount cdAccount : cdAccounts) {
            if (cdAccount.getId() == id) return cdAccount;
        }
        List<PersonalChecking> personalCheckingAccounts = personalCheckingRepository.findAll();
        for (PersonalChecking personalChecking : personalCheckingAccounts) {
            if (personalChecking.getId() == id) return personalChecking;
        }
        List<DbaChecking> dbaCheckingList = dbaCheckingRepository.findAll();
        for (DbaChecking dbaChecking : dbaCheckingList) {
            if (dbaChecking.getId() == id) return dbaChecking;
        }
        List<IraRegular> iraRegularList = iraRegularRepository.findAll();
        for (IraRegular iraRegular : iraRegularList) {
            if (iraRegular.getId() == id) return iraRegular;
        }
        List<IraRollover> iraRolloverList = iraRolloverRepository.findAll();
        for (IraRollover iraRollover : iraRolloverList) {
            if (iraRollover.getId() == id) return iraRollover;
        }
        List<IraRoth> iraRothList = iraRothRepository.findAll();
        for (IraRoth iraRoth : iraRothList) {
            if (iraRoth.getId() == id) return iraRoth;
        }
        List<SavingsAccount> savingsAccounts = savingsAccountRepository.findAll();
        for (SavingsAccount savingsAccount : savingsAccounts) {
            if (savingsAccount.getId() == id) return savingsAccount;
        }
        return null;
    }

//    public Transaction addTransaction(TransactionDTO transactionDTO, Long id) {
//        BankAccount bankAccount = getBankAccountById(id);
//        Transaction transaction = new Transaction(transactionDTO.getAmount(),
//                transactionDTO.getDateOfTransaction(), transactionDTO.getTransactionType());
//        transaction.setBankAccount(bankAccount);
//        return transactionRepository.save(transaction);
//    }
//
//
//    public List<Transaction> getTransactions(Long id) {
//        BankAccount bankAccount = getBankAccountById(id);
//        return transactionRepository.findByBankAccount(bankAccount);
//    }

//    public Transaction addDepositTransaction(TransactionDTO transactionDTO, Long id) {
//        BankAccount bankAccount = getBankAccountById(id);
//        double balance = bankAccount.getBalance();
//        balance += transactionDTO.getAmount();
//    }
}
