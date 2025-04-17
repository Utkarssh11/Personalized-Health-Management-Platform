package com.healthplatform.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthplatform.model.MedicationSchedule;
import com.healthplatform.model.User;

@Repository
public interface MedicationScheduleRepository extends JpaRepository<MedicationSchedule, Long> {
    Page<MedicationSchedule> findByPatient(User patient, Pageable pageable);
    List<MedicationSchedule> findByPatientAndActive(User patient, boolean active);
    
    @Query("SELECT ms FROM MedicationSchedule ms WHERE ms.patient = ?1 AND ms.active = true AND ms.nextReminder <= ?2")
    List<MedicationSchedule> findDueReminders(User patient, LocalDateTime dateTime);
    
    List<MedicationSchedule> findByPatientAndStartDateBetween(User patient, LocalDateTime start, LocalDateTime end);
    
    @Query("SELECT ms FROM MedicationSchedule ms WHERE ms.patient = ?1 AND ms.active = true AND ms.lastTaken IS NULL")
    List<MedicationSchedule> findMissedMedications(User patient);
} 