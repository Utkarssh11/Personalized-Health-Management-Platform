package com.healthplatform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthplatform.model.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    Optional<Medication> findByName(String name);
    List<Medication> findByNameContainingIgnoreCase(String name);
    Page<Medication> findAll(Pageable pageable);
    boolean existsByName(String name);
} 