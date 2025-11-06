package com.rdvmedical.serviceguser.domain.mapper;

import com.rdvmedical.serviceguser.domain.dto.DoctorDTO;
import com.rdvmedical.serviceguser.domain.entity.Doctor;
import com.rdvmedical.serviceguser.domain.entity.Specialty;
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
public class DoctorMapperImpl implements DoctorMapper {

    @Override
    public DoctorDTO toDTO(Doctor doctor) {
        if ( doctor == null ) {
            return null;
        }

        DoctorDTO doctorDTO = new DoctorDTO();

        doctorDTO.setSpecialtyId( doctorSpecialtyId( doctor ) );
        doctorDTO.setSpecialtyNom( doctorSpecialtyNom( doctor ) );
        doctorDTO.setId( doctor.getId() );
        doctorDTO.setNom( doctor.getNom() );
        doctorDTO.setPrenom( doctor.getPrenom() );
        doctorDTO.setEmail( doctor.getEmail() );
        doctorDTO.setTelephone( doctor.getTelephone() );
        doctorDTO.setNumeroRpps( doctor.getNumeroRpps() );
        doctorDTO.setActif( doctor.getActif() );

        return doctorDTO;
    }

    @Override
    public Doctor toEntity(DoctorDTO doctorDTO) {
        if ( doctorDTO == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setSpecialty( doctorDTOToSpecialty( doctorDTO ) );
        doctor.setId( doctorDTO.getId() );
        doctor.setNom( doctorDTO.getNom() );
        doctor.setPrenom( doctorDTO.getPrenom() );
        doctor.setEmail( doctorDTO.getEmail() );
        doctor.setTelephone( doctorDTO.getTelephone() );
        doctor.setNumeroRpps( doctorDTO.getNumeroRpps() );
        doctor.setActif( doctorDTO.getActif() );

        return doctor;
    }

    @Override
    public List<DoctorDTO> toDTOList(List<Doctor> doctors) {
        if ( doctors == null ) {
            return null;
        }

        List<DoctorDTO> list = new ArrayList<DoctorDTO>( doctors.size() );
        for ( Doctor doctor : doctors ) {
            list.add( toDTO( doctor ) );
        }

        return list;
    }

    @Override
    public List<Doctor> toEntityList(List<DoctorDTO> doctorDTOs) {
        if ( doctorDTOs == null ) {
            return null;
        }

        List<Doctor> list = new ArrayList<Doctor>( doctorDTOs.size() );
        for ( DoctorDTO doctorDTO : doctorDTOs ) {
            list.add( toEntity( doctorDTO ) );
        }

        return list;
    }

    private Long doctorSpecialtyId(Doctor doctor) {
        if ( doctor == null ) {
            return null;
        }
        Specialty specialty = doctor.getSpecialty();
        if ( specialty == null ) {
            return null;
        }
        Long id = specialty.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String doctorSpecialtyNom(Doctor doctor) {
        if ( doctor == null ) {
            return null;
        }
        Specialty specialty = doctor.getSpecialty();
        if ( specialty == null ) {
            return null;
        }
        String nom = specialty.getNom();
        if ( nom == null ) {
            return null;
        }
        return nom;
    }

    protected Specialty doctorDTOToSpecialty(DoctorDTO doctorDTO) {
        if ( doctorDTO == null ) {
            return null;
        }

        Specialty specialty = new Specialty();

        specialty.setId( doctorDTO.getSpecialtyId() );

        return specialty;
    }
}
