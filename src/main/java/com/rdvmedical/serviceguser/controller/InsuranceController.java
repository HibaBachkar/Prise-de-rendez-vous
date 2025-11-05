package com.rdvmedical.serviceguser.controller;

import com.rdvmedical.serviceguser.domain.entity.Insurance;
import com.rdvmedical.serviceguser.service.IServiceInsurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurances")
public class InsuranceController {

    @Autowired
    private IServiceInsurance serviceInsurance;

    @GetMapping
    public ResponseEntity<List<Insurance>> getAllInsurances() {
        List<Insurance> insurances = serviceInsurance.findAll();
        return ResponseEntity.ok(insurances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insurance> getInsuranceById(@PathVariable Long id) {
        return serviceInsurance.findById(id)
                .map(insurance -> ResponseEntity.ok(insurance))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Insurance>> getInsurancesByPatient(@PathVariable Long patientId) {
        List<Insurance> insurances = serviceInsurance.findByPatientId(patientId);
        return ResponseEntity.ok(insurances);
    }

    @PostMapping
    public ResponseEntity<Insurance> createInsurance(@RequestBody Insurance insurance) {
        try {
            Insurance createdInsurance = serviceInsurance.save(insurance);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdInsurance);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Insurance> updateInsurance(@PathVariable Long id, @RequestBody Insurance insurance) {
        try {
            Insurance updatedInsurance = serviceInsurance.update(id, insurance);
            return ResponseEntity.ok(updatedInsurance);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsurance(@PathVariable Long id) {
        try {
            serviceInsurance.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

