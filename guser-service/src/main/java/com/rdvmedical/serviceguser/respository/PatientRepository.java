package com.rdvmedical.serviceguser.respository;

import com.rdvmedical.serviceguser.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByEmail(String email);
    Optional<Patient> findByNumeroSecu(String numeroSecu);
    boolean existsByEmail(String email);
    boolean existsByNumeroSecu(String numeroSecu);
}

