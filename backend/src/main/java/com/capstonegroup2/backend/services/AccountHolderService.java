package com.capstonegroup2.backend.services;

import com.capstonegroup2.backend.dto.AccountHolderDTO;
import com.capstonegroup2.backend.dto.CDAccountDTO;
import com.capstonegroup2.backend.dto.CheckingDBAdto;
import com.capstonegroup2.backend.dto.CheckingPersonalDTO;
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
    UserDetailsRepository userDetailsRepository;

    @Autowired
    CDAccountRepository cdAccountRepository;

    @Autowired
    CDOfferingRepository cdOfferingRepository;

    @Autowired
    CheckingDBARepository checkingDBARepository;

    @Autowired
    CheckingPersonalRepository checkingPersonalRepository;

    @Autowired
    RegularIRARepository regularIRARepository;

    @Autowired
    RolloverIRARepository rolloverIRARepository;

    @Autowired
    RothIRARepository rothIRARepository;

    @Autowired
    SavingsAccountRepository savingsAccountRepository;

    public List<AccountHolder> getAllAccounts(){
        return accountHolderRepository.findAll();
    }

    public AccountHolder addAccount(AccountHolderDTO accountHolderDTO)  {

        UserDetails user = userDetailsRepository.findById(accountHolderDTO.getId()).orElse(null);

        AccountHolder newHolder = new AccountHolder(accountHolderDTO.getFirstName(), accountHolderDTO.getMiddleName(),
                accountHolderDTO.getLastName(), accountHolderDTO.getSsn(), user);
        return accountHolderRepository.save(newHolder);
    }

    public AccountHolder getAccountHolderById(Long id) {
        return accountHolderRepository.findById(id).orElse(null);
    }


    /* CD Accounts ================================================================================================== */
    public CDAccount addCDAccount(CDAccountDTO cdAccountDTO, Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        CDAccount cdAccount = new CDAccount(cdAccountDTO.getBalance(), cdAccountDTO.getCdOffering());
        return cdAccountRepository.save(cdAccount);
    }


    public List<CDAccount> getCDAccounts(Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        return cdAccountRepository.findByAccountHolder(accountHolder);
    }


    /* Personal Checking Accounts =================================================================================== */
    public CheckingPersonal addCheckingPersonal(CheckingPersonalDTO checkingPersonalDTO, Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        CheckingPersonal checkingPersonal = new CheckingPersonal(checkingPersonalDTO.getBalance());
        return checkingPersonalRepository.save(checkingPersonal);
    }

    public CheckingPersonal getCheckingPersonal(Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        return checkingPersonalRepository.findByAccountHolder(accountHolder);
    }

    /* DBA Checking Accounts ======================================================================================== */
    public CheckingDBA addCheckingDBA(CheckingDBAdto checkingDBAdto, Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        CheckingDBA checkingDBA = new CheckingDBA(checkingDBAdto.getBalance());
        return checkingDBARepository.save(checkingDBA);
    }

    public List<CheckingDBA> getCheckingDBA(Long id) {
        AccountHolder accountHolder = getAccountHolderById(id);
        return checkingDBARepository.findByAccountHolder(accountHolder);
    }
}
