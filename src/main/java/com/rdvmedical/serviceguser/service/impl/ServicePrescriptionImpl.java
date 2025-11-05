package com.rdvmedical.serviceguser.service.impl;

import com.rdvmedical.serviceguser.domain.entity.Prescription;
import com.rdvmedical.serviceguser.respository.PrescriptionRepository;
import com.rdvmedical.serviceguser.service.IServicePrescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ServicePrescriptionImpl implements IServicePrescription {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public Optional<Prescription> findById(Long id) {
        return prescriptionRepository.findById(id);
    }

    @Override
    public Optional<Prescription> findByConsultationId(Long consultationId) {
        return prescriptionRepository.findByConsultationId(consultationId);
    }

    @Override
    public Prescription save(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    @Override
    public Prescription update(Long id, Prescription prescription) {
        Prescription existingPrescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription non trouvée avec l'id: " + id));
        
        existingPrescription.setConsultation(prescription.getConsultation());
        existingPrescription.setDatePrescription(prescription.getDatePrescription());
        existingPrescription.setMedicaments(prescription.getMedicaments());
        existingPrescription.setPosologie(prescription.getPosologie());
        existingPrescription.setDureeTraitement(prescription.getDureeTraitement());
        existingPrescription.setInstructions(prescription.getInstructions());
        existingPrescription.setValide(prescription.getValide());
        
        return prescriptionRepository.save(existingPrescription);
    }

    @Override
    public void deleteById(Long id) {
        if (!prescriptionRepository.existsById(id)) {
            throw new RuntimeException("Prescription non trouvée avec l'id: " + id);
        }
        prescriptionRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return prescriptionRepository.existsById(id);
    }
}

