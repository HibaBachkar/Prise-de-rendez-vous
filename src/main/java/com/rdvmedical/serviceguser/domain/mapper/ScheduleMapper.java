package com.rdvmedical.serviceguser.domain.mapper;

import com.rdvmedical.serviceguser.domain.dto.ScheduleDTO;
import com.rdvmedical.serviceguser.domain.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DoctorMapper.class})
public interface ScheduleMapper {
    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    @Mapping(source = "doctor.id", target = "doctorId")
    @Mapping(source = "doctor.nom", target = "doctorNom")
    @Mapping(source = "doctor.prenom", target = "doctorPrenom")
    ScheduleDTO toDTO(Schedule schedule);

    @Mapping(source = "doctorId", target = "doctor.id")
    @Mapping(target = "doctor.nom", ignore = true)
    @Mapping(target = "doctor.prenom", ignore = true)
    @Mapping(target = "doctor.email", ignore = true)
    @Mapping(target = "doctor.telephone", ignore = true)
    @Mapping(target = "doctor.numeroRpps", ignore = true)
    @Mapping(target = "doctor.specialty", ignore = true)
    @Mapping(target = "doctor.actif", ignore = true)
    Schedule toEntity(ScheduleDTO scheduleDTO);

    List<ScheduleDTO> toDTOList(List<Schedule> schedules);
    List<Schedule> toEntityList(List<ScheduleDTO> scheduleDTOs);
}

