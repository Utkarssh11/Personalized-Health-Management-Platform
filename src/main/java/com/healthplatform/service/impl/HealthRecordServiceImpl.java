package com.healthplatform.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthplatform.model.HealthRecord;
import com.healthplatform.model.User;
import com.healthplatform.repository.HealthRecordRepository;
import com.healthplatform.service.HealthRecordService;

@Service
public class HealthRecordServiceImpl implements HealthRecordService {

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    @Override
    @Transactional
    public HealthRecord createHealthRecord(HealthRecord healthRecord) {
        return healthRecordRepository.save(healthRecord);
    }

    @Override
    @Transactional
    public HealthRecord updateHealthRecord(HealthRecord healthRecord) {
        return healthRecordRepository.save(healthRecord);
    }

    @Override
    @Transactional
    public void deleteHealthRecord(Long id) {
        healthRecordRepository.deleteById(id);
    }

    @Override
    public HealthRecord getHealthRecord(Long id) {
        return healthRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Health record not found"));
    }

    @Override
    public Page<HealthRecord> getPatientHealthRecords(User patient, Pageable pageable) {
        return healthRecordRepository.findByPatient(patient, pageable);
    }

    @Override
    public List<HealthRecord> getPatientHealthRecordsByDateRange(User patient, LocalDateTime start, LocalDateTime end) {
        return healthRecordRepository.findByPatientAndRecordDateBetween(patient, start, end);
    }

    @Override
    public List<HealthRecord> getLatestHealthRecords(User patient) {
        return healthRecordRepository.findByPatientOrderByRecordDateDesc(patient);
    }
} 