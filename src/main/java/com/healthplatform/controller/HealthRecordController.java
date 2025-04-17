package com.healthplatform.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthplatform.model.HealthRecord;
import com.healthplatform.model.User;
import com.healthplatform.service.HealthRecordService;
import com.healthplatform.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/health-records")
@CrossOrigin(origins = "*", maxAge = 3600)
public class HealthRecordController {

    @Autowired
    private HealthRecordService healthRecordService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<HealthRecord> createHealthRecord(
            @Valid @RequestBody HealthRecord healthRecord,
            @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        healthRecord.setPatient(user);
        healthRecord.setCreatedBy(user);
        
        HealthRecord created = healthRecordService.createHealthRecord(healthRecord);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<Page<HealthRecord>> getHealthRecords(
            @AuthenticationPrincipal UserDetails userDetails,
            Pageable pageable) {
        User user = userService.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Page<HealthRecord> records = healthRecordService.getPatientHealthRecords(user, pageable);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<HealthRecord>> getHealthRecordsByDateRange(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        User user = userService.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        List<HealthRecord> records = healthRecordService.getPatientHealthRecordsByDateRange(user, start, end);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/latest")
    public ResponseEntity<List<HealthRecord>> getLatestHealthRecords(
            @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        List<HealthRecord> records = healthRecordService.getLatestHealthRecords(user);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthRecord> getHealthRecord(@PathVariable Long id) {
        HealthRecord record = healthRecordService.getHealthRecord(id);
        return ResponseEntity.ok(record);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthRecord> updateHealthRecord(
            @PathVariable Long id,
            @Valid @RequestBody HealthRecord healthRecord) {
        healthRecord.setId(id);
        HealthRecord updated = healthRecordService.updateHealthRecord(healthRecord);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHealthRecord(@PathVariable Long id) {
        healthRecordService.deleteHealthRecord(id);
        return ResponseEntity.ok().build();
    }
} 