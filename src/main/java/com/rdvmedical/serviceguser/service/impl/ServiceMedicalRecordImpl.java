package com.rdvmedical.serviceguser.service.impl;

import com.rdvmedical.serviceguser.domain.entity.MedicalRecord;
import com.rdvmedical.serviceguser.respository.MedicalRecordRepository;
import com.rdvmedical.serviceguser.service.IServiceMedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceMedicalRecordImpl implements IServiceMedicalRecord {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public List<MedicalRecord> findAll() {
        return medicalRecordRepository.findAll();
    }

    @Override
    public Optional<MedicalRecord> findById(Long id) {
        return medicalRecordRepository.findById(id);
    }

    @Override
    public List<MedicalRecord> findByPatientId(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }

    @Override
    public List<MedicalRecord> findByPatientIdOrderByDateConsultationDesc(Long patientId) {
        return medicalRecordRepository.findByPatientIdOrderByDateConsultationDesc(patientId);
    }

    @Override
    public List<MedicalRecord> findByPatientIdAndDateConsultationBetween(Long patientId, Date start, Date end) {
        return medicalRecordRepository.findByPatientIdAndDateConsultationBetween(patientId, start, end);
    }

    @Override
    public List<MedicalRecord> findByDoctorId(Long doctorId) {
        return medicalRecordRepository.findByDoctorId(doctorId);
    }

    @Override
    public MedicalRecord save(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord update(Long id, MedicalRecord medicalRecord) {
        MedicalRecord existingRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dossier médical non trouvé avec l'id: " + id));
        
        existingRecord.setPatient(medicalRecord.getPatient());
        existingRecord.setDateConsultation(medicalRecord.getDateConsultation());
        existingRecord.setDiagnostic(medicalRecord.getDiagnostic());
        existingRecord.setSymptomes(medicalRecord.getSymptomes());
        existingRecord.setTraitement(medicalRecord.getTraitement());
        existingRecord.setNotes(medicalRecord.getNotes());
        existingRecord.setDoctor(medicalRecord.getDoctor());
        
        return medicalRecordRepository.save(existingRecord);
    }

    @Override
    public void deleteById(Long id) {
        if (!medicalRecordRepository.existsById(id)) {
            throw new RuntimeException("Dossier médical non trouvé avec l'id: " + id);
        }
        medicalRecordRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return medicalRecordRepository.existsById(id);
    }
}

