package com.capstonegroup2.backend.repositories;

import com.capstonegroup2.backend.models.CDOffering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CDOfferingRepository extends JpaRepository<CDOffering, Long> {
    CDOffering findById(long id);
    List<CDOffering> findAll();
}
