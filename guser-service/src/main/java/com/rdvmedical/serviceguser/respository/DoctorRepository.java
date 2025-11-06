package com.rdvmedical.serviceguser.respository;

import com.rdvmedical.serviceguser.domain.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByEmail(String email);
    Optional<Doctor> findByNumeroRpps(String numeroRpps);
    List<Doctor> findBySpecialtyId(Long specialtyId);
    List<Doctor> findByActif(Boolean actif);
    boolean existsByEmail(String email);
    boolean existsByNumeroRpps(String numeroRpps);
}

