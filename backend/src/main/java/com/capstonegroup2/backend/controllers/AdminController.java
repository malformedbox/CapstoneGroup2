package com.capstonegroup2.backend.controllers;

import com.capstonegroup2.backend.dto.CDAccountDTO;
import com.capstonegroup2.backend.dto.CDOfferingDTO;
import com.capstonegroup2.backend.models.CDOffering;
import com.capstonegroup2.backend.repositories.CDOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    CDOfferingRepository cdOfferingRepository;

    @PostMapping("/CDOfferings")
    public CDOffering addCDOffering(@RequestBody CDOfferingDTO cdOfferingDTO) {
        CDOffering cdOffering = new CDOffering(cdOfferingDTO.getTerm(), cdOfferingDTO.getInterestRate());
        return cdOfferingRepository.save(cdOffering);
    }
}
