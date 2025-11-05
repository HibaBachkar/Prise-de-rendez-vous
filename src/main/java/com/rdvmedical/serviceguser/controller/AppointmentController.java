package com.rdvmedical.serviceguser.controller;

import com.rdvmedical.serviceguser.domain.entity.Appointment;
import com.rdvmedical.serviceguser.service.IServiceAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private IServiceAppointment serviceAppointment;

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = serviceAppointment.findAll();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return serviceAppointment.findById(id)
                .map(appointment -> ResponseEntity.ok(appointment))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient(@PathVariable Long patientId) {
        List<Appointment> appointments = serviceAppointment.findByPatientId(patientId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        List<Appointment> appointments = serviceAppointment.findByDoctorId(doctorId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/doctor/{doctorId}/periode")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorAndPeriode(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<Appointment> appointments = serviceAppointment.findByDoctorIdAndDateHeureBetween(doctorId, start, end);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/patient/{patientId}/periode")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientAndPeriode(
            @PathVariable Long patientId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<Appointment> appointments = serviceAppointment.findByPatientIdAndDateHeureBetween(patientId, start, end);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Appointment>> getAppointmentsByStatut(@PathVariable String statut) {
        Appointment.AppointmentStatus status = Appointment.AppointmentStatus.valueOf(statut.toUpperCase());
        List<Appointment> appointments = serviceAppointment.findByStatut(status);
        return ResponseEntity.ok(appointments);
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        try {
            Appointment createdAppointment = serviceAppointment.save(appointment);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        try {
            Appointment updatedAppointment = serviceAppointment.update(id, appointment);
            return ResponseEntity.ok(updatedAppointment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<Appointment> updateAppointmentStatut(@PathVariable Long id, @RequestParam String statut) {
        try {
            Appointment.AppointmentStatus status = Appointment.AppointmentStatus.valueOf(statut.toUpperCase());
            Appointment updatedAppointment = serviceAppointment.updateStatut(id, status);
            return ResponseEntity.ok(updatedAppointment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        try {
            serviceAppointment.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

