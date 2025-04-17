package com.healthplatform.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.healthplatform.model.HealthRecord;
import com.healthplatform.model.User;

public interface HealthRecordService {
    HealthRecord createHealthRecord(HealthRecord healthRecord);
    HealthRecord updateHealthRecord(HealthRecord healthRecord);
    void deleteHealthRecord(Long id);
    HealthRecord getHealthRecord(Long id);
    Page<HealthRecord> getPatientHealthRecords(User patient, Pageable pageable);
    List<HealthRecord> getPatientHealthRecordsByDateRange(User patient, LocalDateTime start, LocalDateTime end);
    List<HealthRecord> getLatestHealthRecords(User patient);
} 