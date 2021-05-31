package com.capstonegroup2.backend.services;

import com.capstonegroup2.backend.dto.AccountHolderDTO;
import com.capstonegroup2.backend.dto.CDAccountDTO;
import com.capstonegroup2.backend.models.*;
import com.capstonegroup2.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public AccountHolder getAccountById(Long id) {
        return accountHolderRepository.findById(id).orElse(null);
    }

    //===================================================================================================================
    public CDAccount addCDAccount(CDAccountDTO cdAccountDTO, Long id) {
        AccountHolder accountHolder = getAccountById(id);
        CDAccount cdAccount = new CDAccount(cdAccountDTO.getBalance(), cdAccountDTO.getCdOffering());
        return cdAccountRepository.save(cdAccount);
    }


    public List<CDAccount> getCDAccounts(Long id) {
        AccountHolder accountHolder = getAccountById(id);

        return cdAccountRepository.findByAccountHolder(accountHolder);
    }
}
