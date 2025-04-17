package com.healthplatform.repository;

import com.healthplatform.model.User;
import com.healthplatform.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    List<User> findByRole(UserRole role);
    List<User> findByRoleAndEnabled(UserRole role, boolean enabled);
} 