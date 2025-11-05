package com.rdvmedical.serviceguser.domain.mapper;

import com.rdvmedical.serviceguser.domain.dto.ConsultationDTO;
import com.rdvmedical.serviceguser.domain.entity.Consultation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AppointmentMapper.class})
public interface ConsultationMapper {
    ConsultationMapper INSTANCE = Mappers.getMapper(ConsultationMapper.class);

    @Mapping(source = "appointment.id", target = "appointmentId")
    ConsultationDTO toDTO(Consultation consultation);

    @Mapping(source = "appointmentId", target = "appointment.id")
    @Mapping(target = "appointment.patient", ignore = true)
    @Mapping(target = "appointment.doctor", ignore = true)
    @Mapping(target = "appointment.dateHeure", ignore = true)
    @Mapping(target = "appointment.duree", ignore = true)
    @Mapping(target = "appointment.statut", ignore = true)
    @Mapping(target = "appointment.motif", ignore = true)
    @Mapping(target = "appointment.notes", ignore = true)
    Consultation toEntity(ConsultationDTO consultationDTO);

    List<ConsultationDTO> toDTOList(List<Consultation> consultations);
    List<Consultation> toEntityList(List<ConsultationDTO> consultationDTOs);
}

