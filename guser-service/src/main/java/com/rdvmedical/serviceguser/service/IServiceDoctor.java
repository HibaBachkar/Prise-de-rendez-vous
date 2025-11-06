package com.rdvmedical.serviceguser.service;

import com.rdvmedical.serviceguser.domain.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface IServiceDoctor {
    List<Doctor> findAll();
    Optional<Doctor> findById(Long id);
    Optional<Doctor> findByEmail(String email);
    Optional<Doctor> findByNumeroRpps(String numeroRpps);
    List<Doctor> findBySpecialtyId(Long specialtyId);
    List<Doctor> findByActif(Boolean actif);
    Doctor save(Doctor doctor);
    Doctor update(Long id, Doctor doctor);
    void deleteById(Long id);
    boolean existsById(Long id);
}

