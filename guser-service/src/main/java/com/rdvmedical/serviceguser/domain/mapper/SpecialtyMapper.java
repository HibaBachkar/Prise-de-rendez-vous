package com.rdvmedical.serviceguser.domain.mapper;

import com.rdvmedical.serviceguser.domain.dto.SpecialtyDTO;
import com.rdvmedical.serviceguser.domain.entity.Specialty;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {
    SpecialtyMapper INSTANCE = Mappers.getMapper(SpecialtyMapper.class);

    SpecialtyDTO toDTO(Specialty specialty);
    Specialty toEntity(SpecialtyDTO specialtyDTO);
    List<SpecialtyDTO> toDTOList(List<Specialty> specialties);
    List<Specialty> toEntityList(List<SpecialtyDTO> specialtyDTOs);
}

