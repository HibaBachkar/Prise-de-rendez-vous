package com.rdvmedical.serviceguser.controller;

import com.rdvmedical.serviceguser.domain.dto.AppointmentDTO;
import com.rdvmedical.serviceguser.domain.entity.Appointment;
import com.rdvmedical.serviceguser.domain.mapper.AppointmentMapper;
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

    @Autowired
    private AppointmentMapper appointmentMapper;

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        List<Appointment> appointments = serviceAppointment.findAll();
        List<AppointmentDTO> appointmentDTOs = appointmentMapper.toDTOList(appointments);
        return ResponseEntity.ok(appointmentDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Long id) {
        return serviceAppointment.findById(id)
                .map(appointment -> ResponseEntity.ok(appointmentMapper.toDTO(appointment)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByPatient(@PathVariable Long patientId) {
        List<Appointment> appointments = serviceAppointment.findByPatientId(patientId);
        List<AppointmentDTO> appointmentDTOs = appointmentMapper.toDTOList(appointments);
        return ResponseEntity.ok(appointmentDTOs);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        List<Appointment> appointments = serviceAppointment.findByDoctorId(doctorId);
        List<AppointmentDTO> appointmentDTOs = appointmentMapper.toDTOList(appointments);
        return ResponseEntity.ok(appointmentDTOs);
    }

    @GetMapping("/doctor/{doctorId}/periode")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByDoctorAndPeriode(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<Appointment> appointments = serviceAppointment.findByDoctorIdAndDateHeureBetween(doctorId, start, end);
        List<AppointmentDTO> appointmentDTOs = appointmentMapper.toDTOList(appointments);
        return ResponseEntity.ok(appointmentDTOs);
    }

    @GetMapping("/patient/{patientId}/periode")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByPatientAndPeriode(
            @PathVariable Long patientId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<Appointment> appointments = serviceAppointment.findByPatientIdAndDateHeureBetween(patientId, start, end);
        List<AppointmentDTO> appointmentDTOs = appointmentMapper.toDTOList(appointments);
        return ResponseEntity.ok(appointmentDTOs);
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByStatut(@PathVariable String statut) {
        Appointment.AppointmentStatus status = Appointment.AppointmentStatus.valueOf(statut.toUpperCase());
        List<Appointment> appointments = serviceAppointment.findByStatut(status);
        List<AppointmentDTO> appointmentDTOs = appointmentMapper.toDTOList(appointments);
        return ResponseEntity.ok(appointmentDTOs);
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        try {
            Appointment appointment = appointmentMapper.toEntity(appointmentDTO);
            Appointment createdAppointment = serviceAppointment.save(appointment);
            AppointmentDTO createdDTO = appointmentMapper.toDTO(createdAppointment);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Long id, @RequestBody AppointmentDTO appointmentDTO) {
        try {
            Appointment appointment = appointmentMapper.toEntity(appointmentDTO);
            Appointment updatedAppointment = serviceAppointment.update(id, appointment);
            AppointmentDTO updatedDTO = appointmentMapper.toDTO(updatedAppointment);
            return ResponseEntity.ok(updatedDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<AppointmentDTO> updateAppointmentStatut(@PathVariable Long id, @RequestParam String statut) {
        try {
            Appointment.AppointmentStatus status = Appointment.AppointmentStatus.valueOf(statut.toUpperCase());
            Appointment updatedAppointment = serviceAppointment.updateStatut(id, status);
            AppointmentDTO updatedDTO = appointmentMapper.toDTO(updatedAppointment);
            return ResponseEntity.ok(updatedDTO);
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
