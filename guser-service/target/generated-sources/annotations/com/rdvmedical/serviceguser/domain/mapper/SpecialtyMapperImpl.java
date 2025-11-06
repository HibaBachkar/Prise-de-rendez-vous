package com.rdvmedical.serviceguser.domain.mapper;

import com.rdvmedical.serviceguser.domain.dto.SpecialtyDTO;
import com.rdvmedical.serviceguser.domain.entity.Specialty;
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
public class SpecialtyMapperImpl implements SpecialtyMapper {

    @Override
    public SpecialtyDTO toDTO(Specialty specialty) {
        if ( specialty == null ) {
            return null;
        }

        SpecialtyDTO specialtyDTO = new SpecialtyDTO();

        specialtyDTO.setId( specialty.getId() );
        specialtyDTO.setNom( specialty.getNom() );
        specialtyDTO.setDescription( specialty.getDescription() );

        return specialtyDTO;
    }

    @Override
    public Specialty toEntity(SpecialtyDTO specialtyDTO) {
        if ( specialtyDTO == null ) {
            return null;
        }

        Specialty specialty = new Specialty();

        specialty.setId( specialtyDTO.getId() );
        specialty.setNom( specialtyDTO.getNom() );
        specialty.setDescription( specialtyDTO.getDescription() );

        return specialty;
    }

    @Override
    public List<SpecialtyDTO> toDTOList(List<Specialty> specialties) {
        if ( specialties == null ) {
            return null;
        }

        List<SpecialtyDTO> list = new ArrayList<SpecialtyDTO>( specialties.size() );
        for ( Specialty specialty : specialties ) {
            list.add( toDTO( specialty ) );
        }

        return list;
    }

    @Override
    public List<Specialty> toEntityList(List<SpecialtyDTO> specialtyDTOs) {
        if ( specialtyDTOs == null ) {
            return null;
        }

        List<Specialty> list = new ArrayList<Specialty>( specialtyDTOs.size() );
        for ( SpecialtyDTO specialtyDTO : specialtyDTOs ) {
            list.add( toEntity( specialtyDTO ) );
        }

        return list;
    }
}
