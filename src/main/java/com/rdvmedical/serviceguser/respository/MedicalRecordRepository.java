package com.rdvmedical.serviceguser.respository;

import com.rdvmedical.serviceguser.domain.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByPatientId(Long patientId);
    List<MedicalRecord> findByPatientIdOrderByDateConsultationDesc(Long patientId);
    List<MedicalRecord> findByPatientIdAndDateConsultationBetween(Long patientId, Date start, Date end);
    List<MedicalRecord> findByDoctorId(Long doctorId);
}

