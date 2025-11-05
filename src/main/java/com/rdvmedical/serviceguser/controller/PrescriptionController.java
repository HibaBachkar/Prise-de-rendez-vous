package com.rdvmedical.serviceguser.controller;

import com.rdvmedical.serviceguser.domain.entity.Prescription;
import com.rdvmedical.serviceguser.service.IServicePrescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private IServicePrescription servicePrescription;

    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id) {
        return servicePrescription.findById(id)
                .map(prescription -> ResponseEntity.ok(prescription))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/consultation/{consultationId}")
    public ResponseEntity<Prescription> getPrescriptionByConsultation(@PathVariable Long consultationId) {
        return servicePrescription.findByConsultationId(consultationId)
                .map(prescription -> ResponseEntity.ok(prescription))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Prescription> createPrescription(@RequestBody Prescription prescription) {
        try {
            Prescription createdPrescription = servicePrescription.save(prescription);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPrescription);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prescription> updatePrescription(@PathVariable Long id, @RequestBody Prescription prescription) {
        try {
            Prescription updatedPrescription = servicePrescription.update(id, prescription);
            return ResponseEntity.ok(updatedPrescription);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
        try {
            servicePrescription.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

