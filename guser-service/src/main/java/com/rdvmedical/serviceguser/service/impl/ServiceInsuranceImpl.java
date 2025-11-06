package com.rdvmedical.serviceguser.service.impl;

import com.rdvmedical.serviceguser.domain.entity.Insurance;
import com.rdvmedical.serviceguser.domain.entity.Patient;
import com.rdvmedical.serviceguser.respository.InsuranceRepository;
import com.rdvmedical.serviceguser.respository.PatientRepository;
import com.rdvmedical.serviceguser.service.IServiceInsurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceInsuranceImpl implements IServiceInsurance {

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Insurance> findAll() {
        return insuranceRepository.findAll();
    }

    @Override
    public Optional<Insurance> findById(Long id) {
        return insuranceRepository.findById(id);
    }

    @Override
    public List<Insurance> findByPatientId(Long patientId) {
        return insuranceRepository.findByPatientId(patientId);
    }

    @Override
    public Insurance save(Insurance insurance) {
        // Charger le patient si seulement l'ID est fourni
        if (insurance.getPatient() != null && insurance.getPatient().getId() != null && insurance.getPatient().getNom() == null) {
            Patient patient = patientRepository.findById(insurance.getPatient().getId())
                    .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'id: " + insurance.getPatient().getId()));
            insurance.setPatient(patient);
        }
        return insuranceRepository.save(insurance);
    }

    @Override
    public Insurance update(Long id, Insurance insurance) {
        Insurance existingInsurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assurance non trouvée avec l'id: " + id));
        
        // Charger le patient si seulement l'ID est fourni
        if (insurance.getPatient() != null && insurance.getPatient().getId() != null && insurance.getPatient().getNom() == null) {
            Patient patient = patientRepository.findById(insurance.getPatient().getId())
                    .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'id: " + insurance.getPatient().getId()));
            existingInsurance.setPatient(patient);
        } else if (insurance.getPatient() != null) {
            existingInsurance.setPatient(insurance.getPatient());
        }
        
        existingInsurance.setNom(insurance.getNom());
        existingInsurance.setNumeroAssure(insurance.getNumeroAssure());
        existingInsurance.setDateDebut(insurance.getDateDebut());
        existingInsurance.setDateFin(insurance.getDateFin());
        
        return insuranceRepository.save(existingInsurance);
    }

    @Override
    public void deleteById(Long id) {
        if (!insuranceRepository.existsById(id)) {
            throw new RuntimeException("Assurance non trouvée avec l'id: " + id);
        }
        insuranceRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return insuranceRepository.existsById(id);
    }
}

