package com.capstonegroup2.backend.services;

import com.capstonegroup2.backend.dto.CDAccountDTO;
import com.capstonegroup2.backend.exceptions.AccountHolderNotFoundException;
import com.capstonegroup2.backend.models.AccountHolder;
import com.capstonegroup2.backend.models.CDAccount;
import com.capstonegroup2.backend.models.UserCredentials;
import com.capstonegroup2.backend.repositories.UserCredentialsRepository;
import com.capstonegroup2.backend.security.JwtTokenCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggedInService {

    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    @Autowired
    AccountHolderService accountHolderService;

    @Autowired
    JwtTokenCreator jwtTokenCreator;

    public AccountHolder getLoggedInAccountHolder(String token) {
        token = token.substring(7);
        UserCredentials userCredentials = userCredentialsRepository.findByUsername(jwtTokenCreator.extractUsername(token)).get();
        return accountHolderService.getAccountHolderById(userCredentials.getAccountHolder().getId());
    }

    /* CD Accounts ================================================================================================== */
    public CDAccount addLoggedInCDAccount(String token, CDAccountDTO cdAccountDTO)
            throws AccountHolderNotFoundException {
        AccountHolder accountHolder = getLoggedInAccountHolder(token);
        if (accountHolder == null) {
            throw new AccountHolderNotFoundException("Account Holder could not be located : CD Account failed to post");
        }
        return accountHolderService.addCDAccount(cdAccountDTO, accountHolder.getId());
    }

    public List<CDAccount> getLoggedInCDAccounts(String token) throws AccountHolderNotFoundException {
        AccountHolder accountHolder = getLoggedInAccountHolder(token);
        if (accountHolder == null) {
            throw new AccountHolderNotFoundException();
        }
        return accountHolderService.getCDAccounts(accountHolder.getId());
    }

    // I assume this is the class where should do all our business logic validation and that would should create
    // exceptions to handle them.
}
