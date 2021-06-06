package com.capstonegroup2.backend.services;

import com.capstonegroup2.backend.dto.CDAccountDTO;
import com.capstonegroup2.backend.dto.TransactionDTO;
import com.capstonegroup2.backend.exceptions.AccountHolderNotFoundException;
import com.capstonegroup2.backend.models.*;
import com.capstonegroup2.backend.repositories.TransactionRepository;
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
    TransactionRepository transactionRepository;

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


    // So this is tricky to understand what does and does not need to be done. Currently this method I believe will
    // save over the account related to the deposit that is in the database. We could conceivably use generics or an
    // enum to have not have to create these methods for each type of account but since some will have different
    // business logic i'm not sure we would be able to get away with that in the long run anyway
    public Transaction depositIntoPersonalChecking(String token, TransactionDTO depositDTO) {
        AccountHolder accountHolder = getLoggedInAccountHolder(token);
        PersonalChecking personalChecking = accountHolder.getPersonalChecking();
        Transaction depositTransaction = new Transaction(depositDTO.getAmount(), depositDTO.getTransactionType());
        personalChecking.deposit(depositTransaction);
        accountHolderService.personalCheckingRepository.save(personalChecking);
        return transactionRepository.save(depositTransaction);
    }
}
