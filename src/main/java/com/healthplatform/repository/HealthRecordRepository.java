package com.healthplatform.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthplatform.model.HealthRecord;
import com.healthplatform.model.User;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {
    Page<HealthRecord> findByPatient(User patient, Pageable pageable);
    List<HealthRecord> findByPatientAndRecordDateBetween(User patient, LocalDateTime start, LocalDateTime end);
    List<HealthRecord> findByPatientOrderByRecordDateDesc(User patient);
}