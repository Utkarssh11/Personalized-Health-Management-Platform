package com.healthplatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.healthplatform.model.User;
import com.healthplatform.model.UserRole;

public interface UserService extends UserDetailsService {
    User registerUser(User user);
    Optional<User> findByEmail(String email);
    List<User> findByRole(UserRole role);
    User updateUser(User user);
    void deleteUser(Long userId);
    boolean existsByEmail(String email);
    List<User> findDoctors();
    List<User> findPatients();
    void assignDoctorToPatient(Long patientId, Long doctorId);
    void removeDoctorFromPatient(Long patientId, Long doctorId);
}