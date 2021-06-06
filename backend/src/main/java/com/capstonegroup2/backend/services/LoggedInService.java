package com.capstonegroup2.backend.services;

import com.capstonegroup2.backend.dto.CDAccountDTO;
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

    public CDAccount addLoggedInCDAccount(String token, CDAccountDTO cdAccountDTO) {
        AccountHolder accountHolder = getLoggedInAccountHolder(token);
        return accountHolderService.addCDAccount(cdAccountDTO, accountHolder.getId());
    }

    public List<CDAccount> getLoggedInCDAccounts(String token) {
        AccountHolder accountHolder = getLoggedInAccountHolder(token);
        return accountHolderService.getCDAccounts(accountHolder.getId());
    }
}
