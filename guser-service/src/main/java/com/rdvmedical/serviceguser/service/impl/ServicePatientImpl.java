package com.rdvmedical.serviceguser.service.impl;

import com.rdvmedical.serviceguser.domain.entity.Patient;
import com.rdvmedical.serviceguser.respository.PatientRepository;
import com.rdvmedical.serviceguser.service.IServicePatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicePatientImpl implements IServicePatient {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Optional<Patient> findByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    @Override
    public Optional<Patient> findByNumeroSecu(String numeroSecu) {
        return patientRepository.findByNumeroSecu(numeroSecu);
    }

    @Override
    public Patient save(Patient patient) {
        if (patientRepository.existsByEmail(patient.getEmail())) {
            throw new RuntimeException("L'email existe déjà: " + patient.getEmail());
        }
        if (patient.getNumeroSecu() != null && patientRepository.existsByNumeroSecu(patient.getNumeroSecu())) {
            throw new RuntimeException("Le numéro de sécurité sociale existe déjà: " + patient.getNumeroSecu());
        }
        return patientRepository.save(patient);
    }

    @Override
    public Patient update(Long id, Patient patient) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'id: " + id));
        
        if (!existingPatient.getEmail().equals(patient.getEmail()) && 
            patientRepository.existsByEmail(patient.getEmail())) {
            throw new RuntimeException("L'email existe déjà: " + patient.getEmail());
        }
        if (patient.getNumeroSecu() != null && 
            !patient.getNumeroSecu().equals(existingPatient.getNumeroSecu()) && 
            patientRepository.existsByNumeroSecu(patient.getNumeroSecu())) {
            throw new RuntimeException("Le numéro de sécurité sociale existe déjà: " + patient.getNumeroSecu());
        }
        
        existingPatient.setNom(patient.getNom());
        existingPatient.setPrenom(patient.getPrenom());
        existingPatient.setEmail(patient.getEmail());
        existingPatient.setTelephone(patient.getTelephone());
        existingPatient.setDateNaissance(patient.getDateNaissance());
        existingPatient.setAdresse(patient.getAdresse());
        existingPatient.setNumeroSecu(patient.getNumeroSecu());
        existingPatient.setActif(patient.getActif());
        
        return patientRepository.save(existingPatient);
    }

    @Override
    public void deleteById(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient non trouvé avec l'id: " + id);
        }
        patientRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return patientRepository.existsById(id);
    }
}

