package com.rdvmedical.serviceguser.service;

import com.rdvmedical.serviceguser.domain.entity.MedicalRecord;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IServiceMedicalRecord {
    List<MedicalRecord> findAll();
    Optional<MedicalRecord> findById(Long id);
    List<MedicalRecord> findByPatientId(Long patientId);
    List<MedicalRecord> findByPatientIdOrderByDateConsultationDesc(Long patientId);
    List<MedicalRecord> findByPatientIdAndDateConsultationBetween(Long patientId, Date start, Date end);
    List<MedicalRecord> findByDoctorId(Long doctorId);
    MedicalRecord save(MedicalRecord medicalRecord);
    MedicalRecord update(Long id, MedicalRecord medicalRecord);
    void deleteById(Long id);
    boolean existsById(Long id);
}

