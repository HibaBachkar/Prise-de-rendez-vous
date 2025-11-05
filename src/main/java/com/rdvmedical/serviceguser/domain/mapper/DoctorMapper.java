package com.rdvmedical.serviceguser.domain.mapper;

import com.rdvmedical.serviceguser.domain.dto.DoctorDTO;
import com.rdvmedical.serviceguser.domain.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SpecialtyMapper.class})
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    @Mapping(source = "specialty.id", target = "specialtyId")
    @Mapping(source = "specialty.nom", target = "specialtyNom")
    DoctorDTO toDTO(Doctor doctor);

    @Mapping(source = "specialtyId", target = "specialty.id")
    @Mapping(target = "specialty.nom", ignore = true)
    @Mapping(target = "specialty.description", ignore = true)
    Doctor toEntity(DoctorDTO doctorDTO);

    List<DoctorDTO> toDTOList(List<Doctor> doctors);
    List<Doctor> toEntityList(List<DoctorDTO> doctorDTOs);
}

