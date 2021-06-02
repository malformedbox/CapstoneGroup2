package com.capstonegroup2.backend.services;

import com.capstonegroup2.backend.dto.CDOfferingDTO;
import com.capstonegroup2.backend.models.CDOffering;
import com.capstonegroup2.backend.repositories.CDOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CDOfferingService {

    @Autowired
    CDOfferingRepository cdOfferingRepository;

    public CDOffering addCDOffering(CDOfferingDTO cdOfferingDTO) {
        CDOffering cdOffering = new CDOffering(cdOfferingDTO.getTerm(), cdOfferingDTO.getInterestRate());
        return cdOfferingRepository.save(cdOffering);
    }

    public CDOffering getCDOfferingById(long id) {
        return cdOfferingRepository.findById(id);
    }

    public List<CDOffering> getCDOfferings() {
        return cdOfferingRepository.findAll();
    }

}