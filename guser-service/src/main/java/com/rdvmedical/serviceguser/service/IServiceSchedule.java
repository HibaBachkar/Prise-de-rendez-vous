package com.rdvmedical.serviceguser.service;

import com.rdvmedical.serviceguser.domain.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface IServiceSchedule {
    List<Schedule> findAll();
    Optional<Schedule> findById(Long id);
    List<Schedule> findByDoctorId(Long doctorId);
    List<Schedule> findByDoctorIdAndJourSemaine(Long doctorId, java.time.DayOfWeek jourSemaine);
    Schedule save(Schedule schedule);
    Schedule update(Long id, Schedule schedule);
    void deleteById(Long id);
    boolean existsById(Long id);
}

