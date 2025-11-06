package com.rdvmedical.serviceguser.domain.mapper;

import com.rdvmedical.serviceguser.domain.dto.AvailabilityDTO;
import com.rdvmedical.serviceguser.domain.entity.Availability;
import com.rdvmedical.serviceguser.domain.entity.Doctor;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-06T00:37:03+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class AvailabilityMapperImpl implements AvailabilityMapper {

    @Override
    public AvailabilityDTO toDTO(Availability availability) {
        if ( availability == null ) {
            return null;
        }

        AvailabilityDTO availabilityDTO = new AvailabilityDTO();

        availabilityDTO.setDoctorId( availabilityDoctorId( availability ) );
        availabilityDTO.setDoctorNom( availabilityDoctorNom( availability ) );
        availabilityDTO.setDoctorPrenom( availabilityDoctorPrenom( availability ) );
        availabilityDTO.setId( availability.getId() );
        availabilityDTO.setDateDebut( availability.getDateDebut() );
        availabilityDTO.setDateFin( availability.getDateFin() );
        availabilityDTO.setDisponible( availability.getDisponible() );

        return availabilityDTO;
    }

    @Override
    public Availability toEntity(AvailabilityDTO availabilityDTO) {
        if ( availabilityDTO == null ) {
            return null;
        }

        Availability availability = new Availability();

        availability.setDoctor( availabilityDTOToDoctor( availabilityDTO ) );
        availability.setId( availabilityDTO.getId() );
        availability.setDateDebut( availabilityDTO.getDateDebut() );
        availability.setDateFin( availabilityDTO.getDateFin() );
        availability.setDisponible( availabilityDTO.getDisponible() );

        return availability;
    }

    @Override
    public List<AvailabilityDTO> toDTOList(List<Availability> availabilities) {
        if ( availabilities == null ) {
            return null;
        }

        List<AvailabilityDTO> list = new ArrayList<AvailabilityDTO>( availabilities.size() );
        for ( Availability availability : availabilities ) {
            list.add( toDTO( availability ) );
        }

        return list;
    }

    @Override
    public List<Availability> toEntityList(List<AvailabilityDTO> availabilityDTOs) {
        if ( availabilityDTOs == null ) {
            return null;
        }

        List<Availability> list = new ArrayList<Availability>( availabilityDTOs.size() );
        for ( AvailabilityDTO availabilityDTO : availabilityDTOs ) {
            list.add( toEntity( availabilityDTO ) );
        }

        return list;
    }

    private Long availabilityDoctorId(Availability availability) {
        if ( availability == null ) {
            return null;
        }
        Doctor doctor = availability.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        Long id = doctor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String availabilityDoctorNom(Availability availability) {
        if ( availability == null ) {
            return null;
        }
        Doctor doctor = availability.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        String nom = doctor.getNom();
        if ( nom == null ) {
            return null;
        }
        return nom;
    }

    private String availabilityDoctorPrenom(Availability availability) {
        if ( availability == null ) {
            return null;
        }
        Doctor doctor = availability.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        String prenom = doctor.getPrenom();
        if ( prenom == null ) {
            return null;
        }
        return prenom;
    }

    protected Doctor availabilityDTOToDoctor(AvailabilityDTO availabilityDTO) {
        if ( availabilityDTO == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setId( availabilityDTO.getDoctorId() );

        return doctor;
    }
}
