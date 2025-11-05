package com.rdvmedical.serviceguser.service.impl;

import com.rdvmedical.serviceguser.domain.entity.Appointment;
import com.rdvmedical.serviceguser.domain.entity.Consultation;
import com.rdvmedical.serviceguser.respository.AppointmentRepository;
import com.rdvmedical.serviceguser.respository.ConsultationRepository;
import com.rdvmedical.serviceguser.service.IServiceConsultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ServiceConsultationImpl implements IServiceConsultation {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Optional<Consultation> findById(Long id) {
        return consultationRepository.findById(id);
    }

    @Override
    public Optional<Consultation> findByAppointmentId(Long appointmentId) {
        return consultationRepository.findByAppointmentId(appointmentId);
    }

    @Override
    public Consultation save(Consultation consultation) {
        // Charger le rendez-vous si seulement l'ID est fourni
        if (consultation.getAppointment() != null && consultation.getAppointment().getId() != null && consultation.getAppointment().getDateHeure() == null) {
            Appointment appointment = appointmentRepository.findById(consultation.getAppointment().getId())
                    .orElseThrow(() -> new RuntimeException("Rendez-vous non trouvé avec l'id: " + consultation.getAppointment().getId()));
            consultation.setAppointment(appointment);
        }
        return consultationRepository.save(consultation);
    }

    @Override
    public Consultation update(Long id, Consultation consultation) {
        Consultation existingConsultation = consultationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation non trouvée avec l'id: " + id));
        
        // Charger le rendez-vous si seulement l'ID est fourni
        if (consultation.getAppointment() != null && consultation.getAppointment().getId() != null && consultation.getAppointment().getDateHeure() == null) {
            Appointment appointment = appointmentRepository.findById(consultation.getAppointment().getId())
                    .orElseThrow(() -> new RuntimeException("Rendez-vous non trouvé avec l'id: " + consultation.getAppointment().getId()));
            existingConsultation.setAppointment(appointment);
        } else if (consultation.getAppointment() != null) {
            existingConsultation.setAppointment(consultation.getAppointment());
        }
        
        existingConsultation.setDateConsultation(consultation.getDateConsultation());
        existingConsultation.setCompteRendu(consultation.getCompteRendu());
        existingConsultation.setDiagnostic(consultation.getDiagnostic());
        existingConsultation.setSymptomes(consultation.getSymptomes());
        existingConsultation.setExamenClinique(consultation.getExamenClinique());
        existingConsultation.setPrescriptionRecommandee(consultation.getPrescriptionRecommandee());
        
        return consultationRepository.save(existingConsultation);
    }

    @Override
    public void deleteById(Long id) {
        if (!consultationRepository.existsById(id)) {
            throw new RuntimeException("Consultation non trouvée avec l'id: " + id);
        }
        consultationRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return consultationRepository.existsById(id);
    }
}

