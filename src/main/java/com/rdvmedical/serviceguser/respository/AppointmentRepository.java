package com.rdvmedical.serviceguser.respository;

import com.rdvmedical.serviceguser.domain.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByDoctorIdAndDateHeureBetween(Long doctorId, LocalDateTime start, LocalDateTime end);
    List<Appointment> findByPatientIdAndDateHeureBetween(Long patientId, LocalDateTime start, LocalDateTime end);
    List<Appointment> findByStatut(Appointment.AppointmentStatus statut);
}

