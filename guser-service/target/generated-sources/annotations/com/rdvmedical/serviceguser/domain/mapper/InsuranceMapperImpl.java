package com.rdvmedical.serviceguser.domain.mapper;

import com.rdvmedical.serviceguser.domain.dto.InsuranceDTO;
import com.rdvmedical.serviceguser.domain.entity.Insurance;
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
public class InsuranceMapperImpl implements InsuranceMapper {

    @Override
    public InsuranceDTO toDTO(Insurance insurance) {
        if ( insurance == null ) {
            return null;
        }

        InsuranceDTO insuranceDTO = new InsuranceDTO();

        insuranceDTO.setPatientId( insurancePatientId( insurance ) );
        insuranceDTO.setPatientNom( insurancePatientNom( insurance ) );
        insuranceDTO.setPatientPrenom( insurancePatientPrenom( insurance ) );
        insuranceDTO.setId( insurance.getId() );
        insuranceDTO.setNom( insurance.getNom() );
        insuranceDTO.setNumeroAssure( insurance.getNumeroAssure() );
        insuranceDTO.setDateDebut( insurance.getDateDebut() );
        insuranceDTO.setDateFin( insurance.getDateFin() );

        return insuranceDTO;
    }

    @Override
    public Insurance toEntity(InsuranceDTO insuranceDTO) {
        if ( insuranceDTO == null ) {
            return null;
        }

        Insurance insurance = new Insurance();

        insurance.setPatient( insuranceDTOToPatient( insuranceDTO ) );
        insurance.setId( insuranceDTO.getId() );
        insurance.setNom( insuranceDTO.getNom() );
        insurance.setNumeroAssure( insuranceDTO.getNumeroAssure() );
        insurance.setDateDebut( insuranceDTO.getDateDebut() );
        insurance.setDateFin( insuranceDTO.getDateFin() );

        return insurance;
    }

    @Override
    public List<InsuranceDTO> toDTOList(List<Insurance> insurances) {
        if ( insurances == null ) {
            return null;
        }

        List<InsuranceDTO> list = new ArrayList<InsuranceDTO>( insurances.size() );
        for ( Insurance insurance : insurances ) {
            list.add( toDTO( insurance ) );
        }

        return list;
    }

    @Override
    public List<Insurance> toEntityList(List<InsuranceDTO> insuranceDTOs) {
        if ( insuranceDTOs == null ) {
            return null;
        }

        List<Insurance> list = new ArrayList<Insurance>( insuranceDTOs.size() );
        for ( InsuranceDTO insuranceDTO : insuranceDTOs ) {
            list.add( toEntity( insuranceDTO ) );
        }

        return list;
    }

    private Long insurancePatientId(Insurance insurance) {
        if ( insurance == null ) {
            return null;
        }
        Patient patient = insurance.getPatient();
        if ( patient == null ) {
            return null;
        }
        Long id = patient.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String insurancePatientNom(Insurance insurance) {
        if ( insurance == null ) {
            return null;
        }
        Patient patient = insurance.getPatient();
        if ( patient == null ) {
            return null;
        }
        String nom = patient.getNom();
        if ( nom == null ) {
            return null;
        }
        return nom;
    }

    private String insurancePatientPrenom(Insurance insurance) {
        if ( insurance == null ) {
            return null;
        }
        Patient patient = insurance.getPatient();
        if ( patient == null ) {
            return null;
        }
        String prenom = patient.getPrenom();
        if ( prenom == null ) {
            return null;
        }
        return prenom;
    }

    protected Patient insuranceDTOToPatient(InsuranceDTO insuranceDTO) {
        if ( insuranceDTO == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setId( insuranceDTO.getPatientId() );

        return patient;
    }
}
