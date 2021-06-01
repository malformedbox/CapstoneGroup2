package com.capstonegroup2.backend.controllers;

import com.capstonegroup2.backend.dto.CDAccountDTO;
import com.capstonegroup2.backend.dto.CheckingDBAdto;
import com.capstonegroup2.backend.dto.CheckingPersonalDTO;
import com.capstonegroup2.backend.models.CDAccount;
import com.capstonegroup2.backend.models.CheckingDBA;
import com.capstonegroup2.backend.models.CheckingPersonal;
import com.capstonegroup2.backend.services.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/AccountHolders")
public class AccountHolderController {

    @GetMapping("/Test")
    public String test() {
        return "<h1>Hello World!</h1>";
    }

    @Autowired
    AccountHolderService accountHolderService;

    /* CD Accounts ================================================================================================== */
    @PostMapping("/{id}/CDAccounts")
    public CDAccount addCDAccount(@RequestBody CDAccountDTO cdAccountDTO, @PathVariable Long id) {
        return accountHolderService.addCDAccount(cdAccountDTO, id);
    }

    @GetMapping("/{id}/CDAccounts")
    public List<CDAccount> getCDAccounts(@PathVariable Long id) {
        return accountHolderService.getCDAccounts(id);
    }

    /* Personal Checking Accounts =================================================================================== */
    @PostMapping("/{id}/CheckingPersonal")
    public CheckingPersonal addCheckingPersonal(@RequestBody CheckingPersonalDTO checkingPersonalDTO,
                                                @PathVariable Long id) {
        return accountHolderService.addCheckingPersonal(checkingPersonalDTO, id);
    }

    @GetMapping("/{id}/CheckingPersonal")
    public CheckingPersonal getCheckingPersonal(@PathVariable Long id) {
        return accountHolderService.getCheckingPersonal(id);
    }

    /* DBA Checking Accounts ======================================================================================== */
    @PostMapping("/{id}/CheckingDBA")
    public CheckingDBA addCheckingDBA(@RequestBody CheckingDBAdto checkingDBAdto, @PathVariable Long id) {
        return accountHolderService.addCheckingDBA(checkingDBAdto, id);
    }

    @GetMapping("/{id}/CheckingDBA")
    public List<CheckingDBA> getCheckingDBA(@PathVariable Long id) {
        return accountHolderService.getCheckingDBA(id);
    }

    /* IRA Regular Accounts ========================================================================================= */

    /* IRA Rollover Accounts ======================================================================================== */

    /* IRA Roth Accounts ============================================================================================ */

    /* Savings Accounts ============================================================================================= */
}
