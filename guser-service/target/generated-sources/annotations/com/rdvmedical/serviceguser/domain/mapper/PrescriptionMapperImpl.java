package com.rdvmedical.serviceguser.domain.mapper;

import com.rdvmedical.serviceguser.domain.dto.PrescriptionDTO;
import com.rdvmedical.serviceguser.domain.entity.Consultation;
import com.rdvmedical.serviceguser.domain.entity.Prescription;
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
public class PrescriptionMapperImpl implements PrescriptionMapper {

    @Override
    public PrescriptionDTO toDTO(Prescription prescription) {
        if ( prescription == null ) {
            return null;
        }

        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();

        prescriptionDTO.setConsultationId( prescriptionConsultationId( prescription ) );
        prescriptionDTO.setId( prescription.getId() );
        prescriptionDTO.setDatePrescription( prescription.getDatePrescription() );
        prescriptionDTO.setMedicaments( prescription.getMedicaments() );
        prescriptionDTO.setPosologie( prescription.getPosologie() );
        prescriptionDTO.setDureeTraitement( prescription.getDureeTraitement() );
        prescriptionDTO.setInstructions( prescription.getInstructions() );
        prescriptionDTO.setValide( prescription.getValide() );

        return prescriptionDTO;
    }

    @Override
    public Prescription toEntity(PrescriptionDTO prescriptionDTO) {
        if ( prescriptionDTO == null ) {
            return null;
        }

        Prescription prescription = new Prescription();

        prescription.setConsultation( prescriptionDTOToConsultation( prescriptionDTO ) );
        prescription.setId( prescriptionDTO.getId() );
        prescription.setDatePrescription( prescriptionDTO.getDatePrescription() );
        prescription.setMedicaments( prescriptionDTO.getMedicaments() );
        prescription.setPosologie( prescriptionDTO.getPosologie() );
        prescription.setDureeTraitement( prescriptionDTO.getDureeTraitement() );
        prescription.setInstructions( prescriptionDTO.getInstructions() );
        prescription.setValide( prescriptionDTO.getValide() );

        return prescription;
    }

    @Override
    public List<PrescriptionDTO> toDTOList(List<Prescription> prescriptions) {
        if ( prescriptions == null ) {
            return null;
        }

        List<PrescriptionDTO> list = new ArrayList<PrescriptionDTO>( prescriptions.size() );
        for ( Prescription prescription : prescriptions ) {
            list.add( toDTO( prescription ) );
        }

        return list;
    }

    @Override
    public List<Prescription> toEntityList(List<PrescriptionDTO> prescriptionDTOs) {
        if ( prescriptionDTOs == null ) {
            return null;
        }

        List<Prescription> list = new ArrayList<Prescription>( prescriptionDTOs.size() );
        for ( PrescriptionDTO prescriptionDTO : prescriptionDTOs ) {
            list.add( toEntity( prescriptionDTO ) );
        }

        return list;
    }

    private Long prescriptionConsultationId(Prescription prescription) {
        if ( prescription == null ) {
            return null;
        }
        Consultation consultation = prescription.getConsultation();
        if ( consultation == null ) {
            return null;
        }
        Long id = consultation.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Consultation prescriptionDTOToConsultation(PrescriptionDTO prescriptionDTO) {
        if ( prescriptionDTO == null ) {
            return null;
        }

        Consultation consultation = new Consultation();

        consultation.setId( prescriptionDTO.getConsultationId() );

        return consultation;
    }
}
