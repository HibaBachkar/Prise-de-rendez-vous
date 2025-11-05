package com.rdvmedical.serviceguser.service;

import com.rdvmedical.serviceguser.domain.entity.Consultation;

import java.util.Optional;

public interface IServiceConsultation {
    Optional<Consultation> findById(Long id);
    Optional<Consultation> findByAppointmentId(Long appointmentId);
    Consultation save(Consultation consultation);
    Consultation update(Long id, Consultation consultation);
    void deleteById(Long id);
    boolean existsById(Long id);
}

