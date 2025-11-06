package com.rdvmedical.serviceguser.domain.mapper;

import com.rdvmedical.serviceguser.domain.dto.AppointmentDTO;
import com.rdvmedical.serviceguser.domain.entity.Appointment;
import com.rdvmedical.serviceguser.domain.entity.Doctor;
import com.rdvmedical.serviceguser.domain.entity.Patient;
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
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public AppointmentDTO toDTO(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        AppointmentDTO appointmentDTO = new AppointmentDTO();

        appointmentDTO.setPatientId( appointmentPatientId( appointment ) );
        appointmentDTO.setPatientNom( appointmentPatientNom( appointment ) );
        appointmentDTO.setPatientPrenom( appointmentPatientPrenom( appointment ) );
        appointmentDTO.setDoctorId( appointmentDoctorId( appointment ) );
        appointmentDTO.setDoctorNom( appointmentDoctorNom( appointment ) );
        appointmentDTO.setDoctorPrenom( appointmentDoctorPrenom( appointment ) );
        appointmentDTO.setStatut( statusToString( appointment.getStatut() ) );
        appointmentDTO.setId( appointment.getId() );
        appointmentDTO.setDateHeure( appointment.getDateHeure() );
        appointmentDTO.setDuree( appointment.getDuree() );
        appointmentDTO.setMotif( appointment.getMotif() );
        appointmentDTO.setNotes( appointment.getNotes() );

        return appointmentDTO;
    }

    @Override
    public Appointment toEntity(AppointmentDTO appointmentDTO) {
        if ( appointmentDTO == null ) {
            return null;
        }

        Appointment appointment = new Appointment();

        appointment.setPatient( appointmentDTOToPatient( appointmentDTO ) );
        appointment.setDoctor( appointmentDTOToDoctor( appointmentDTO ) );
        appointment.setStatut( stringToStatus( appointmentDTO.getStatut() ) );
        appointment.setId( appointmentDTO.getId() );
        appointment.setDateHeure( appointmentDTO.getDateHeure() );
        appointment.setDuree( appointmentDTO.getDuree() );
        appointment.setMotif( appointmentDTO.getMotif() );
        appointment.setNotes( appointmentDTO.getNotes() );

        return appointment;
    }

    @Override
    public List<AppointmentDTO> toDTOList(List<Appointment> appointments) {
        if ( appointments == null ) {
            return null;
        }

        List<AppointmentDTO> list = new ArrayList<AppointmentDTO>( appointments.size() );
        for ( Appointment appointment : appointments ) {
            list.add( toDTO( appointment ) );
        }

        return list;
    }

    @Override
    public List<Appointment> toEntityList(List<AppointmentDTO> appointmentDTOs) {
        if ( appointmentDTOs == null ) {
            return null;
        }

        List<Appointment> list = new ArrayList<Appointment>( appointmentDTOs.size() );
        for ( AppointmentDTO appointmentDTO : appointmentDTOs ) {
            list.add( toEntity( appointmentDTO ) );
        }

        return list;
    }

    private Long appointmentPatientId(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }
        Patient patient = appointment.getPatient();
        if ( patient == null ) {
            return null;
        }
        Long id = patient.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String appointmentPatientNom(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }
        Patient patient = appointment.getPatient();
        if ( patient == null ) {
            return null;
        }
        String nom = patient.getNom();
        if ( nom == null ) {
            return null;
        }
        return nom;
    }

    private String appointmentPatientPrenom(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }
        Patient patient = appointment.getPatient();
        if ( patient == null ) {
            return null;
        }
        String prenom = patient.getPrenom();
        if ( prenom == null ) {
            return null;
        }
        return prenom;
    }

    private Long appointmentDoctorId(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }
        Doctor doctor = appointment.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        Long id = doctor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String appointmentDoctorNom(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }
        Doctor doctor = appointment.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        String nom = doctor.getNom();
        if ( nom == null ) {
            return null;
        }
        return nom;
    }

    private String appointmentDoctorPrenom(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }
        Doctor doctor = appointment.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        String prenom = doctor.getPrenom();
        if ( prenom == null ) {
            return null;
        }
        return prenom;
    }

    protected Patient appointmentDTOToPatient(AppointmentDTO appointmentDTO) {
        if ( appointmentDTO == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setId( appointmentDTO.getPatientId() );

        return patient;
    }

    protected Doctor appointmentDTOToDoctor(AppointmentDTO appointmentDTO) {
        if ( appointmentDTO == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setId( appointmentDTO.getDoctorId() );

        return doctor;
    }
}
