package com.rdvmedical.serviceguser.service;

import com.rdvmedical.serviceguser.domain.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IServiceAppointment {
    List<Appointment> findAll();
    Optional<Appointment> findById(Long id);
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByDoctorIdAndDateHeureBetween(Long doctorId, LocalDateTime start, LocalDateTime end);
    List<Appointment> findByPatientIdAndDateHeureBetween(Long patientId, LocalDateTime start, LocalDateTime end);
    List<Appointment> findByStatut(Appointment.AppointmentStatus statut);
    Appointment save(Appointment appointment);
    Appointment update(Long id, Appointment appointment);
    Appointment updateStatut(Long id, Appointment.AppointmentStatus statut);
    void deleteById(Long id);
    boolean existsById(Long id);
}

