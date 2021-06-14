package com.capstonegroup2.backend.services;

import com.capstonegroup2.backend.dto.CDOfferingDTO;
import com.capstonegroup2.backend.models.CDOffering;
import com.capstonegroup2.backend.repositories.CDOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CDOfferingService {

    @Autowired
    CDOfferingRepository cdOfferingRepository;

    public CDOffering addCDOffering(CDOfferingDTO cdOfferingDTO) {
        CDOffering cdOffering = new CDOffering(cdOfferingDTO.getTerm(), cdOfferingDTO.getInterestRate());
        return cdOfferingRepository.save(cdOffering);
    }

    public CDOffering addCDOffering(CDOffering cdOffering) {
        return cdOfferingRepository.save(cdOffering);
    }

    public CDOffering getCDOfferingById(long id) {
        return cdOfferingRepository.findById(id);
    }

    public List<CDOffering> getCDOfferings() {
        return cdOfferingRepository.findAll();
    }

    public List<CDOffering> seedCurrentOfferings() {
        CDOffering offer1 = new CDOffering(1, "0.018");
        CDOffering offer2 = new CDOffering(3, "0.02");
        CDOffering offer3 = new CDOffering(5, "0.023");
        CDOffering offer4 = new CDOffering(10, "0.025");

        addCDOffering(offer1);
        addCDOffering(offer2);
        addCDOffering(offer3);
        addCDOffering(offer4);

        List<CDOffering> currentOfferings = new ArrayList<>();
        currentOfferings.add(offer1);
        currentOfferings.add(offer2);
        currentOfferings.add(offer3);
        currentOfferings.add(offer4);

        return currentOfferings;
    }
}
