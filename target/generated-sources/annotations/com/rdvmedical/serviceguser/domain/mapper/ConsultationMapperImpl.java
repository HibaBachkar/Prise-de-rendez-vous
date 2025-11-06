package com.rdvmedical.serviceguser.domain.mapper;

import com.rdvmedical.serviceguser.domain.dto.ConsultationDTO;
import com.rdvmedical.serviceguser.domain.entity.Appointment;
import com.rdvmedical.serviceguser.domain.entity.Consultation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-06T00:37:04+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class ConsultationMapperImpl implements ConsultationMapper {

    @Override
    public ConsultationDTO toDTO(Consultation consultation) {
        if ( consultation == null ) {
            return null;
        }

        ConsultationDTO consultationDTO = new ConsultationDTO();

        consultationDTO.setAppointmentId( consultationAppointmentId( consultation ) );
        consultationDTO.setId( consultation.getId() );
        consultationDTO.setDateConsultation( consultation.getDateConsultation() );
        consultationDTO.setCompteRendu( consultation.getCompteRendu() );
        consultationDTO.setDiagnostic( consultation.getDiagnostic() );
        consultationDTO.setSymptomes( consultation.getSymptomes() );
        consultationDTO.setExamenClinique( consultation.getExamenClinique() );
        consultationDTO.setPrescriptionRecommandee( consultation.getPrescriptionRecommandee() );

        return consultationDTO;
    }

    @Override
    public Consultation toEntity(ConsultationDTO consultationDTO) {
        if ( consultationDTO == null ) {
            return null;
        }

        Consultation consultation = new Consultation();

        consultation.setAppointment( consultationDTOToAppointment( consultationDTO ) );
        consultation.setId( consultationDTO.getId() );
        consultation.setDateConsultation( consultationDTO.getDateConsultation() );
        consultation.setCompteRendu( consultationDTO.getCompteRendu() );
        consultation.setDiagnostic( consultationDTO.getDiagnostic() );
        consultation.setSymptomes( consultationDTO.getSymptomes() );
        consultation.setExamenClinique( consultationDTO.getExamenClinique() );
        consultation.setPrescriptionRecommandee( consultationDTO.getPrescriptionRecommandee() );

        return consultation;
    }

    @Override
    public List<ConsultationDTO> toDTOList(List<Consultation> consultations) {
        if ( consultations == null ) {
            return null;
        }

        List<ConsultationDTO> list = new ArrayList<ConsultationDTO>( consultations.size() );
        for ( Consultation consultation : consultations ) {
            list.add( toDTO( consultation ) );
        }

        return list;
    }

    @Override
    public List<Consultation> toEntityList(List<ConsultationDTO> consultationDTOs) {
        if ( consultationDTOs == null ) {
            return null;
        }

        List<Consultation> list = new ArrayList<Consultation>( consultationDTOs.size() );
        for ( ConsultationDTO consultationDTO : consultationDTOs ) {
            list.add( toEntity( consultationDTO ) );
        }

        return list;
    }

    private Long consultationAppointmentId(Consultation consultation) {
        if ( consultation == null ) {
            return null;
        }
        Appointment appointment = consultation.getAppointment();
        if ( appointment == null ) {
            return null;
        }
        Long id = appointment.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Appointment consultationDTOToAppointment(ConsultationDTO consultationDTO) {
        if ( consultationDTO == null ) {
            return null;
        }

        Appointment appointment = new Appointment();

        appointment.setId( consultationDTO.getAppointmentId() );

        return appointment;
    }
}
