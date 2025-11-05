package com.rdvmedical.serviceguser.service.impl;

import com.rdvmedical.serviceguser.domain.entity.Insurance;
import com.rdvmedical.serviceguser.respository.InsuranceRepository;
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
        return insuranceRepository.save(insurance);
    }

    @Override
    public Insurance update(Long id, Insurance insurance) {
        Insurance existingInsurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assurance non trouvée avec l'id: " + id));
        
        existingInsurance.setNom(insurance.getNom());
        existingInsurance.setNumeroAssure(insurance.getNumeroAssure());
        existingInsurance.setDateDebut(insurance.getDateDebut());
        existingInsurance.setDateFin(insurance.getDateFin());
        existingInsurance.setPatient(insurance.getPatient());
        
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

