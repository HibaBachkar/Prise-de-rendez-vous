package com.rdvmedical.serviceguser.service.impl;

import com.rdvmedical.serviceguser.domain.entity.Specialty;
import com.rdvmedical.serviceguser.respository.SpecialtyRepository;
import com.rdvmedical.serviceguser.service.IServiceSpecialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceSpecialtyImpl implements IServiceSpecialty {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public List<Specialty> findAll() {
        return specialtyRepository.findAll();
    }

    @Override
    public Optional<Specialty> findById(Long id) {
        return specialtyRepository.findById(id);
    }

    @Override
    public Optional<Specialty> findByNom(String nom) {
        return specialtyRepository.findByNom(nom);
    }

    @Override
    public Specialty save(Specialty specialty) {
        if (specialtyRepository.existsByNom(specialty.getNom())) {
            throw new RuntimeException("La spécialité existe déjà: " + specialty.getNom());
        }
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty update(Long id, Specialty specialty) {
        Specialty existingSpecialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Spécialité non trouvée avec l'id: " + id));
        
        if (!existingSpecialty.getNom().equals(specialty.getNom()) && 
            specialtyRepository.existsByNom(specialty.getNom())) {
            throw new RuntimeException("La spécialité existe déjà: " + specialty.getNom());
        }
        
        existingSpecialty.setNom(specialty.getNom());
        existingSpecialty.setDescription(specialty.getDescription());
        return specialtyRepository.save(existingSpecialty);
    }

    @Override
    public void deleteById(Long id) {
        if (!specialtyRepository.existsById(id)) {
            throw new RuntimeException("Spécialité non trouvée avec l'id: " + id);
        }
        specialtyRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return specialtyRepository.existsById(id);
    }
}

