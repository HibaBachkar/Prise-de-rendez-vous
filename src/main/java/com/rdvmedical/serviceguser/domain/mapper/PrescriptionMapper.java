package com.rdvmedical.serviceguser.domain.mapper;

import com.rdvmedical.serviceguser.domain.dto.PrescriptionDTO;
import com.rdvmedical.serviceguser.domain.entity.Prescription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ConsultationMapper.class})
public interface PrescriptionMapper {
    PrescriptionMapper INSTANCE = Mappers.getMapper(PrescriptionMapper.class);

    @Mapping(source = "consultation.id", target = "consultationId")
    PrescriptionDTO toDTO(Prescription prescription);

    @Mapping(source = "consultationId", target = "consultation.id")
    @Mapping(target = "consultation.appointment", ignore = true)
    @Mapping(target = "consultation.dateConsultation", ignore = true)
    @Mapping(target = "consultation.compteRendu", ignore = true)
    @Mapping(target = "consultation.diagnostic", ignore = true)
    @Mapping(target = "consultation.symptomes", ignore = true)
    @Mapping(target = "consultation.examenClinique", ignore = true)
    @Mapping(target = "consultation.prescriptionRecommandee", ignore = true)
    Prescription toEntity(PrescriptionDTO prescriptionDTO);

    List<PrescriptionDTO> toDTOList(List<Prescription> prescriptions);
    List<Prescription> toEntityList(List<PrescriptionDTO> prescriptionDTOs);
}

