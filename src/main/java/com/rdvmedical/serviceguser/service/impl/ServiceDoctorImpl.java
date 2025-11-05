package com.rdvmedical.serviceguser.service.impl;

import com.rdvmedical.serviceguser.domain.entity.Doctor;
import com.rdvmedical.serviceguser.respository.DoctorRepository;
import com.rdvmedical.serviceguser.service.IServiceDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceDoctorImpl implements IServiceDoctor {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public Optional<Doctor> findByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }

    @Override
    public Optional<Doctor> findByNumeroRpps(String numeroRpps) {
        return doctorRepository.findByNumeroRpps(numeroRpps);
    }

    @Override
    public List<Doctor> findBySpecialtyId(Long specialtyId) {
        return doctorRepository.findBySpecialtyId(specialtyId);
    }

    @Override
    public List<Doctor> findByActif(Boolean actif) {
        return doctorRepository.findByActif(actif);
    }

    @Override
    public Doctor save(Doctor doctor) {
        if (doctorRepository.existsByEmail(doctor.getEmail())) {
            throw new RuntimeException("L'email existe déjà: " + doctor.getEmail());
        }
        if (doctor.getNumeroRpps() != null && doctorRepository.existsByNumeroRpps(doctor.getNumeroRpps())) {
            throw new RuntimeException("Le numéro RPPS existe déjà: " + doctor.getNumeroRpps());
        }
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor update(Long id, Doctor doctor) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docteur non trouvé avec l'id: " + id));
        
        if (!existingDoctor.getEmail().equals(doctor.getEmail()) && 
            doctorRepository.existsByEmail(doctor.getEmail())) {
            throw new RuntimeException("L'email existe déjà: " + doctor.getEmail());
        }
        if (doctor.getNumeroRpps() != null && 
            !doctor.getNumeroRpps().equals(existingDoctor.getNumeroRpps()) && 
            doctorRepository.existsByNumeroRpps(doctor.getNumeroRpps())) {
            throw new RuntimeException("Le numéro RPPS existe déjà: " + doctor.getNumeroRpps());
        }
        
        existingDoctor.setNom(doctor.getNom());
        existingDoctor.setPrenom(doctor.getPrenom());
        existingDoctor.setEmail(doctor.getEmail());
        existingDoctor.setTelephone(doctor.getTelephone());
        existingDoctor.setNumeroRpps(doctor.getNumeroRpps());
        existingDoctor.setSpecialty(doctor.getSpecialty());
        existingDoctor.setActif(doctor.getActif());
        
        return doctorRepository.save(existingDoctor);
    }

    @Override
    public void deleteById(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Docteur non trouvé avec l'id: " + id);
        }
        doctorRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return doctorRepository.existsById(id);
    }
}

