package com.rdvmedical.serviceguser.controller;

import com.rdvmedical.serviceguser.domain.dto.PrescriptionDTO;
import com.rdvmedical.serviceguser.domain.entity.Prescription;
import com.rdvmedical.serviceguser.domain.mapper.PrescriptionMapper;
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

    @Autowired
    private PrescriptionMapper prescriptionMapper;

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDTO> getPrescriptionById(@PathVariable Long id) {
        return servicePrescription.findById(id)
                .map(prescription -> ResponseEntity.ok(prescriptionMapper.toDTO(prescription)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/consultation/{consultationId}")
    public ResponseEntity<PrescriptionDTO> getPrescriptionByConsultation(@PathVariable Long consultationId) {
        return servicePrescription.findByConsultationId(consultationId)
                .map(prescription -> ResponseEntity.ok(prescriptionMapper.toDTO(prescription)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PrescriptionDTO> createPrescription(@RequestBody PrescriptionDTO prescriptionDTO) {
        try {
            Prescription prescription = prescriptionMapper.toEntity(prescriptionDTO);
            Prescription createdPrescription = servicePrescription.save(prescription);
            PrescriptionDTO createdDTO = prescriptionMapper.toDTO(createdPrescription);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionDTO> updatePrescription(@PathVariable Long id, @RequestBody PrescriptionDTO prescriptionDTO) {
        try {
            Prescription prescription = prescriptionMapper.toEntity(prescriptionDTO);
            Prescription updatedPrescription = servicePrescription.update(id, prescription);
            PrescriptionDTO updatedDTO = prescriptionMapper.toDTO(updatedPrescription);
            return ResponseEntity.ok(updatedDTO);
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
