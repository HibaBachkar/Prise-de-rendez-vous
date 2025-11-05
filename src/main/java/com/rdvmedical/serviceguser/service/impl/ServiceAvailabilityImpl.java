package com.rdvmedical.serviceguser.service.impl;

import com.rdvmedical.serviceguser.domain.entity.Availability;
import com.rdvmedical.serviceguser.domain.entity.Doctor;
import com.rdvmedical.serviceguser.respository.AvailabilityRepository;
import com.rdvmedical.serviceguser.respository.DoctorRepository;
import com.rdvmedical.serviceguser.service.IServiceAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceAvailabilityImpl implements IServiceAvailability {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Availability> findAll() {
        return availabilityRepository.findAll();
    }

    @Override
    public Optional<Availability> findById(Long id) {
        return availabilityRepository.findById(id);
    }

    @Override
    public List<Availability> findByDoctorId(Long doctorId) {
        return availabilityRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Availability> findByDoctorIdAndDisponible(Long doctorId, Boolean disponible) {
        return availabilityRepository.findByDoctorIdAndDisponible(doctorId, disponible);
    }

    @Override
    public List<Availability> findByDoctorIdAndDateDebutBetween(Long doctorId, LocalDateTime start, LocalDateTime end) {
        return availabilityRepository.findByDoctorIdAndDateDebutBetween(doctorId, start, end);
    }

    @Override
    public Availability save(Availability availability) {
        // Charger le docteur si seulement l'ID est fourni
        if (availability.getDoctor() != null && availability.getDoctor().getId() != null && availability.getDoctor().getNom() == null) {
            Doctor doctor = doctorRepository.findById(availability.getDoctor().getId())
                    .orElseThrow(() -> new RuntimeException("Docteur non trouvé avec l'id: " + availability.getDoctor().getId()));
            availability.setDoctor(doctor);
        }
        return availabilityRepository.save(availability);
    }

    @Override
    public Availability update(Long id, Availability availability) {
        Availability existingAvailability = availabilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disponibilité non trouvée avec l'id: " + id));
        
        // Charger le docteur si seulement l'ID est fourni
        if (availability.getDoctor() != null && availability.getDoctor().getId() != null && availability.getDoctor().getNom() == null) {
            Doctor doctor = doctorRepository.findById(availability.getDoctor().getId())
                    .orElseThrow(() -> new RuntimeException("Docteur non trouvé avec l'id: " + availability.getDoctor().getId()));
            existingAvailability.setDoctor(doctor);
        } else if (availability.getDoctor() != null) {
            existingAvailability.setDoctor(availability.getDoctor());
        }
        
        existingAvailability.setDateDebut(availability.getDateDebut());
        existingAvailability.setDateFin(availability.getDateFin());
        existingAvailability.setDisponible(availability.getDisponible());
        
        return availabilityRepository.save(existingAvailability);
    }

    @Override
    public void deleteById(Long id) {
        if (!availabilityRepository.existsById(id)) {
            throw new RuntimeException("Disponibilité non trouvée avec l'id: " + id);
        }
        availabilityRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return availabilityRepository.existsById(id);
    }
}

