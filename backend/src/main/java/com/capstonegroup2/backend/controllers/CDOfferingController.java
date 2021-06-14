package com.capstonegroup2.backend.controllers;

import com.capstonegroup2.backend.dto.CDOfferingDTO;
import com.capstonegroup2.backend.models.CDOffering;
import com.capstonegroup2.backend.services.CDOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cdofferings")
public class CDOfferingController {

    @Autowired
    CDOfferingService cdOfferingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CDOffering addCDOffering(@RequestBody CDOfferingDTO cdOfferingDTO) {
        return cdOfferingService.addCDOffering(cdOfferingDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CDOffering getCDOfferingById(@PathVariable Long id) {
        return cdOfferingService.getCDOfferingById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CDOffering> getCDOfferings() {
        return cdOfferingService.getCDOfferings();
    }

    @PostMapping("/seed")
    public List<CDOffering> currentOfferingsIntializer() {
        return cdOfferingService.seedCurrentOfferings();
    }

}
