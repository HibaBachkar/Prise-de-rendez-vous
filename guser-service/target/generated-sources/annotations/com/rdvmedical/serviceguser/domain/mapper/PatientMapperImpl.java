package com.rdvmedical.serviceguser.domain.mapper;

import com.rdvmedical.serviceguser.domain.dto.PatientDTO;
import com.rdvmedical.serviceguser.domain.entity.Patient;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-06T14:14:40+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class PatientMapperImpl implements PatientMapper {

    @Override
    public PatientDTO toDTO(Patient patient) {
        if ( patient == null ) {
            return null;
        }

        PatientDTO patientDTO = new PatientDTO();

        patientDTO.setId( patient.getId() );
        patientDTO.setNom( patient.getNom() );
        patientDTO.setPrenom( patient.getPrenom() );
        patientDTO.setEmail( patient.getEmail() );
        patientDTO.setTelephone( patient.getTelephone() );
        patientDTO.setDateNaissance( patient.getDateNaissance() );
        patientDTO.setAdresse( patient.getAdresse() );
        patientDTO.setNumeroSecu( patient.getNumeroSecu() );
        patientDTO.setActif( patient.getActif() );

        return patientDTO;
    }

    @Override
    public Patient toEntity(PatientDTO patientDTO) {
        if ( patientDTO == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setId( patientDTO.getId() );
        patient.setNom( patientDTO.getNom() );
        patient.setPrenom( patientDTO.getPrenom() );
        patient.setEmail( patientDTO.getEmail() );
        patient.setTelephone( patientDTO.getTelephone() );
        patient.setDateNaissance( patientDTO.getDateNaissance() );
        patient.setAdresse( patientDTO.getAdresse() );
        patient.setNumeroSecu( patientDTO.getNumeroSecu() );
        patient.setActif( patientDTO.getActif() );

        return patient;
    }

    @Override
    public List<PatientDTO> toDTOList(List<Patient> patients) {
        if ( patients == null ) {
            return null;
        }

        List<PatientDTO> list = new ArrayList<PatientDTO>( patients.size() );
        for ( Patient patient : patients ) {
            list.add( toDTO( patient ) );
        }

        return list;
    }

    @Override
    public List<Patient> toEntityList(List<PatientDTO> patientDTOs) {
        if ( patientDTOs == null ) {
            return null;
        }

        List<Patient> list = new ArrayList<Patient>( patientDTOs.size() );
        for ( PatientDTO patientDTO : patientDTOs ) {
            list.add( toEntity( patientDTO ) );
        }

        return list;
    }
}
