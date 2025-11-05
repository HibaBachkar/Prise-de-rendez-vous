package com.rdvmedical.serviceguser.domain.mapper;

import com.rdvmedical.serviceguser.domain.dto.MedicalRecordDTO;
import com.rdvmedical.serviceguser.domain.entity.MedicalRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PatientMapper.class, DoctorMapper.class})
public interface MedicalRecordMapper {
    MedicalRecordMapper INSTANCE = Mappers.getMapper(MedicalRecordMapper.class);

    @Mapping(source = "patient.id", target = "patientId")
    @Mapping(source = "patient.nom", target = "patientNom")
    @Mapping(source = "patient.prenom", target = "patientPrenom")
    @Mapping(source = "doctor.id", target = "doctorId")
    @Mapping(source = "doctor.nom", target = "doctorNom")
    @Mapping(source = "doctor.prenom", target = "doctorPrenom")
    MedicalRecordDTO toDTO(MedicalRecord medicalRecord);

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
    MedicalRecord toEntity(MedicalRecordDTO medicalRecordDTO);

    List<MedicalRecordDTO> toDTOList(List<MedicalRecord> medicalRecords);
    List<MedicalRecord> toEntityList(List<MedicalRecordDTO> medicalRecordDTOs);
}

