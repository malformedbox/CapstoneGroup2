package com.capstonegroup2.backend.controllers;

import com.capstonegroup2.backend.dto.CDAccountDTO;
import com.capstonegroup2.backend.models.CDAccount;
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

    @PostMapping("/{id}/CDAccounts")
    public CDAccount addCDAccount(@RequestBody CDAccountDTO cdAccountDTO, @PathVariable Long id) {
        return accountHolderService.addCDAccount(cdAccountDTO, id);
    }

    @GetMapping("/{id}/CDAccounts")
    public List<CDAccount> getCDAccounts(@PathVariable Long id) {
        return accountHolderService.getCDAccounts(id);
    }


}
