package com.rdvmedical.serviceguser.respository;

import com.rdvmedical.serviceguser.domain.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByDoctorId(Long doctorId);
    List<Availability> findByDoctorIdAndDisponible(Long doctorId, Boolean disponible);
    List<Availability> findByDoctorIdAndDateDebutBetween(Long doctorId, LocalDateTime start, LocalDateTime end);
}

