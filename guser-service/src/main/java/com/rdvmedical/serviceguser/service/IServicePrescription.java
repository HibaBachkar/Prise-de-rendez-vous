package com.rdvmedical.serviceguser.service;

import com.rdvmedical.serviceguser.domain.entity.Prescription;

import java.util.Optional;

public interface IServicePrescription {
    Optional<Prescription> findById(Long id);
    Optional<Prescription> findByConsultationId(Long consultationId);
    Prescription save(Prescription prescription);
    Prescription update(Long id, Prescription prescription);
    void deleteById(Long id);
    boolean existsById(Long id);
}

