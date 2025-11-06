package com.rdvmedical.serviceguser.controller;

import com.rdvmedical.serviceguser.domain.dto.MedicalRecordDTO;
import com.rdvmedical.serviceguser.domain.entity.MedicalRecord;
import com.rdvmedical.serviceguser.domain.mapper.MedicalRecordMapper;
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

    @Autowired
    private MedicalRecordMapper medicalRecordMapper;

    @GetMapping
    public ResponseEntity<List<MedicalRecordDTO>> getAllMedicalRecords() {
        List<MedicalRecord> records = serviceMedicalRecord.findAll();
        List<MedicalRecordDTO> recordDTOs = medicalRecordMapper.toDTOList(records);
        return ResponseEntity.ok(recordDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecordDTO> getMedicalRecordById(@PathVariable Long id) {
        return serviceMedicalRecord.findById(id)
                .map(record -> ResponseEntity.ok(medicalRecordMapper.toDTO(record)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalRecordDTO>> getMedicalRecordsByPatient(@PathVariable Long patientId) {
        List<MedicalRecord> records = serviceMedicalRecord.findByPatientId(patientId);
        List<MedicalRecordDTO> recordDTOs = medicalRecordMapper.toDTOList(records);
        return ResponseEntity.ok(recordDTOs);
    }

    @GetMapping("/patient/{patientId}/historique")
    public ResponseEntity<List<MedicalRecordDTO>> getMedicalRecordsHistoryByPatient(@PathVariable Long patientId) {
        List<MedicalRecord> records = serviceMedicalRecord.findByPatientIdOrderByDateConsultationDesc(patientId);
        List<MedicalRecordDTO> recordDTOs = medicalRecordMapper.toDTOList(records);
        return ResponseEntity.ok(recordDTOs);
    }

    @GetMapping("/patient/{patientId}/periode")
    public ResponseEntity<List<MedicalRecordDTO>> getMedicalRecordsByPatientAndPeriode(
            @PathVariable Long patientId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        List<MedicalRecord> records = serviceMedicalRecord.findByPatientIdAndDateConsultationBetween(patientId, start, end);
        List<MedicalRecordDTO> recordDTOs = medicalRecordMapper.toDTOList(records);
        return ResponseEntity.ok(recordDTOs);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<MedicalRecordDTO>> getMedicalRecordsByDoctor(@PathVariable Long doctorId) {
        List<MedicalRecord> records = serviceMedicalRecord.findByDoctorId(doctorId);
        List<MedicalRecordDTO> recordDTOs = medicalRecordMapper.toDTOList(records);
        return ResponseEntity.ok(recordDTOs);
    }

    @PostMapping
    public ResponseEntity<MedicalRecordDTO> createMedicalRecord(@RequestBody MedicalRecordDTO medicalRecordDTO) {
        try {
            MedicalRecord medicalRecord = medicalRecordMapper.toEntity(medicalRecordDTO);
            MedicalRecord createdRecord = serviceMedicalRecord.save(medicalRecord);
            MedicalRecordDTO createdDTO = medicalRecordMapper.toDTO(createdRecord);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecordDTO> updateMedicalRecord(@PathVariable Long id, @RequestBody MedicalRecordDTO medicalRecordDTO) {
        try {
            MedicalRecord medicalRecord = medicalRecordMapper.toEntity(medicalRecordDTO);
            MedicalRecord updatedRecord = serviceMedicalRecord.update(id, medicalRecord);
            MedicalRecordDTO updatedDTO = medicalRecordMapper.toDTO(updatedRecord);
            return ResponseEntity.ok(updatedDTO);
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
