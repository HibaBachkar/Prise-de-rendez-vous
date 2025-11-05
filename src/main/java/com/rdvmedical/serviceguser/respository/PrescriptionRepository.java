package com.rdvmedical.serviceguser.respository;

import com.rdvmedical.serviceguser.domain.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    Optional<Prescription> findByConsultationId(Long consultationId);
}

