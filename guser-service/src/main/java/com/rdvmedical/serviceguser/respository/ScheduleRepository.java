package com.rdvmedical.serviceguser.respository;

import com.rdvmedical.serviceguser.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByDoctorId(Long doctorId);
    List<Schedule> findByDoctorIdAndJourSemaine(Long doctorId, java.time.DayOfWeek jourSemaine);
}

