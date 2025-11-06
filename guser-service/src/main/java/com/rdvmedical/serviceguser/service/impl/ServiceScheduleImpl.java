package com.rdvmedical.serviceguser.service.impl;

import com.rdvmedical.serviceguser.domain.entity.Doctor;
import com.rdvmedical.serviceguser.domain.entity.Schedule;
import com.rdvmedical.serviceguser.respository.DoctorRepository;
import com.rdvmedical.serviceguser.respository.ScheduleRepository;
import com.rdvmedical.serviceguser.service.IServiceSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceScheduleImpl implements IServiceSchedule {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public List<Schedule> findByDoctorId(Long doctorId) {
        return scheduleRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Schedule> findByDoctorIdAndJourSemaine(Long doctorId, java.time.DayOfWeek jourSemaine) {
        return scheduleRepository.findByDoctorIdAndJourSemaine(doctorId, jourSemaine);
    }

    @Override
    public Schedule save(Schedule schedule) {
        // Charger le docteur si seulement l'ID est fourni
        if (schedule.getDoctor() != null && schedule.getDoctor().getId() != null && schedule.getDoctor().getNom() == null) {
            Doctor doctor = doctorRepository.findById(schedule.getDoctor().getId())
                    .orElseThrow(() -> new RuntimeException("Docteur non trouvé avec l'id: " + schedule.getDoctor().getId()));
            schedule.setDoctor(doctor);
        }
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule update(Long id, Schedule schedule) {
        Schedule existingSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Créneau non trouvé avec l'id: " + id));
        
        // Charger le docteur si seulement l'ID est fourni
        if (schedule.getDoctor() != null && schedule.getDoctor().getId() != null && schedule.getDoctor().getNom() == null) {
            Doctor doctor = doctorRepository.findById(schedule.getDoctor().getId())
                    .orElseThrow(() -> new RuntimeException("Docteur non trouvé avec l'id: " + schedule.getDoctor().getId()));
            existingSchedule.setDoctor(doctor);
        } else if (schedule.getDoctor() != null) {
            existingSchedule.setDoctor(schedule.getDoctor());
        }
        
        existingSchedule.setJourSemaine(schedule.getJourSemaine());
        existingSchedule.setHeureDebut(schedule.getHeureDebut());
        existingSchedule.setHeureFin(schedule.getHeureFin());
        existingSchedule.setDureeCreneau(schedule.getDureeCreneau());
        
        return scheduleRepository.save(existingSchedule);
    }

    @Override
    public void deleteById(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new RuntimeException("Créneau non trouvé avec l'id: " + id);
        }
        scheduleRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return scheduleRepository.existsById(id);
    }
}

