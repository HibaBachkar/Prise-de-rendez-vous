package com.rdvmedical.serviceguser.service.impl;

import com.rdvmedical.serviceguser.domain.entity.Doctor;
import com.rdvmedical.serviceguser.domain.entity.MedicalRecord;
import com.rdvmedical.serviceguser.domain.entity.Patient;
import com.rdvmedical.serviceguser.respository.DoctorRepository;
import com.rdvmedical.serviceguser.respository.MedicalRecordRepository;
import com.rdvmedical.serviceguser.respository.PatientRepository;
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

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

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
        // Charger le patient si seulement l'ID est fourni
        if (medicalRecord.getPatient() != null && medicalRecord.getPatient().getId() != null && medicalRecord.getPatient().getNom() == null) {
            Patient patient = patientRepository.findById(medicalRecord.getPatient().getId())
                    .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'id: " + medicalRecord.getPatient().getId()));
            medicalRecord.setPatient(patient);
        }
        // Charger le docteur si seulement l'ID est fourni
        if (medicalRecord.getDoctor() != null && medicalRecord.getDoctor().getId() != null && medicalRecord.getDoctor().getNom() == null) {
            Doctor doctor = doctorRepository.findById(medicalRecord.getDoctor().getId())
                    .orElseThrow(() -> new RuntimeException("Docteur non trouvé avec l'id: " + medicalRecord.getDoctor().getId()));
            medicalRecord.setDoctor(doctor);
        }
        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord update(Long id, MedicalRecord medicalRecord) {
        MedicalRecord existingRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dossier médical non trouvé avec l'id: " + id));
        
        // Charger le patient si seulement l'ID est fourni
        if (medicalRecord.getPatient() != null && medicalRecord.getPatient().getId() != null && medicalRecord.getPatient().getNom() == null) {
            Patient patient = patientRepository.findById(medicalRecord.getPatient().getId())
                    .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'id: " + medicalRecord.getPatient().getId()));
            existingRecord.setPatient(patient);
        } else if (medicalRecord.getPatient() != null) {
            existingRecord.setPatient(medicalRecord.getPatient());
        }
        
        // Charger le docteur si seulement l'ID est fourni
        if (medicalRecord.getDoctor() != null && medicalRecord.getDoctor().getId() != null && medicalRecord.getDoctor().getNom() == null) {
            Doctor doctor = doctorRepository.findById(medicalRecord.getDoctor().getId())
                    .orElseThrow(() -> new RuntimeException("Docteur non trouvé avec l'id: " + medicalRecord.getDoctor().getId()));
            existingRecord.setDoctor(doctor);
        } else if (medicalRecord.getDoctor() != null) {
            existingRecord.setDoctor(medicalRecord.getDoctor());
        }
        
        existingRecord.setDateConsultation(medicalRecord.getDateConsultation());
        existingRecord.setDiagnostic(medicalRecord.getDiagnostic());
        existingRecord.setSymptomes(medicalRecord.getSymptomes());
        existingRecord.setTraitement(medicalRecord.getTraitement());
        existingRecord.setNotes(medicalRecord.getNotes());
        
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

