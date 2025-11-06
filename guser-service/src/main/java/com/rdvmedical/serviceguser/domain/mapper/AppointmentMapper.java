package com.rdvmedical.serviceguser.domain.mapper;

import com.rdvmedical.serviceguser.domain.dto.AppointmentDTO;
import com.rdvmedical.serviceguser.domain.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PatientMapper.class, DoctorMapper.class})
public interface AppointmentMapper {
    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    @Mapping(source = "patient.id", target = "patientId")
    @Mapping(source = "patient.nom", target = "patientNom")
    @Mapping(source = "patient.prenom", target = "patientPrenom")
    @Mapping(source = "doctor.id", target = "doctorId")
    @Mapping(source = "doctor.nom", target = "doctorNom")
    @Mapping(source = "doctor.prenom", target = "doctorPrenom")
    @Mapping(source = "statut", target = "statut", qualifiedByName = "statusToString")
    AppointmentDTO toDTO(Appointment appointment);

    @Mapping(source = "patientId", target = "patient.id")
    @Mapping(target = "patient.nom", ignore = true)
    @Mapping(target = "patient.prenom", ignore = true)
    @Mapping(target = "patient.email", ignore = true)
    @Mapping(target = "patient.telephone", ignore = true)
    @Mapping(target = "patient.dateNaissance", ignore = true)
    @Mapping(target = "patient.adresse", ignore = true)
    @Mapping(target = "patient.numeroSecu", ignore = true)
    @Mapping(target = "patient.actif", ignore = true)
    @Mapping(source = "doctorId", target = "doctor.id")
    @Mapping(target = "doctor.nom", ignore = true)
    @Mapping(target = "doctor.prenom", ignore = true)
    @Mapping(target = "doctor.email", ignore = true)
    @Mapping(target = "doctor.telephone", ignore = true)
    @Mapping(target = "doctor.numeroRpps", ignore = true)
    @Mapping(target = "doctor.specialty", ignore = true)
    @Mapping(target = "doctor.actif", ignore = true)
    @Mapping(source = "statut", target = "statut", qualifiedByName = "stringToStatus")
    Appointment toEntity(AppointmentDTO appointmentDTO);

    @Named("statusToString")
    default String statusToString(Appointment.AppointmentStatus status) {
        return status != null ? status.name() : Appointment.AppointmentStatus.PLANIFIE.name();
    }

    @Named("stringToStatus")
    default Appointment.AppointmentStatus stringToStatus(String statut) {
        if (statut == null || statut.isEmpty()) {
            return Appointment.AppointmentStatus.PLANIFIE;
        }
        try {
            return Appointment.AppointmentStatus.valueOf(statut.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Appointment.AppointmentStatus.PLANIFIE;
        }
    }

    List<AppointmentDTO> toDTOList(List<Appointment> appointments);
    List<Appointment> toEntityList(List<AppointmentDTO> appointmentDTOs);
}

