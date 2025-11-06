package com.rdvmedical.serviceguser.service;

import com.rdvmedical.serviceguser.domain.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface IServicePatient {
    List<Patient> findAll();
    Optional<Patient> findById(Long id);
    Optional<Patient> findByEmail(String email);
    Optional<Patient> findByNumeroSecu(String numeroSecu);
    Patient save(Patient patient);
    Patient update(Long id, Patient patient);
    void deleteById(Long id);
    boolean existsById(Long id);
}

