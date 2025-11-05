package com.rdvmedical.serviceguser.controller;

import com.rdvmedical.serviceguser.domain.entity.MedicalRecord;
import com.rdvmedical.serviceguser.service.IServiceMedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {

    @Autowired
    private IServiceMedicalRecord serviceMedicalRecord;

    @GetMapping
    public ResponseEntity<List<MedicalRecord>> getAllMedicalRecords() {
        List<MedicalRecord> records = serviceMedicalRecord.findAll();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable Long id) {
        return serviceMedicalRecord.findById(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalRecord>> getMedicalRecordsByPatient(@PathVariable Long patientId) {
        List<MedicalRecord> records = serviceMedicalRecord.findByPatientId(patientId);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/patient/{patientId}/historique")
    public ResponseEntity<List<MedicalRecord>> getMedicalRecordsHistoryByPatient(@PathVariable Long patientId) {
        List<MedicalRecord> records = serviceMedicalRecord.findByPatientIdOrderByDateConsultationDesc(patientId);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/patient/{patientId}/periode")
    public ResponseEntity<List<MedicalRecord>> getMedicalRecordsByPatientAndPeriode(
            @PathVariable Long patientId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        List<MedicalRecord> records = serviceMedicalRecord.findByPatientIdAndDateConsultationBetween(patientId, start, end);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<MedicalRecord>> getMedicalRecordsByDoctor(@PathVariable Long doctorId) {
        List<MedicalRecord> records = serviceMedicalRecord.findByDoctorId(doctorId);
        return ResponseEntity.ok(records);
    }

    @PostMapping
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        try {
            MedicalRecord createdRecord = serviceMedicalRecord.save(medicalRecord);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable Long id, @RequestBody MedicalRecord medicalRecord) {
        try {
            MedicalRecord updatedRecord = serviceMedicalRecord.update(id, medicalRecord);
            return ResponseEntity.ok(updatedRecord);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable Long id) {
        try {
            serviceMedicalRecord.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

