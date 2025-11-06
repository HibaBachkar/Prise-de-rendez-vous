package com.rdvmedical.serviceguser.service;

import com.rdvmedical.serviceguser.domain.entity.Availability;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IServiceAvailability {
    List<Availability> findAll();
    Optional<Availability> findById(Long id);
    List<Availability> findByDoctorId(Long doctorId);
    List<Availability> findByDoctorIdAndDisponible(Long doctorId, Boolean disponible);
    List<Availability> findByDoctorIdAndDateDebutBetween(Long doctorId, LocalDateTime start, LocalDateTime end);
    Availability save(Availability availability);
    Availability update(Long id, Availability availability);
    void deleteById(Long id);
    boolean existsById(Long id);
}

