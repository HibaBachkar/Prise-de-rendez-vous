package com.rdvmedical.serviceguser.controller;

import com.rdvmedical.serviceguser.domain.entity.Consultation;
import com.rdvmedical.serviceguser.service.IServiceConsultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {

    @Autowired
    private IServiceConsultation serviceConsultation;

    @GetMapping("/{id}")
    public ResponseEntity<Consultation> getConsultationById(@PathVariable Long id) {
        return serviceConsultation.findById(id)
                .map(consultation -> ResponseEntity.ok(consultation))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<Consultation> getConsultationByAppointment(@PathVariable Long appointmentId) {
        return serviceConsultation.findByAppointmentId(appointmentId)
                .map(consultation -> ResponseEntity.ok(consultation))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Consultation> createConsultation(@RequestBody Consultation consultation) {
        try {
            Consultation createdConsultation = serviceConsultation.save(consultation);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdConsultation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consultation> updateConsultation(@PathVariable Long id, @RequestBody Consultation consultation) {
        try {
            Consultation updatedConsultation = serviceConsultation.update(id, consultation);
            return ResponseEntity.ok(updatedConsultation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        try {
            serviceConsultation.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

