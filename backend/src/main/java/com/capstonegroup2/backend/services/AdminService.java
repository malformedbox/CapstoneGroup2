package com.capstonegroup2.backend.services;

import com.capstonegroup2.backend.dto.*;
import com.capstonegroup2.backend.models.*;
import com.capstonegroup2.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired AccountHolderRepository accountHolderRepository;

    @Autowired UserCredentialsRepository userCredentialsRepository;

    @Autowired BankAccountRepository bankAccountRepository;

    @Autowired CDAccountRepository cdAccountRepository;

    @Autowired CDOfferingRepository cdOfferingRepository;

    @Autowired DbaCheckingRepository dbaCheckingRepository;

    @Autowired PersonalCheckingRepository personalCheckingRepository;

    @Autowired IraRegularRepository iraRegularRepository;

    @Autowired IraRolloverRepository iraRolloverRepository;

    @Autowired IraRothRepository iraRothRepository;

    @Autowired SavingsAccountRepository savingsAccountRepository;



    /* Account Holders ============================================================================================== */
    public List<AccountHolder> getAllAccounts(){
        return accountHolderRepository.findAll();
    }

    public AccountHolder getAccountHolderById(Long id) {
        return accountHolderRepository.findById(id).orElse(null);
    }

    public List<AccountHolder> getAllAccountHolders() {
        return accountHolderRepository.findAll();
    }


    /* CD Accounts ================================================================================================== */
    public CDAccount addCDAccount(CDAccount cdAccount, AccountHolder accountHolder) {
        cdAccount.setAccountHolder(accountHolder);
        return cdAccountRepository.save(cdAccount);
    }

    public CDOffering getCDOfferingById(long id) {
        return cdOfferingRepository.findById(id);
    }


    public List<CDAccount> getCDAccounts(AccountHolder accountHolder) {
        return cdAccountRepository.findByAccountHolder(accountHolder);
    }


    /* Personal Checking Accounts =================================================================================== */
    public PersonalChecking addPersonalChecking(PersonalChecking personalChecking, AccountHolder accountHolder) {
        personalChecking.setAccountHolder(accountHolder);
        return personalCheckingRepository.save(personalChecking);
    }

    public PersonalChecking getPersonalChecking(AccountHolder accountHolder) {
        return personalCheckingRepository.findByAccountHolder(accountHolder);
    }

    /* DBA Checking Accounts ======================================================================================== */
    public DbaChecking addDbaChecking(DbaChecking dbaChecking, AccountHolder accountHolder) {
        dbaChecking.setAccountHolder(accountHolder);
        return dbaCheckingRepository.save(dbaChecking);
    }

    public List<DbaChecking> getDbaChecking(AccountHolder accountHolder) {
        return dbaCheckingRepository.findByAccountHolder(accountHolder);
    }

    /* IRA Regular Accounts ========================================================================================= */
    public IraRegular addIraRegular(IraRegular iraRegular, AccountHolder accountHolder) {
        iraRegular.setAccountHolder(accountHolder);
        return iraRegularRepository.save(iraRegular);
    }

    public IraRegular getIraRegular(AccountHolder accountHolder) {
        return  iraRegularRepository.findByAccountHolder(accountHolder);
    }

    /* IRA Rollover Accounts ======================================================================================== */
    public IraRollover addIraRollover(IraRollover iraRollover, AccountHolder accountHolder) {
        iraRollover.setAccountHolder(accountHolder);
        return iraRolloverRepository.save(iraRollover);
    }

    public IraRollover getIraRollover(AccountHolder accountHolder) {
        return iraRolloverRepository.findByAccountHolder(accountHolder);
    }

    /* IRA Roth Accounts ============================================================================================ */
    public IraRoth addIraRoth(IraRoth iraRoth, AccountHolder accountHolder) {
        iraRoth.setAccountHolder(accountHolder);
        return iraRothRepository.save(iraRoth);
    }

    public IraRoth getIraRoth(AccountHolder accountHolder) {
        return iraRothRepository.findByAccountHolder(accountHolder);
    }

    /* Savings Accounts ============================================================================================= */
    public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount, AccountHolder accountHolder) {
        savingsAccount.setAccountHolder(accountHolder);
        return savingsAccountRepository.save(savingsAccount);
    }

    public SavingsAccount getSavingsAccount(AccountHolder accountHolder) {
        return savingsAccountRepository.findByAccountHolder(accountHolder);
    }




}
