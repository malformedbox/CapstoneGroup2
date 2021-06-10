package com.capstonegroup2.backend.services;

import com.capstonegroup2.backend.exceptions.AccountHolderNotFoundException;
import com.capstonegroup2.backend.exceptions.AccountNotFoundException;
import com.capstonegroup2.backend.models.*;
import com.capstonegroup2.backend.repositories.*;
import com.capstonegroup2.backend.security.JwtTokenCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LoggedInService {

    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    @Autowired
    AccountHolderService accountHolderService;

    @Autowired
    CDAccountRepository cdAccountRepository;

    @Autowired
    PersonalCheckingRepository personalCheckingRepository;

    @Autowired
    DbaCheckingRepository dbaCheckingRepository;

    @Autowired
    IraRegularRepository iraRegularRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    JwtTokenCreator jwtTokenCreator;

    /* Account Holder ============================================================================================== */
    public AccountHolder getLoggedInAccountHolder(String token) {
        token = token.substring(7);
        UserCredentials user = userCredentialsRepository.findByUsername(jwtTokenCreator.extractUsername(token)).get();
        return accountHolderService.getAccountHolderById(user.getAccountHolder().getId());
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
            throws AccountHolderNotFoundException {
        if (accountHolder == null) throw new AccountHolderNotFoundException();
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
    public DbaChecking addDbaChecking(AccountHolder accountHolder, DbaChecking dbaChecking)
            throws AccountHolderNotFoundException {
        if (accountHolder == null) throw new AccountHolderNotFoundException();
        dbaChecking.setAccountHolder(accountHolder);
        return dbaCheckingRepository.save(dbaChecking);
    }

    public List<DbaChecking> getDbaChecking(AccountHolder accountHolder) throws AccountHolderNotFoundException {
        if (accountHolder == null) throw new AccountHolderNotFoundException();
        return accountHolderService.getDbaChecking(accountHolder.getId());
    }

    /* IRA Regular Accounts ========================================================================================= */
    public IraRegular addIraRegular(AccountHolder accountHolder, IraRegular iraRegular)
            throws AccountHolderNotFoundException {
        if (accountHolder ==  null) throw new AccountHolderNotFoundException();
        iraRegular.setAccountHolder(accountHolder);
        return iraRegularRepository.save(iraRegular);
    }

    public IraRegular getIraRegular(AccountHolder accountHolder) {
        return iraRegularRepository.findByAccountHolder(accountHolder);
    }


    // Ignore below, this can be refactored to be a general deposit method that hits whatever
    // bank account we target

    // So this is tricky to understand what does and does not need to be done. Currently this method I believe will
    // save over the account related to the deposit that is in the database. We could conceivably use generics or an
    // enum to have not have to create these methods for each type of account but since some will have different
    // business logic i'm not sure we would be able to get away with that in the long run anyway
//    public Transaction depositIntoPersonalChecking(String token, TransactionDTO depositDTO)
//            throws AccountHolderNotFoundException, AccountNotFoundException {
//        // Grab the Account Holder
//        AccountHolder accountHolder = getLoggedInAccountHolder(token);
//        if (accountHolder == null) throw new AccountHolderNotFoundException();
//
//        // Grab the targeted account
//        PersonalChecking personalChecking = accountHolder.getPersonalChecking();
//        if (personalChecking == null) throw new AccountNotFoundException();
//        if (depositDTO.getAmount() < 0) throw new IllegalArgumentException("A deposit must contain an amount greater than 0.");
//
//        // Create the transaction, modify the balance of the account and save the account
//        Transaction depositTransaction = new Transaction(depositDTO.getAmount(), depositDTO.getTransactionType());
//        personalChecking.deposit(depositTransaction);
////        accountHolderService.personalCheckingRepository.save(personalChecking);
//
//        // save and return the transaction
//        return transactionRepository.save(depositTransaction);
//    }


}
