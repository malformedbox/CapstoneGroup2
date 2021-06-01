package com.capstonegroup2.backend.controllers;

import com.capstonegroup2.backend.dto.CDOfferingDTO;
import com.capstonegroup2.backend.models.CDOffering;
import com.capstonegroup2.backend.services.CDOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CDOfferingController {

    @Autowired
    CDOfferingService cdOfferingService;

    @PostMapping("/CDOfferings")
    public CDOffering addCDOffering(@RequestBody CDOfferingDTO cdOfferingDTO) {
        return cdOfferingService.addCDOffering(cdOfferingDTO);
    }

    @GetMapping("/CDOfferings/{id}")
    public CDOffering getCDOfferingById(@PathVariable Long id) {
        return cdOfferingService.getCDOfferingById(id);
    }

    @GetMapping("/CDOfferings")
    public List<CDOffering> getCDOfferings() {
        return cdOfferingService.getCDOfferings();
    }

}
