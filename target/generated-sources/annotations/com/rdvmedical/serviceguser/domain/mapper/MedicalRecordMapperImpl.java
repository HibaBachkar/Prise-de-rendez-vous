package com.rdvmedical.serviceguser.domain.mapper;

import com.rdvmedical.serviceguser.domain.dto.MedicalRecordDTO;
import com.rdvmedical.serviceguser.domain.entity.Doctor;
import com.rdvmedical.serviceguser.domain.entity.MedicalRecord;
import com.rdvmedical.serviceguser.domain.entity.Patient;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-06T00:37:04+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class MedicalRecordMapperImpl implements MedicalRecordMapper {

    @Override
    public MedicalRecordDTO toDTO(MedicalRecord medicalRecord) {
        if ( medicalRecord == null ) {
            return null;
        }

        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();

        medicalRecordDTO.setPatientId( medicalRecordPatientId( medicalRecord ) );
        medicalRecordDTO.setPatientNom( medicalRecordPatientNom( medicalRecord ) );
        medicalRecordDTO.setPatientPrenom( medicalRecordPatientPrenom( medicalRecord ) );
        medicalRecordDTO.setDoctorId( medicalRecordDoctorId( medicalRecord ) );
        medicalRecordDTO.setDoctorNom( medicalRecordDoctorNom( medicalRecord ) );
        medicalRecordDTO.setDoctorPrenom( medicalRecordDoctorPrenom( medicalRecord ) );
        medicalRecordDTO.setId( medicalRecord.getId() );
        medicalRecordDTO.setDateConsultation( medicalRecord.getDateConsultation() );
        medicalRecordDTO.setDiagnostic( medicalRecord.getDiagnostic() );
        medicalRecordDTO.setSymptomes( medicalRecord.getSymptomes() );
        medicalRecordDTO.setTraitement( medicalRecord.getTraitement() );
        medicalRecordDTO.setNotes( medicalRecord.getNotes() );

        return medicalRecordDTO;
    }

    @Override
    public MedicalRecord toEntity(MedicalRecordDTO medicalRecordDTO) {
        if ( medicalRecordDTO == null ) {
            return null;
        }

        MedicalRecord medicalRecord = new MedicalRecord();

        medicalRecord.setPatient( medicalRecordDTOToPatient( medicalRecordDTO ) );
        medicalRecord.setDoctor( medicalRecordDTOToDoctor( medicalRecordDTO ) );
        medicalRecord.setId( medicalRecordDTO.getId() );
        medicalRecord.setDateConsultation( medicalRecordDTO.getDateConsultation() );
        medicalRecord.setDiagnostic( medicalRecordDTO.getDiagnostic() );
        medicalRecord.setSymptomes( medicalRecordDTO.getSymptomes() );
        medicalRecord.setTraitement( medicalRecordDTO.getTraitement() );
        medicalRecord.setNotes( medicalRecordDTO.getNotes() );

        return medicalRecord;
    }

    @Override
    public List<MedicalRecordDTO> toDTOList(List<MedicalRecord> medicalRecords) {
        if ( medicalRecords == null ) {
            return null;
        }

        List<MedicalRecordDTO> list = new ArrayList<MedicalRecordDTO>( medicalRecords.size() );
        for ( MedicalRecord medicalRecord : medicalRecords ) {
            list.add( toDTO( medicalRecord ) );
        }

        return list;
    }

    @Override
    public List<MedicalRecord> toEntityList(List<MedicalRecordDTO> medicalRecordDTOs) {
        if ( medicalRecordDTOs == null ) {
            return null;
        }

        List<MedicalRecord> list = new ArrayList<MedicalRecord>( medicalRecordDTOs.size() );
        for ( MedicalRecordDTO medicalRecordDTO : medicalRecordDTOs ) {
            list.add( toEntity( medicalRecordDTO ) );
        }

        return list;
    }

    private Long medicalRecordPatientId(MedicalRecord medicalRecord) {
        if ( medicalRecord == null ) {
            return null;
        }
        Patient patient = medicalRecord.getPatient();
        if ( patient == null ) {
            return null;
        }
        Long id = patient.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String medicalRecordPatientNom(MedicalRecord medicalRecord) {
        if ( medicalRecord == null ) {
            return null;
        }
        Patient patient = medicalRecord.getPatient();
        if ( patient == null ) {
            return null;
        }
        String nom = patient.getNom();
        if ( nom == null ) {
            return null;
        }
        return nom;
    }

    private String medicalRecordPatientPrenom(MedicalRecord medicalRecord) {
        if ( medicalRecord == null ) {
            return null;
        }
        Patient patient = medicalRecord.getPatient();
        if ( patient == null ) {
            return null;
        }
        String prenom = patient.getPrenom();
        if ( prenom == null ) {
            return null;
        }
        return prenom;
    }

    private Long medicalRecordDoctorId(MedicalRecord medicalRecord) {
        if ( medicalRecord == null ) {
            return null;
        }
        Doctor doctor = medicalRecord.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        Long id = doctor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String medicalRecordDoctorNom(MedicalRecord medicalRecord) {
        if ( medicalRecord == null ) {
            return null;
        }
        Doctor doctor = medicalRecord.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        String nom = doctor.getNom();
        if ( nom == null ) {
            return null;
        }
        return nom;
    }

    private String medicalRecordDoctorPrenom(MedicalRecord medicalRecord) {
        if ( medicalRecord == null ) {
            return null;
        }
        Doctor doctor = medicalRecord.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        String prenom = doctor.getPrenom();
        if ( prenom == null ) {
            return null;
        }
        return prenom;
    }

    protected Patient medicalRecordDTOToPatient(MedicalRecordDTO medicalRecordDTO) {
        if ( medicalRecordDTO == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setId( medicalRecordDTO.getPatientId() );

        return patient;
    }

    protected Doctor medicalRecordDTOToDoctor(MedicalRecordDTO medicalRecordDTO) {
        if ( medicalRecordDTO == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setId( medicalRecordDTO.getDoctorId() );

        return doctor;
    }
}
