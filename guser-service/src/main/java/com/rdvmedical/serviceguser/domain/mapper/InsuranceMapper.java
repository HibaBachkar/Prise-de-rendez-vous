package com.rdvmedical.serviceguser.domain.mapper;

import com.rdvmedical.serviceguser.domain.dto.InsuranceDTO;
import com.rdvmedical.serviceguser.domain.entity.Insurance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PatientMapper.class})
public interface InsuranceMapper {
    InsuranceMapper INSTANCE = Mappers.getMapper(InsuranceMapper.class);

    @Mapping(source = "patient.id", target = "patientId")
    @Mapping(source = "patient.nom", target = "patientNom")
    @Mapping(source = "patient.prenom", target = "patientPrenom")
    InsuranceDTO toDTO(Insurance insurance);

    @Mapping(source = "patientId", target = "patient.id")
    @Mapping(target = "patient.nom", ignore = true)
    @Mapping(target = "patient.prenom", ignore = true)
    @Mapping(target = "patient.email", ignore = true)
    @Mapping(target = "patient.telephone", ignore = true)
    @Mapping(target = "patient.dateNaissance", ignore = true)
    @Mapping(target = "patient.adresse", ignore = true)
    @Mapping(target = "patient.numeroSecu", ignore = true)
    @Mapping(target = "patient.actif", ignore = true)
    Insurance toEntity(InsuranceDTO insuranceDTO);

    List<InsuranceDTO> toDTOList(List<Insurance> insurances);
    List<Insurance> toEntityList(List<InsuranceDTO> insuranceDTOs);
}

