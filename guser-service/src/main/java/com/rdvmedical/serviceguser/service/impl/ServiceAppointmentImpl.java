package com.rdvmedical.serviceguser.service.impl;

import com.rdvmedical.serviceguser.client.PatientServiceClient;
import com.rdvmedical.serviceguser.domain.entity.Appointment;
import com.rdvmedical.serviceguser.domain.entity.Doctor;
import com.rdvmedical.serviceguser.domain.entity.Patient;
import com.rdvmedical.serviceguser.respository.AppointmentRepository;
import com.rdvmedical.serviceguser.respository.DoctorRepository;
import com.rdvmedical.serviceguser.respository.PatientRepository;
import com.rdvmedical.serviceguser.service.IServiceAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceAppointmentImpl implements IServiceAppointment {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    // Client Feign pour communication avec le service Patients (quand séparé)
    // Pour l'instant, ce service reste dans guser-service, mais cet exemple montre
    // comment utiliser OpenFeign pour la communication inter-services
    @Autowired(required = false)
    private PatientServiceClient patientServiceClient;

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public List<Appointment> findByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    @Override
    public List<Appointment> findByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> findByDoctorIdAndDateHeureBetween(Long doctorId, LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByDoctorIdAndDateHeureBetween(doctorId, start, end);
    }

    @Override
    public List<Appointment> findByPatientIdAndDateHeureBetween(Long patientId, LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByPatientIdAndDateHeureBetween(patientId, start, end);
    }

    @Override
    public List<Appointment> findByStatut(Appointment.AppointmentStatus statut) {
        return appointmentRepository.findByStatut(statut);
    }

    @Override
    public Appointment save(Appointment appointment) {
        // Exemple d'utilisation d'OpenFeign pour valider le patient via un autre service
        // Si patientServiceClient est disponible (service patients séparé), on l'utilise
        // Sinon, on utilise le repository local (comportement actuel)
        if (patientServiceClient != null && appointment.getPatient() != null && appointment.getPatient().getId() != null) {
            // Validation du patient via OpenFeign (communication inter-services)
            Boolean patientExists = patientServiceClient.patientExists(appointment.getPatient().getId());
            if (patientExists == null || !patientExists) {
                throw new RuntimeException("Patient non trouvé dans le service Patients avec l'id: " + appointment.getPatient().getId());
            }
            // Charger le patient depuis le service distant si nécessaire
            // PatientDTO patientDTO = patientServiceClient.getPatientById(appointment.getPatient().getId());
            // Convertir DTO en Entity si nécessaire
        } else {
            // Comportement local actuel (quand tout est dans le même service)
            if (appointment.getPatient() != null && appointment.getPatient().getId() != null && appointment.getPatient().getNom() == null) {
                Patient patient = patientRepository.findById(appointment.getPatient().getId())
                        .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'id: " + appointment.getPatient().getId()));
                appointment.setPatient(patient);
            }
        }

        // Charger le docteur si seulement l'ID est fourni
        if (appointment.getDoctor() != null && appointment.getDoctor().getId() != null && appointment.getDoctor().getNom() == null) {
            Doctor doctor = doctorRepository.findById(appointment.getDoctor().getId())
                    .orElseThrow(() -> new RuntimeException("Docteur non trouvé avec l'id: " + appointment.getDoctor().getId()));
            appointment.setDoctor(doctor);
        }
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment update(Long id, Appointment appointment) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendez-vous non trouvé avec l'id: " + id));
        
        // Exemple d'utilisation d'OpenFeign pour valider le patient
        if (patientServiceClient != null && appointment.getPatient() != null && appointment.getPatient().getId() != null) {
            Boolean patientExists = patientServiceClient.patientExists(appointment.getPatient().getId());
            if (patientExists == null || !patientExists) {
                throw new RuntimeException("Patient non trouvé dans le service Patients avec l'id: " + appointment.getPatient().getId());
            }
        } else {
            // Comportement local actuel
            if (appointment.getPatient() != null && appointment.getPatient().getId() != null && appointment.getPatient().getNom() == null) {
                Patient patient = patientRepository.findById(appointment.getPatient().getId())
                        .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'id: " + appointment.getPatient().getId()));
                existingAppointment.setPatient(patient);
            } else if (appointment.getPatient() != null) {
                existingAppointment.setPatient(appointment.getPatient());
            }
        }
        
        // Charger le docteur si seulement l'ID est fourni
        if (appointment.getDoctor() != null && appointment.getDoctor().getId() != null && appointment.getDoctor().getNom() == null) {
            Doctor doctor = doctorRepository.findById(appointment.getDoctor().getId())
                    .orElseThrow(() -> new RuntimeException("Docteur non trouvé avec l'id: " + appointment.getDoctor().getId()));
            existingAppointment.setDoctor(doctor);
        } else if (appointment.getDoctor() != null) {
            existingAppointment.setDoctor(appointment.getDoctor());
        }
        
        existingAppointment.setDateHeure(appointment.getDateHeure());
        existingAppointment.setDuree(appointment.getDuree());
        existingAppointment.setStatut(appointment.getStatut());
        existingAppointment.setMotif(appointment.getMotif());
        existingAppointment.setNotes(appointment.getNotes());
        
        return appointmentRepository.save(existingAppointment);
    }

    @Override
    public Appointment updateStatut(Long id, Appointment.AppointmentStatus statut) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendez-vous non trouvé avec l'id: " + id));
        
        existingAppointment.setStatut(statut);
        return appointmentRepository.save(existingAppointment);
    }

    @Override
    public void deleteById(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Rendez-vous non trouvé avec l'id: " + id);
        }
        appointmentRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return appointmentRepository.existsById(id);
    }
}
